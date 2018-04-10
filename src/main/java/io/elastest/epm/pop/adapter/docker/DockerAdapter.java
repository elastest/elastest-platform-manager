package io.elastest.epm.pop.adapter.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.PullImageCmd;
import com.github.dockerjava.api.exception.BadRequestException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.PullImageResultCallback;
import io.elastest.epm.core.ResourceGroupManagement;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import io.elastest.epm.pop.interfaces.VirtualisedComputeResourcesManagmentInterface;
import io.elastest.epm.pop.interfaces.VirtualisedNetworkResourceManagementInterface;
import io.elastest.epm.pop.messages.compute.*;
import io.elastest.epm.pop.messages.network.*;
import io.elastest.epm.pop.model.common.Filter;
import io.elastest.epm.pop.model.common.OperationalState;
import io.elastest.epm.pop.model.compute.VirtualCompute;
import io.elastest.epm.pop.model.network.*;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.ResourceGroupRepository;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.ArchiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DockerAdapter
    implements VirtualisedComputeResourcesManagmentInterface,
        VirtualisedNetworkResourceManagementInterface,
        RuntimeManagmentInterface,
        PackageManagementInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private DockerProperties dockerProperties;
  @Autowired private ResourceGroupRepository resourceGroupRepository;
  @Autowired private ResourceGroupManagement resourceGroupManagement;

  private DockerClient getDockerClient(PoP poP) throws AdapterException {
    log.debug("Preparing docker client for: " + poP);
    DockerClientConfig dockerClientConfig =
        DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost(poP.getInterfaceEndpoint())
            .build();
    DockerClient client = DockerClientBuilder.getInstance(dockerClientConfig).build();
    return client;
  }

  @Override
  public AllocateComputeResponse allocateVirtualisedComputeResource(
      AllocateComputeRequest allocateComputeRequest, PoP poP) throws AdapterException {
    log.info("Allocating container " + allocateComputeRequest.getComputeName());
    DockerClient dockerClient = getDockerClient(poP);

    String containerName = allocateComputeRequest.getComputeName();
    String imageId = allocateComputeRequest.getVcImageId();
    if (!imageExists(poP, imageId)) {
      log.info("Not found image " + imageId + ". Try to pull...");
      pullImage(poP, imageId);
    }
    LogConfig logConfig = getLogConfig(containerName, allocateComputeRequest.getMetaData());
    List<String> environmentVariables =
        getMetaDataValuesOfKey("ENVIRONMENT_VARIABLE", allocateComputeRequest.getMetaData());
    List<PortBinding> portBindings =
        getPortBindingsFromMetadata(allocateComputeRequest.getMetaData());
    List<Volume> volumes = getVolumesFromMetaData(allocateComputeRequest.getMetaData());
    CreateContainerResponse createdContainer = null;
    try {
      log.debug("Creating Container...");
      createdContainer =
          dockerClient
              .createContainerCmd(imageId)
              .withName(containerName)
              .withLogConfig(logConfig)
              .withEnv(environmentVariables)
              .withVolumes(volumes)
              .withPortBindings(portBindings)
              .withNetworkMode(
                  getMetadataValueOfKey(allocateComputeRequest.getMetaData(), "NETWORK", "bridge"))
              .exec();
      log.debug("Created Container: " + createdContainer);
      log.debug("Starting Container: " + createdContainer.getId());
      dockerClient.startContainerCmd(createdContainer.getId()).exec();
      log.debug("Started Container: " + createdContainer.getId());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new AdapterException(e.getMessage(), createdContainer.getId());
    }
    Container deployedContainer = null;
    for (Container container : dockerClient.listContainersCmd().withShowAll(true).exec()) {
      if (container.getId().equals(createdContainer.getId())) {
        deployedContainer = container;
      }
    }
    AllocateComputeResponse allocateComputeResponse = new AllocateComputeResponse();
    allocateComputeResponse.setComputeData(translateContainerToVirtualCompute(deployedContainer));
    log.info("Allocated container " + allocateComputeResponse);
    return allocateComputeResponse;
  }

  private List<String> getMetaDataValuesOfKey(String key, List<KeyValuePair> metaData) {
    log.debug("Find values in metadata for key: " + key + " -> " + metaData);
    List<String> values = new ArrayList<>();
    for (KeyValuePair keyValuePair : metaData) {
      if (keyValuePair.getKey().equals(key)) {
        values.add(keyValuePair.getValue());
      }
    }
    log.debug("Found values in metadata for key: " + key + " -> " + values);
    return values;
  }

  private List<PortBinding> getPortBindingsFromMetadata(List<KeyValuePair> metaData) {
    log.debug("Find port bindings in metadata -> " + metaData);
    List<PortBinding> portBindings = new ArrayList<>();
    for (KeyValuePair keyValuePair : metaData) {
      if (keyValuePair.getKey().equals("PORT_BINDING")) {
        String[] portSplit = keyValuePair.getValue().split(Pattern.quote(":"));
        if (portSplit.length == 2) {
          String hostPort = portSplit[0];
          String exposedPortString = portSplit[1];
          String[] exposedPortSplit = exposedPortString.split(Pattern.quote("/"));
          if (exposedPortSplit.length == 2) {
            Ports.Binding binding = new Ports.Binding("0.0.0.0", hostPort);
            int remotePort = Integer.parseInt(exposedPortSplit[0]);
            String protocol = exposedPortSplit[1].toUpperCase();
            InternetProtocol internetProtocol = InternetProtocol.valueOf(protocol);
            ExposedPort exposedPort = new ExposedPort(remotePort, internetProtocol);
            PortBinding portBinding = new PortBinding(binding, exposedPort);
            portBindings.add(portBinding);
          } else {
            throw new BadRequestException("PORT_BINDING has wrong format -> \"8080:8080/tcp\"");
          }
        } else {
          throw new BadRequestException("PORT_BINDING has wrong format -> \"8080:8080/tcp\"");
        }
      }
    }

    log.debug("Found port bindings in metadata -> " + portBindings);
    return portBindings;
  }

  private List<Volume> getVolumesFromMetaData(List<KeyValuePair> metaData) {
    log.debug("Find volumes in metadata -> " + metaData);
    List<Volume> volumes = new ArrayList<>();
    for (KeyValuePair keyValuePair : metaData) {
      if (keyValuePair.getKey().equals("VOLUME")) {
        Volume volume = new Volume(keyValuePair.getValue());
        volumes.add(volume);
      }
    }
    log.debug("Found volumes in metadata -> " + volumes);
    return volumes;
  }

  private LogConfig getLogConfig(String containerName, List<KeyValuePair> metadata) {
    log.info("Creating Log config for " + containerName + ": " + metadata);
    LogConfig logConfig = new LogConfig();
    if (ifMetadataExists("LOGSTASH_ADDRESS", metadata)) {
      logConfig.setType(
          LogConfig.LoggingType.valueOf(
              getMetadataValueOfKey(metadata, "LOGSTASH_LOGGING_TYPE", "SYSLOG")));
      HashMap logConfigMap = new HashMap();
      logConfigMap.put(
          "syslog-address",
          getMetadataValueOfKey(metadata, "LOGSTASH_ADDRESS", "tcp://localhost:5000"));
      logConfigMap.put("tag", containerName);
      logConfig.setConfig(logConfigMap);
      log.info("Created Log config for " + containerName + ": " + logConfig);
    } else if (dockerProperties.getLogStash().isEnabled()) {
      logConfig.setType(
          LogConfig.LoggingType.valueOf(
              getMetadataValueOfKey(metadata, "LOGSTASH_LOGGING_TYPE", "SYSLOG")));
      HashMap logConfigMap = new HashMap();
      logConfigMap.put("syslog-address", dockerProperties.getLogStash().getAddress());
      logConfigMap.put("tag", containerName);
      logConfig.setConfig(logConfigMap);
      log.info("Created Log config for " + containerName + ": " + logConfig);
    } else {
      log.debug("LogStash is neither enabled by default nor enabled via the VDU definition...");
    }
    return logConfig;
  }

  private boolean ifMetadataExists(String key, List<KeyValuePair> metadata) {
    String value = null;
    if (metadata != null)
      for (KeyValuePair keyValuePair : metadata) {
        if (keyValuePair.getKey().equals(key)) {
          value = keyValuePair.getValue();
          break;
        }
      }
    if (value == null) {
      log.debug("Metadata value for key \"" + key + "\" not found.");
      return false;
    } else {
      return true;
    }
  }

  private boolean imageExists(PoP poP, String vcImageId) throws AdapterException {
    log.debug("Checking if image " + vcImageId + " exists");
    boolean imageExists = false;
    DockerClient dockerClient = getDockerClient(poP);
    List<Image> availableImages = dockerClient.listImagesCmd().exec();
    for (Image availableImage : availableImages) {
      log.debug("Image under consideration " + availableImage);
      if (availableImage.getId().equals(vcImageId)) {
        log.debug("Found image " + availableImage);
        imageExists = true;
        break;
      }
      if (availableImage.getRepoTags() != null) {
        for (String tag : availableImage.getRepoTags()) {
          if (tag.equals(vcImageId) || tag.equals(vcImageId + ":latest")) {
            log.debug("Found image " + availableImage);
            imageExists = true;
            break;
          }
        }
      }
      if (imageExists) {
        break;
      }
    }
    return imageExists;
  }

  private void pullImage(PoP poP, String vcImageId) throws AdapterException {
    log.info("Pull image " + vcImageId);
    DockerClient dockerClient = getDockerClient(poP);
    try {
      PullImageCmd pullImageCmd = dockerClient.pullImageCmd(vcImageId);
      pullImageCmd.exec(new PullImageResultCallback()).awaitSuccess();
    } catch (Exception e) {
      log.error("Not Found image " + vcImageId + " -> " + e.getMessage());
      throw new AdapterException("Not found image " + vcImageId + " -> " + e.getMessage());
    }
    log.info("Pulled image " + vcImageId);
  }

  @Override
  public QueryComputeResponse queryVirtualisedComputeResource(
      QueryComputeRequest queryComputeRequest, PoP poP) throws AdapterException {
    log.debug("Querying virtualised compute resources: " + queryComputeRequest);
    DockerClient dockerClient = getDockerClient(poP);
    List<Container> containers = dockerClient.listContainersCmd().exec();
    List<VirtualCompute> virtualComputes = new ArrayList<>();
    for (Container container : containers) {
      virtualComputes.add(translateContainerToVirtualCompute(container));
    }
    QueryComputeResponse queryComputeResponse = new QueryComputeResponse();
    queryComputeResponse.setQueryResult(
        queryComputeRequest.getQueryComputeFilter().filter(virtualComputes));
    log.debug("Queried virtualised compute resources: " + queryComputeResponse);
    return queryComputeResponse;
  }

  @Override
  public UpdateComputeResponse updateVirtualisedComputeResource(
      UpdateComputeRequest updateComputeRequest, PoP poP) throws AdapterException {
    log.info("Updating container " + updateComputeRequest);
    DockerClient dockerClient = getDockerClient(poP);
    String computeId = updateComputeRequest.getComputeId();
    List<VirtualNetworkInterfaceData> networkInterfacesNew =
        updateComputeRequest.getNetworkInterfaceNew();

    for (VirtualNetworkInterfaceData virtualNetworkInterfaceData : networkInterfacesNew) {
      try {
        String networkId = virtualNetworkInterfaceData.getNetworkId();
        dockerClient
            .connectToNetworkCmd()
            .withContainerId(computeId)
            .withNetworkId(networkId)
            .exec();
        //      dockerClient.connectToNetworkCmd().withContainerId(computeId).withNetworkId(networkId);
      } catch (Exception e) {
        log.error(e.getMessage());
        throw new AdapterException(e.getMessage());
      }
    }

    QueryComputeRequest queryComputeRequest = new QueryComputeRequest();

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
        virtualCompute -> ((VirtualCompute) virtualCompute).getComputeId().matches(computeId));

    Filter filter = new Filter();
    filter.setPredicates(predicates);
    queryComputeRequest.setQueryComputeFilter(filter);
    QueryComputeResponse queryComputeResponse =
        this.queryVirtualisedComputeResource(queryComputeRequest, poP);
    UpdateComputeResponse updateComputeResponse = new UpdateComputeResponse();
    updateComputeResponse.setComputeId(computeId);
    updateComputeResponse.setComputeData(queryComputeResponse.getQueryResult().get(0));
    log.info("Updated container " + updateComputeResponse);
    return updateComputeResponse;
  }

  @Override
  public TerminateComputeResponse terminateVirtualisedComputeResource(
      TerminateComputeRequest terminateComputeRequest, PoP poP) throws AdapterException {
    DockerClient dockerClient = getDockerClient(poP);
    TerminateComputeResponse terminateComputeResponse = new TerminateComputeResponse();
    terminateComputeResponse.setComputeId(new ArrayList<>());
    for (String computeId : terminateComputeRequest.getComputeId()) {
      log.info("Terminating container " + computeId);
      try {
        log.debug("Stopping container " + computeId);
        dockerClient.stopContainerCmd(computeId).exec();
        log.debug("Stopped container " + computeId);
      } catch (Exception exc) {
        log.error(exc.getMessage(), exc);
      }
      try {
        log.debug("Deleting container " + computeId);
        dockerClient.removeContainerCmd(computeId).exec();
        log.debug("Deleted container " + computeId);
        terminateComputeResponse.getComputeId().add(computeId);
      } catch (Exception exc) {
        log.error(exc.getMessage(), exc);
      }
    }
    return terminateComputeResponse;
  }

  @Override
  public AllocateNetworkResponse allocateVirtualisedNetworkResource(
      AllocateNetworkRequest allocateNetworkRequest, PoP poP) throws AdapterException {
    log.info("Allocating VirtualisedNetworkResource " + allocateNetworkRequest);
    DockerClient dockerClient = getDockerClient(poP);
    NetworkResourceType networkType = allocateNetworkRequest.getNetworkResourceType();
    if (networkType != NetworkResourceType.NETWORK) {
      throw new AdapterException(
          "Docker is not able to add a subnet/port to an existing network...");
    }
    String networkName = allocateNetworkRequest.getNetworkResourceName();
    CreateNetworkResponse createNetworkResponse;
    if (allocateNetworkRequest.getTypeNetworkData() != null) {
      String networkDriver =
          getMetadataValueOfKey(
              allocateNetworkRequest.getTypeNetworkData().getMetadata(),
              "DOCKER_NETWORK_DRIVER",
              "bridge");
      List<NetworkSubnetData> subnetDataList =
          allocateNetworkRequest.getTypeNetworkData().getLayer3Attributes();
      if (subnetDataList != null && !subnetDataList.isEmpty()) {
        NetworkSubnetData networkSubnetData = subnetDataList.get(0);
        String cidr =
            getMetadataValueOfKey(
                networkSubnetData.getMetadata(),
                "CIDR",
                "192.168." + (int) (Math.random() * 255) + ".0/24");
        String gateway;
        if (networkSubnetData.getGatewayIp() != null
            && networkSubnetData.getGatewayIp().getAddress() != null) {
          gateway = networkSubnetData.getGatewayIp().getAddress();
        } else {
          gateway = cidr.substring(0, cidr.lastIndexOf("/") - 1) + "1";
        }
        Network.Ipam ipam = new Network.Ipam();
        Network.Ipam.Config networkConfig = new Network.Ipam.Config();
        networkConfig = networkConfig.withSubnet(cidr).withGateway(gateway);
        ipam = ipam.withConfig(networkConfig);
        createNetworkResponse =
            dockerClient
                .createNetworkCmd()
                .withName(networkName)
                .withDriver(networkDriver)
                .withIpam(ipam)
                .exec();
      } else {
        createNetworkResponse =
            dockerClient.createNetworkCmd().withName(networkName).withDriver(networkDriver).exec();
      }
    } else {
      createNetworkResponse = dockerClient.createNetworkCmd().withName(networkName).exec();
    }

    List<Network> networks =
        dockerClient.listNetworksCmd().withIdFilter(createNetworkResponse.getId()).exec();
    if (networks.isEmpty()) {
      throw new AdapterException("Failed to create network: " + allocateNetworkRequest);
    }
    Network dockerNetwork = networks.get(0);
    log.debug("Retrieved information of network just created: " + dockerNetwork);

    AllocateNetworkResponse allocateNetworkResponse = new AllocateNetworkResponse();
    allocateNetworkResponse.setNetworkData(translateNetworkToVirtualNetwork(dockerNetwork));
    log.info("Allocated VirtualisedNetworkResource " + allocateNetworkResponse);
    return allocateNetworkResponse;
  }

  @Override
  public QueryNetworkResponse queryVirtualisedNetworkResource(
      QueryNetworkRequest queryNetworkRequest, PoP poP) throws AdapterException {
    log.debug("Querying virtualised network resources: " + queryNetworkRequest);
    DockerClient dockerClient = getDockerClient(poP);
    List<Network> dockerNetworks = dockerClient.listNetworksCmd().exec();
    log.debug("Received networks from docker: " + dockerNetworks);
    List<VirtualNetwork> virtualNetworks = new ArrayList<>();
    for (Network dockerNetwork : dockerNetworks) {
      virtualNetworks.add(translateNetworkToVirtualNetwork(dockerNetwork));
    }
    QueryNetworkResponse queryNetworkResponse = new QueryNetworkResponse();
    queryNetworkResponse.setQueryResult(
        queryNetworkRequest.getQueryNetworkFilter().filter(virtualNetworks));
    log.debug("Queried virtualised network resources: " + queryNetworkResponse);
    return queryNetworkResponse;
  }

  @Override
  public UpdateNetworkResponse updateVirtualisedNetworkResource(
      UpdateNetworkRequest updateNetworkRequest, PoP poP) {
    throw new UnsupportedOperationException();
  }

  @Override
  public TerminateNetworkResponse terminateVirtualisedNetworkResource(
      TerminateNetworkRequest terminateNetworkRequest, PoP poP) throws AdapterException {
    log.debug("Terminating virtualised network resources: " + terminateNetworkRequest);
    DockerClient dockerClient = getDockerClient(poP);
    List<String> removedNetworks = new ArrayList<>();
    for (String networkId : terminateNetworkRequest.getNetworkResourceId()) {
      log.debug("Removing network: " + networkId);
      dockerClient.removeNetworkCmd(networkId).exec();
      removedNetworks.add(networkId);
      log.debug("Removed network: " + networkId);
    }
    TerminateNetworkResponse terminateNetworkResponse = new TerminateNetworkResponse();
    terminateNetworkResponse.setNetworkResourceId(removedNetworks);
    log.debug("Terminated virtualised network resources: " + terminateNetworkRequest);
    return terminateNetworkResponse;
  }

  private VirtualCompute translateContainerToVirtualCompute(Container container) {
    log.debug("Translating container to virtualcompute -> " + container);
    VirtualCompute virtualCompute = new VirtualCompute();
    virtualCompute.setVcImageId(container.getImage());
    OperationalState operationalState =
        container.getStatus().startsWith("Up")
            ? OperationalState.enabled
            : OperationalState.disabled;
    virtualCompute.setOperationalState(operationalState);
    virtualCompute.setComputeId(container.getId());
    virtualCompute.setComputeName(container.getNames()[0]);
    Set<VirtualNetworkInterface> virtualNetworkInterfaceSet = new HashSet<>();
    for (String networkName : container.getNetworkSettings().getNetworks().keySet()) {
      ContainerNetwork containerNetwork =
          container.getNetworkSettings().getNetworks().get(networkName);
      VirtualNetworkInterface virtualNetworkInterface = new VirtualNetworkInterface();

      //general network information
      virtualNetworkInterface.setOwnerId(container.getId());
      virtualNetworkInterface.setResourceId(containerNetwork.getNetworkID());
      virtualNetworkInterface.setOperationalState(OperationalState.enabled);

      //IP information
      Set<IpAddress> ipAddresses = new HashSet<>();
      IpAddress ipAddress = new IpAddress();
      ipAddress.setAddress(containerNetwork.getIpAddress());
      ipAddresses.add(ipAddress);
      virtualNetworkInterface.setIpAddress(ipAddresses);

      //MAC information
      MacAddress macAddress = new MacAddress();
      macAddress.setAddress(containerNetwork.getMacAddress());
      virtualNetworkInterface.setMacAddress(macAddress);

      virtualNetworkInterfaceSet.add(virtualNetworkInterface);
    }
    virtualCompute.setVirtualNetworkInterface(virtualNetworkInterfaceSet);
    log.debug("Translated container to virtualcompute -> " + virtualCompute);
    return virtualCompute;
  }

  private VirtualNetwork translateNetworkToVirtualNetwork(Network network) {

    VirtualNetwork virtualNetwork = new VirtualNetwork();
    virtualNetwork.setOperationalState(OperationalState.enabled);

    virtualNetwork.setNetworkResourceName(network.getName());
    virtualNetwork.setNetworkResourceId(network.getId());
    Set<KeyValuePair> metadata = new HashSet<>();
    virtualNetwork.setMetadata(metadata);

    List<NetworkSubnet> subnets = new ArrayList<>();
    for (Network.Ipam.Config config : network.getIpam().getConfig()) {
      NetworkSubnet subnet = new NetworkSubnet();
      List<KeyValuePair> metadataSubnet = new ArrayList<>();
      subnet.setOperationalState(OperationalState.enabled);
      IpAddress gateway = new IpAddress();
      gateway.setAddress(config.getGateway());
      subnet.setGatewayIp(gateway);
      metadataSubnet.add(new KeyValuePair("CIDR", config.getSubnet()));
      subnet.setMetadata(metadataSubnet);
      subnets.add(subnet);
    }

    virtualNetwork.setSubnet(subnets);
    return virtualNetwork;
  }

  private String getMetadataValueOfKey(
      List<KeyValuePair> metadata, String key, String defaultValue) {
    String value = null;
    if (metadata != null)
      for (KeyValuePair keyValuePair : metadata) {
        if (keyValuePair.getKey().equals(key)) {
          value = keyValuePair.getValue();
          break;
        }
      }
    if (value == null) {
      log.warn(
          "Metadata value for key \"" + key + "\" not found. Use default value: " + defaultValue);
      value = defaultValue;
    }
    return value;
  }

  @Override
  public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
      throws AdapterException {
    DockerClient dockerClient = getDockerClient(pop);
    InputStream inputStream = null;
    if (existsContainer(vdu.getComputeId(), pop)) {
      log.trace("Copying {} from container {}", filepath, vdu.getName());
      try {
        inputStream = dockerClient.copyArchiveFromContainerCmd(vdu.getComputeId(), filepath).exec();
      } catch (Exception exc) {
        log.error(exc.getMessage(), exc);
        throw new AdapterException(exc.getMessage());
      }
    }
    return inputStream;
  }

  @Override
  public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop)
      throws AdapterException {
    DockerClient dockerClient = getDockerClient(pop);
    String output = null;
    log.trace(
        "Executing command {} in container {} (await completion {})",
        command,
        vdu.getName(),
        awaitCompletion);
    if (existsContainer(vdu.getComputeId(), pop)) {
      ExecCreateCmdResponse exec =
          dockerClient
              .execCreateCmd(vdu.getComputeId())
              .withCmd(command.split(" "))
              .withTty(false)
              .withAttachStdin(true)
              .withAttachStdout(true)
              .withAttachStderr(true)
              .exec();

      log.trace("Command executed. Exec id: {}", exec.getId());
      OutputStream outputStream = new ByteArrayOutputStream();
      try {
        ExecStartResultCallback startResultCallback =
            dockerClient
                .execStartCmd(exec.getId())
                .withDetach(false)
                .withTty(true)
                .exec(new ExecStartResultCallback(outputStream, System.err));
        if (awaitCompletion) {
          startResultCallback.awaitCompletion();
        }
        output = outputStream.toString();
      } catch (InterruptedException e) {
        log.warn("Exception executing command {} on container {}", command, vdu.getName(), e);
      } finally {
        log.trace("Callback terminated. Result: {}", output);
      }
    }
    return output;
  }

  @Override
  public void startInstance(VDU vdu, PoP pop) throws AdapterException {
    DockerClient dockerClient = getDockerClient(pop);
    if (!isRunningContainer(vdu.getComputeId(), pop)) {
      log.debug("Starting container {}", vdu.getName());
      dockerClient.startContainerCmd(vdu.getComputeId()).exec();
    } else {
      log.debug("Container {} is already running", vdu.getName());
    }
  }

  @Override
  public void stopInstance(VDU vdu, PoP pop) throws AdapterException {
    DockerClient dockerClient = getDockerClient(pop);
    if (isRunningContainer(vdu.getComputeId(), pop)) {
      log.debug("Stopping container {}", vdu.getName());
      dockerClient.stopContainerCmd(vdu.getComputeId()).exec();
    } else {
      log.debug("Container {} is not running", vdu.getName());
    }
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
      throws AdapterException, IOException {
    DockerClient dockerClient = getDockerClient(pop);
    if (existsContainer(vdu.getComputeId(), pop)) {
      log.trace("Copying {} to container {}", remotePath, vdu.getName());
      dockerClient
          .copyArchiveToContainerCmd(vdu.getComputeId())
          .withRemotePath(remotePath)
          .withHostResource(hostPath)
          .exec();
    }
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop)
      throws AdapterException, IOException {
    DockerClient dockerClient = getDockerClient(pop);
    if (existsContainer(vdu.getComputeId(), pop)) {
      if (file != null) {
        log.debug("Copying {} to container {}", file.getOriginalFilename(), vdu.getName());
        InputStream is = file.getInputStream();
        if (!file.getOriginalFilename().endsWith(".tar")) {
          File convFile = Utils.convert(file);
          //          file.transferTo(convFile);
          File tarFile = Utils.compressFileToTar(convFile);
          is = new FileInputStream(tarFile);
        }
        dockerClient
            .copyArchiveToContainerCmd(vdu.getComputeId())
            .withRemotePath(remotePath)
            .withTarInputStream(is)
            .exec();
        is.close();
        log.debug("Copied {} to container {}", file.getOriginalFilename(), vdu.getName());
      }
    }
  }

  public boolean existsContainer(String containerId, PoP pop) throws AdapterException {
    DockerClient dockerClient = getDockerClient(pop);
    boolean exists = true;
    try {
      dockerClient.inspectContainerCmd(containerId).exec();
      log.debug("Container {} already exist", containerId);

    } catch (NotFoundException e) {
      log.debug("Container {} does not exist", containerId);
      exists = false;
    }
    return exists;
  }

  public boolean isRunningContainer(String containerId, PoP pop) throws AdapterException {
    DockerClient dockerClient = getDockerClient(pop);
    boolean isRunning = false;
    if (existsContainer(containerId, pop)) {
      isRunning = dockerClient.inspectContainerCmd(containerId).exec().getState().getRunning();
      log.debug("Container {} is running: {}", containerId, isRunning);
    }
    return isRunning;
  }

  @Override
  public ResourceGroup deploy(InputStream data)
      throws io.elastest.epm.exception.NotFoundException, IOException, ArchiveException,
          AdapterException, AllocationException, io.elastest.epm.exception.BadRequestException {
    ResourceGroup rg = Utils.extractResourceGroup(data);
    if (rg != null) {
      return resourceGroupManagement.deployResourceGroup(rg);
    } else
      throw new io.elastest.epm.exception.NotFoundException(
          "Couldnt find a valid RG in the package!");
  }

  @Override
  public ResourceGroup deploy(InputStream data, PoP poP)
      throws io.elastest.epm.exception.NotFoundException, IOException, AdapterException,
          io.elastest.epm.exception.BadRequestException, AllocationException, ArchiveException {
    // In the docker case the pop is already specified so ignore
    return deploy(data);
  }

  @Override
  public void terminate(String packageId) throws io.elastest.epm.exception.NotFoundException {
    try {
      ResourceGroup rg = resourceGroupRepository.findOneByName(packageId);
      resourceGroupManagement.terminateResourceGroup(rg.getId());
    } catch (AdapterException e) {
      e.printStackTrace();
    } catch (TerminationException e) {
      e.printStackTrace();
    }
  }

  private io.elastest.epm.model.Event createEvent(String desc) {
    io.elastest.epm.model.Event event = new io.elastest.epm.model.Event();
    event.description(desc);
    event.setTimestamp(Long.toString(System.currentTimeMillis()));
    return event;
  }
}
