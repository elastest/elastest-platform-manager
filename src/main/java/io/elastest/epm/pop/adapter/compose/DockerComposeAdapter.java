package io.elastest.epm.pop.adapter.compose;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.ComposeHandlerGrpc;
import io.elastest.epm.pop.generated.ComposeHandlerGrpc.ComposeHandlerBlockingStub;
import io.elastest.epm.pop.generated.DockerRuntimeMessage;
import io.elastest.epm.pop.generated.FileMessage;
import io.elastest.epm.pop.generated.ResourceGroupCompose;
import io.elastest.epm.pop.generated.ResourceIdentifier;
import io.elastest.epm.pop.generated.StringResponse;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DockerComposeAdapter implements PackageManagementInterface, RuntimeManagmentInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private PoPRepository poPRepository;

  @Autowired private NetworkRepository networkRepository;

  @Autowired private VduRepository vduRepository;

  @Autowired private ResourceGroupRepository resourceGroupRepository;

  @Autowired private DockerProperties dockerProperties;

  private ComposeHandlerBlockingStub getDockerComposeClient(PoP poP) {

    ManagedChannelBuilder<?> channelBuilder =
        ManagedChannelBuilder.forAddress(poP.getInterfaceEndpoint(), 50051).usePlaintext(true);
    ManagedChannel channel = channelBuilder.build();
    return ComposeHandlerGrpc.newBlockingStub(channel);
  }

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    PoP composePoP = poPRepository.findPoPForType("docker-compose");
    ComposeHandlerBlockingStub composeClient = getDockerComposeClient(composePoP);

    ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));
    FileMessage composePackage = FileMessage.newBuilder().setFile(yamlFile).build();
    ResourceGroupCompose rg = composeClient.upCompose(composePackage);

    ResourceGroup resourceGroup = new ResourceGroup();
    resourceGroup.setName(rg.getName());

    for (ResourceGroupCompose.NetworkCompose networkCompose : rg.getNetworksList()) {
      Network network = new Network();
      network.setName(networkCompose.getName());
      network.setCidr(networkCompose.getCidr());
      network.setPoPName(composePoP.getName());
      network.setNetworkId(networkCompose.getNetworkId());
      networkRepository.save(network);
      resourceGroup.addNetworksItem(network);
    }

    for (ResourceGroupCompose.VDUCompose vduCompose : rg.getVdusList()) {

      VDU vdu = new VDU();
      vdu.setName(vduCompose.getName());
      vdu.setImageName(vduCompose.getImageName());
      vdu.setComputeId(vduCompose.getComputeId());
      vdu.setNetName(vduCompose.getNetName());
      vdu.setPoPName(composePoP.getName());
      vdu.setIp(vduCompose.getIp());
      for (ResourceGroupCompose.MetadataEntryCompose metadataEntryCompose :
          vduCompose.getMetadataList()) {
        KeyValuePair kvp =
            new KeyValuePair(metadataEntryCompose.getKey(), metadataEntryCompose.getValue());
        vdu.addMetadataItem(kvp);
      }
      vduRepository.save(vdu);
      resourceGroup.addVdusItem(vdu);
    }

    resourceGroupRepository.save(resourceGroup);
    return resourceGroup;
  }

  @Override
  public void terminate(String packageId) throws NotFoundException {
    ResourceIdentifier composeIdentifier =
        ResourceIdentifier.newBuilder().setResourceId(packageId).build();

    PoP composePop = poPRepository.findPoPForType("docker-compose");
    ComposeHandlerBlockingStub composeClient = getDockerComposeClient(composePop);
    composeClient.removeCompose(composeIdentifier);

    ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);

    vduRepository.delete(resourceGroup.getVdus());
    networkRepository.delete(resourceGroup.getNetworks());
    resourceGroupRepository.delete(resourceGroup);
  }

  @Override
  public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
      throws AdapterException {
    if (isRunning(vdu.getComputeId(), pop)) {
      ComposeHandlerBlockingStub client = getDockerComposeClient(pop);
      log.debug("Downloading file");
      DockerRuntimeMessage dockerRuntimeMessage =
          DockerRuntimeMessage.newBuilder()
              .setResourceId(vdu.getComputeId())
              .addAllProperty(new ArrayList<String>())
              .addProperty(filepath)
              .build();
      FileMessage response = client.downloadFile(dockerRuntimeMessage);
      return new ByteArrayInputStream(response.getFile().toByteArray());
    } else throw new AdapterException("Can't download file from stopped container.");
  }

  @Override
  public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop)
      throws AdapterException {
    if (isRunning(vdu.getComputeId(), pop)) {
      ComposeHandlerBlockingStub client = getDockerComposeClient(pop);

      DockerRuntimeMessage dockerRuntimeMessage =
          DockerRuntimeMessage.newBuilder()
              .setResourceId(vdu.getComputeId())
              .addAllProperty(new ArrayList<String>())
              .addProperty(command)
              .build();
      StringResponse response = client.executeCommand(dockerRuntimeMessage);
      return response.getResponse();
    } else throw new AdapterException("Can't execute command on stopped container.");
  }

  @Override
  public void startInstance(VDU vdu, PoP pop) throws AdapterException {

    if (existsContainer(vdu.getComputeId(), pop) && !isRunning(vdu.getComputeId(), pop)) {
      ComposeHandlerBlockingStub client = getDockerComposeClient(pop);
      ResourceIdentifier resourceIdentifier =
          ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId()).build();
      client.startContainer(resourceIdentifier);
    }
  }

  @Override
  public void stopInstance(VDU vdu, PoP pop) throws AdapterException {

    if (isRunning(vdu.getComputeId(), pop)) {
      ComposeHandlerBlockingStub client = getDockerComposeClient(pop);
      ResourceIdentifier resourceIdentifier =
          ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId()).build();
      client.stopContainer(resourceIdentifier);
    }
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
      throws AdapterException, IOException {
    if (isRunning(vdu.getComputeId(), pop)) {
      ComposeHandlerBlockingStub client = getDockerComposeClient(pop);

      DockerRuntimeMessage dockerRuntimeMessage =
          DockerRuntimeMessage.newBuilder()
              .setResourceId(vdu.getComputeId())
              .addAllProperty(new ArrayList<String>())
              .addProperty("withPath")
              .addProperty(hostPath)
              .addProperty(remotePath)
              .build();
      client.uploadFile(dockerRuntimeMessage);
    } else throw new AdapterException("Can't upload a file to a stopped container.");
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop)
      throws AdapterException, IOException {
    if (isRunning(vdu.getComputeId(), pop)) {
      File output = Utils.convert(file);
      output.deleteOnExit();
      ComposeHandlerBlockingStub client = getDockerComposeClient(pop);
      if (!file.getOriginalFilename().endsWith(".tar")) {
        output = Utils.compressFileToTar(output);
      }

      DockerRuntimeMessage dockerRuntimeMessage =
          DockerRuntimeMessage.newBuilder()
              .setResourceId(vdu.getComputeId())
              .addAllProperty(new ArrayList<String>())
              .addProperty(remotePath)
              .setFile(ByteString.copyFrom(FileUtils.readFileToByteArray(output)))
              .build();
      client.uploadFile(dockerRuntimeMessage);
      log.debug(String.valueOf("File deletion: " + output.delete()));
    } else throw new AdapterException("Can't upload a file to a stopped container.");
  }

  private boolean existsContainer(String containerId, PoP pop) {
    ComposeHandlerBlockingStub client = getDockerComposeClient(pop);
    ResourceIdentifier resourceIdentifier =
        ResourceIdentifier.newBuilder().setResourceId(containerId).build();
    StringResponse stringResponse = client.checkIfContainerExists(resourceIdentifier);
    return Boolean.parseBoolean(stringResponse.getResponse().toLowerCase());
  }

  private boolean isRunning(String containerId, PoP pop) {
    ComposeHandlerBlockingStub client = getDockerComposeClient(pop);
    ResourceIdentifier resourceIdentifier =
        ResourceIdentifier.newBuilder().setResourceId(containerId).build();
    StringResponse stringResponse = client.checkIfContainerRunning(resourceIdentifier);
    return Boolean.parseBoolean(stringResponse.getResponse().toLowerCase());
  }
}
