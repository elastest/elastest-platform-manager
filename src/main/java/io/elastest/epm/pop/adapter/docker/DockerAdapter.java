/*
 *
 *  * (C) Copyright 2016 NUBOMEDIA (http://www.nubomedia.eu)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package io.elastest.epm.pop.adapter.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.VirtualisedComputeResourcesManagmentInterface;
import io.elastest.epm.pop.interfaces.VirtualisedNetworkResourceManagementInterface;
import io.elastest.epm.pop.messages.compute.*;
import io.elastest.epm.pop.messages.network.*;
import io.elastest.epm.pop.model.common.Filter;
import io.elastest.epm.pop.model.common.OperationalState;
import io.elastest.epm.pop.model.compute.VirtualCompute;
import io.elastest.epm.pop.model.network.*;
import java.util.*;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DockerAdapter
    implements VirtualisedComputeResourcesManagmentInterface,
        VirtualisedNetworkResourceManagementInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  private DockerClient getDockerClient(PoP poP) throws AdapterException {
    //      String username = null;
    //      String password = null;
    //      for (KeyValuePair keyValue : poP.getAccessInfo()) {
    //        if (keyValue.getKey().equals("username")) {
    //          username = keyValue.getValue();
    //        } else if (keyValue.getKey().equals("password")) {
    //          password = keyValue.getValue();
    //        }
    //      }
    //if (username.isEmpty() || password.isEmpty()) {
    //  throw new AdapterException(400, "Not found username/password");
    //}
    //        DockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost(poP.getInterfaceEndpoint()).withRegistryUsername(username).withRegistryPassword(password).build();
    log.debug("Preparing docker client for: " + poP);
    DockerClientConfig dockerClientConfig =
        DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost(poP.getInterfaceEndpoint())
            .build();
    DockerClient client = DockerClientBuilder.getInstance(dockerClientConfig).build();
    //    DockerClient client = DockerClientBuilder.getInstance().build();
    return client;
  }

  @Override
  public AllocateComputeResponse allocateVirtualisedComputeResource(
      AllocateComputeRequest allocateComputeRequest, PoP poP) throws AdapterException {
    log.info("Allocating container " + allocateComputeRequest.getComputeName());
    DockerClient dockerClient = getDockerClient(poP);
    log.debug("Check if image " + allocateComputeRequest.getVcImageId() + " exists");
    List<Image> availableImages = dockerClient.listImagesCmd().exec();
    boolean imageExists = false;
    for (Image availableImage : availableImages) {
      log.debug("Image under consideration " + availableImage);
      if (availableImage.getId().equals(allocateComputeRequest.getVcImageId())) {
        log.debug("Found image " + availableImage);
        imageExists = true;
        break;
      }
      for (String tag : availableImage.getRepoTags()) {
        if (tag.equals(allocateComputeRequest.getVcImageId())
            || tag.equals(allocateComputeRequest.getVcImageId() + ":latest")) {
          log.debug("Found image " + availableImage);
          imageExists = true;
        }
        break;
      }
    }
    if (!imageExists) {
      log.error("Not Found image " + allocateComputeRequest.getVcImageId());
      throw new AdapterException("Not found image " + allocateComputeRequest.getVcImageId());
    }
    String containerName = allocateComputeRequest.getComputeName();
    LogConfig logConfig = new LogConfig();
    //logConfig.setType(LogConfig.LoggingType.GELF);
    logConfig.setType(
        LogConfig.LoggingType.valueOf(
            getMetadataValueOfKey(
                allocateComputeRequest.getMetaData(), "LOGSTASH_LOGGING_TYPE", "SYSLOG")));
    HashMap logConfigMap = new HashMap();
    //logConfigMap.put("gelf-address", "udp://10.147.66.246:5000");
    logConfigMap.put(
        "syslog-address",
        getMetadataValueOfKey(
            allocateComputeRequest.getMetaData(), "LOGSTASH_ADDRESS", "tcp://localhost:5000"));
    logConfigMap.put("tag", containerName);
    logConfig.setConfig(logConfigMap);
    String imageName = allocateComputeRequest.getVcImageId();
    //Link link = new Link("logstash-server", "logstash");
    CreateContainerResponse createdContainer =
        //dockerClient.createContainerCmd(imageName).withName(containerName).withLogConfig(logConfig).withLinks(link).withNetworkMode("services_elastest").exec();
        dockerClient
            .createContainerCmd(imageName)
            .withName(containerName)
            //                        .withLogConfig(logConfig)
            .withNetworkDisabled(true)
            .exec();
    dockerClient.startContainerCmd(createdContainer.getId()).exec();

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
      String networkId = virtualNetworkInterfaceData.getNetworkId();
      dockerClient.connectToNetworkCmd().withContainerId(computeId).withNetworkId(networkId).exec();
      //      dockerClient.connectToNetworkCmd().withContainerId(computeId).withNetworkId(networkId);
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
      dockerClient.stopContainerCmd(computeId).exec();
      dockerClient.removeContainerCmd(computeId).exec();
      log.info("Terminated container " + computeId);
      terminateComputeResponse.getComputeId().add(computeId);
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

  //  public static void main(String[] args) throws AdapterException {
  //    PoP poP = new PoP();
  //    //    poP.setInterfaceEndpoint("tcp://localhost:2376");
  //    poP.setInterfaceEndpoint("unix:///var/run/docker.sock");
  //
  //    DockerAdapter dockerAdapter = new DockerAdapter();
  //    DockerClient dockerClient = dockerAdapter.getDockerClient(poP);
  //    ListImagesCmd listImagesCmd = dockerClient.listImagesCmd();
  //    for (Image image : listImagesCmd.exec()) {
  //      for (String tag : image.getRepoTags()) {
  //        System.out.println(tag);
  //      }
  //    }
  //    for (Container container : dockerClient.listContainersCmd().withShowAll(true).exec()) {
  //      System.out.println(container.toString());
  //    }
  //
  //    AllocateComputeRequest allocateComputeRequest = new AllocateComputeRequest();
  //    allocateComputeRequest.setVcImageId("ubuntu");
  //    allocateComputeRequest.setComputeName("vdu_test_container5");
  //    List<KeyValuePair> metadata = new ArrayList<>();
  //    //    KeyValuePair logstashConf = new KeyValuePair("LOGSTASH_ADDRESS", "tcp://:5000");
  //    //    metadata.add(logstashConf);
  //    //    KeyValuePair logstashType = new KeyValuePair("LOGSTASH_LOGGING_TYPE", "SYSLOG");
  //    //    metadata.add(logstashType);
  //    allocateComputeRequest.setMetaData(metadata);
  //    AllocateComputeResponse allocateComputeResponse =
  //        dockerAdapter.allocateVirtualisedComputeResource(allocateComputeRequest, poP);
  //    System.out.println(allocateComputeResponse);
  //
  //    QueryComputeRequest queryComputeRequest = new QueryComputeRequest();
  //
  //    List<Predicate> predicates = new ArrayList<>();
  //    predicates.add(
  //        virtualCompute ->
  //            ((VirtualCompute) virtualCompute)
  //                .getComputeName()
  //                .matches(allocateComputeResponse.getComputeData().getComputeName()));
  //    Filter filter = new Filter();
  //    filter.setPredicates(predicates);
  //    queryComputeRequest.setQueryComputeFilter(filter);
  //    QueryComputeResponse queryComputeResponse =
  //        dockerAdapter.queryVirtualisedComputeResource(queryComputeRequest, poP);
  //    for (VirtualCompute virtualCompute : queryComputeResponse.getQueryResult()) {
  //      System.out.println(virtualCompute.toString());
  //      System.out.println(virtualCompute.toString());
  //      System.out.println(virtualCompute.toString());
  //      System.out.println(virtualCompute.toString());
  //      System.out.println(virtualCompute.toString());
  //      System.out.println(virtualCompute.toString());
  //      System.out.println(virtualCompute.toString());
  //    }
  //    try {
  //      Thread.sleep(30000);
  //    } catch (InterruptedException e) {
  //      e.printStackTrace();
  //    }
  //    TerminateComputeRequest terminateComputeRequest = new TerminateComputeRequest();
  //    List<String> containers = new ArrayList<>();
  //    containers.add(allocateComputeResponse.getComputeData().getComputeId());
  //    terminateComputeRequest.setComputeId(containers);
  //    TerminateComputeResponse terminateComputeResponse =
  //        dockerAdapter.terminateVirtualisedComputeResource(terminateComputeRequest, poP);
  //    System.out.println(terminateComputeResponse);
  //
  //    AllocateNetworkRequest allocateNetworkRequest = new AllocateNetworkRequest();
  //    allocateNetworkRequest.setNetworkResourceName("test_network");
  //    allocateNetworkRequest.setNetworkResourceType(NetworkResourceType.NETWORK);
  //    VirtualNetworkData virtualNetworkData = new VirtualNetworkData();
  //    NetworkSubnetData networkSubnetData = new NetworkSubnetData();
  //    List<KeyValuePair> metadataNetwork = new ArrayList<>();
  //    metadataNetwork.add(new KeyValuePair("DOCKER_NETWORK_DRIVER", "bridge"));
  //    virtualNetworkData.setMetadata(metadataNetwork);
  //
  //    List<KeyValuePair> metadataSubnet = new ArrayList<>();
  //    metadataSubnet.add(new KeyValuePair("CIDR", "192.168.125.0/24"));
  //    networkSubnetData.setMetadata(metadataSubnet);
  //    IpAddress gatewayIP = new IpAddress();
  //    gatewayIP.setAddress("192.168.125.1");
  //    networkSubnetData.setGatewayIp(gatewayIP);
  //    List<NetworkSubnetData> subnetDataSet = new ArrayList<>();
  //    subnetDataSet.add(networkSubnetData);
  //    //        virtualNetworkData.setLayer3Attributes(subnetDataSet);
  //
  //    allocateNetworkRequest.setTypeNetworkData(virtualNetworkData);
  //
  //    AllocateNetworkResponse allocateNetworkResponse =
  //        dockerAdapter.allocateVirtualisedNetworkResource(allocateNetworkRequest, poP);
  //
  //    List<Predicate> predicatesNetwork = new ArrayList<>();
  //    predicates.add(
  //        virtualNetwork ->
  //            ((VirtualNetwork) virtualNetwork).getNetworkResourceName().matches("test_network"));
  //    Filter filterNetwork = new Filter();
  //    filterNetwork.setPredicates(predicates);
  //    QueryNetworkRequest queryNetworkRequest = new QueryNetworkRequest();
  //    queryNetworkRequest.setQueryNetworkFilter(filterNetwork);
  //    QueryNetworkResponse queryNetworkResponse =
  //        dockerAdapter.queryVirtualisedNetworkResource(queryNetworkRequest, poP);
  //
  //    List<String> networksToRemove = new ArrayList<>();
  //    for (VirtualNetwork network : queryNetworkResponse.getQueryResult()) {
  //      networksToRemove.add(network.getNetworkResourceId());
  //    }
  //    TerminateNetworkRequest terminateNetworkRequest = new TerminateNetworkRequest();
  //    terminateNetworkRequest.setNetworkResourceId(networksToRemove);
  //    dockerAdapter.terminateVirtualisedNetworkResource(terminateNetworkRequest, poP);
  //  }
}
