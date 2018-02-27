package io.elastest.epm.pop.adapter.compose;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.*;
import io.elastest.epm.pop.generated.OperationHandlerGrpc.OperationHandlerBlockingStub;
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

  @Autowired private Utils utils;

  private OperationHandlerBlockingStub getDockerComposeClient(PoP poP) {

    ManagedChannelBuilder<?> channelBuilder =
        ManagedChannelBuilder.forAddress(poP.getInterfaceEndpoint(), 50051).usePlaintext(true);
    ManagedChannel channel = channelBuilder.build();
    return OperationHandlerGrpc.newBlockingStub(channel);
  }

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    PoP composePoP = poPRepository.findPoPForType("docker-compose");
    OperationHandlerBlockingStub composeClient = getDockerComposeClient(composePoP);

    ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));

    String enabled = "False";
    String address = "";
    if (dockerProperties.getLogStash().isEnabled()) {
      enabled = "True";
      if (dockerProperties.getLogStash().getAddress() != null
          && !dockerProperties.getLogStash().getAddress().equals(""))
        address = dockerProperties.getLogStash().getAddress();
      else address = "tcp://localhost:5000";
    }

    FileMessage composePackage =
        FileMessage.newBuilder()
            .setFile(yamlFile)
            .addAllOptions(new ArrayList<String>())
            .addOptions(enabled)
            .addOptions(address)
            .build();
    ResourceGroupProto rg = composeClient.create(composePackage);

    ResourceGroup resourceGroup = utils.parseRGProto(rg, composePoP);

    resourceGroupRepository.save(resourceGroup);
    return resourceGroup;
  }

  @Override
  public void terminate(String packageId) throws NotFoundException {
    ResourceIdentifier composeIdentifier =
        ResourceIdentifier.newBuilder().setResourceId(packageId).build();

    PoP composePop = poPRepository.findPoPForType("docker-compose");
    OperationHandlerBlockingStub composeClient = getDockerComposeClient(composePop);
    composeClient.remove(composeIdentifier);

    ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);

    vduRepository.delete(resourceGroup.getVdus());
    networkRepository.delete(resourceGroup.getNetworks());
    resourceGroupRepository.delete(resourceGroup);
  }

  @Override
  public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
      throws AdapterException {
    if (isRunning(vdu.getComputeId(), pop)) {
      OperationHandlerBlockingStub client = getDockerComposeClient(pop);
      log.debug("Downloading file");
      RuntimeMessage dockerRuntimeMessage =
          RuntimeMessage.newBuilder()
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
      OperationHandlerBlockingStub client = getDockerComposeClient(pop);

      RuntimeMessage dockerRuntimeMessage =
          RuntimeMessage.newBuilder()
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
      OperationHandlerBlockingStub client = getDockerComposeClient(pop);
      ResourceIdentifier resourceIdentifier =
          ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId()).build();
      client.startContainer(resourceIdentifier);
    }
  }

  @Override
  public void stopInstance(VDU vdu, PoP pop) throws AdapterException {

    if (isRunning(vdu.getComputeId(), pop)) {
      OperationHandlerBlockingStub client = getDockerComposeClient(pop);
      ResourceIdentifier resourceIdentifier =
          ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId()).build();
      client.stopContainer(resourceIdentifier);
    }
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
      throws AdapterException, IOException {
    if (isRunning(vdu.getComputeId(), pop)) {
      OperationHandlerBlockingStub client = getDockerComposeClient(pop);

      RuntimeMessage dockerRuntimeMessage =
          RuntimeMessage.newBuilder()
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
      OperationHandlerBlockingStub client = getDockerComposeClient(pop);
      if (!file.getOriginalFilename().endsWith(".tar")) {
        output = Utils.compressFileToTar(output);
      }

      RuntimeMessage dockerRuntimeMessage =
          RuntimeMessage.newBuilder()
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
    OperationHandlerBlockingStub client = getDockerComposeClient(pop);
    ResourceIdentifier resourceIdentifier =
        ResourceIdentifier.newBuilder().setResourceId(containerId).build();
    StringResponse stringResponse = client.checkIfContainerExists(resourceIdentifier);
    return Boolean.parseBoolean(stringResponse.getResponse().toLowerCase());
  }

  private boolean isRunning(String containerId, PoP pop) {
    OperationHandlerBlockingStub client = getDockerComposeClient(pop);
    ResourceIdentifier resourceIdentifier =
        ResourceIdentifier.newBuilder().setResourceId(containerId).build();
    StringResponse stringResponse = client.checkIfContainerRunning(resourceIdentifier);
    return Boolean.parseBoolean(stringResponse.getResponse().toLowerCase());
  }
}
