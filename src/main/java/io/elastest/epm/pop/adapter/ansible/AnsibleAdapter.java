package io.elastest.epm.pop.adapter.ansible;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.*;
import io.elastest.epm.pop.generated.OperationHandlerGrpc.OperationHandlerBlockingStub;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
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
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AnsibleAdapter implements PackageManagementInterface, RuntimeManagmentInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private PoPRepository poPRepository;

  @Autowired private NetworkRepository networkRepository;

  @Autowired private VduRepository vduRepository;

  @Autowired private ResourceGroupRepository resourceGroupRepository;

  @Autowired private Utils utils;

  private OperationHandlerBlockingStub getAnsibleClient(PoP poP) {

    ManagedChannelBuilder<?> channelBuilder =
        ManagedChannelBuilder.forAddress(poP.getInterfaceEndpoint(), 50052).usePlaintext(true);
    ManagedChannel channel = channelBuilder.build();
    return OperationHandlerGrpc.newBlockingStub(channel);
  }

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    PoP ansiblePoP = poPRepository.findPoPForType("ansible");
    OperationHandlerBlockingStub ansibleClient = getAnsibleClient(ansiblePoP);

    ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));
    FileMessage composePackage = FileMessage.newBuilder().setFile(yamlFile).build();
    ResourceGroupProto rg = ansibleClient.create(composePackage);

    ResourceGroup resourceGroup = utils.parseRGProto(rg, ansiblePoP);

    resourceGroupRepository.save(resourceGroup);
    return resourceGroup;
  }

  @Override
  public ResourceGroup deploy(InputStream data, PoP poP) throws NotFoundException, IOException {

    log.info("NOT IMPLEMENTED YET: WILL LAUNCH PACKAGE ON RANDOM POP.");
    // TODO: IMPLEMENT PROPERLY + IMPLEMENT PROPER HANDLING OF ANSIBLE POPS
    PoP ansiblePoP = poPRepository.findPoPForType("ansible");
    OperationHandlerBlockingStub ansibleClient = getAnsibleClient(ansiblePoP);

    ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));
    FileMessage composePackage = FileMessage.newBuilder().setFile(yamlFile).build();
    ResourceGroupProto rg = ansibleClient.create(composePackage);

    ResourceGroup resourceGroup = utils.parseRGProto(rg, ansiblePoP);

    resourceGroupRepository.save(resourceGroup);
    return resourceGroup;
  }

  @Override
  public void terminate(String packageId) throws NotFoundException {
    ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);
    PoP composePop = poPRepository.findPoPForType("ansible");
    OperationHandlerBlockingStub ansibleClient = getAnsibleClient(composePop);

    ResourceIdentifier identifier =
        ResourceIdentifier.newBuilder()
            .setResourceId(resourceGroup.getVdus().get(0).getName())
            .build();
    ansibleClient.remove(identifier);

    vduRepository.delete(resourceGroup.getVdus());
    networkRepository.delete(resourceGroup.getNetworks());
    resourceGroupRepository.delete(resourceGroup);
  }

  @Override
  public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
      throws AdapterException {
    OperationHandlerBlockingStub client = getAnsibleClient(pop);
    log.debug("Downloading file");
    RuntimeMessage dockerRuntimeMessage =
        RuntimeMessage.newBuilder()
            .setResourceId(vdu.getIp())
            .addAllProperty(new ArrayList<String>())
            .addProperty(filepath)
            .addProperty("ubuntu")
            .addProperty("")
            .build();
    FileMessage response = client.downloadFile(dockerRuntimeMessage);
    return new ByteArrayInputStream(response.getFile().toByteArray());
  }

  @Override
  public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop)
      throws AdapterException {
    OperationHandlerBlockingStub client = getAnsibleClient(pop);

    RuntimeMessage dockerRuntimeMessage =
        RuntimeMessage.newBuilder()
            .setResourceId(vdu.getIp())
            .addAllProperty(new ArrayList<String>())
            .addProperty(command)
            .addProperty("ubuntu")
            .addProperty("")
            .build();
    StringResponse response = client.executeCommand(dockerRuntimeMessage);
    return response.getResponse();
  }

  @Override
  public void startInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {
    PoP composePop = poPRepository.findPoPForType("ansible");
    OperationHandlerBlockingStub composeClient = getAnsibleClient(composePop);
    ResourceIdentifier identifier =
        ResourceIdentifier.newBuilder().setResourceId(vdu.getName()).build();
    composeClient.startContainer(identifier);
  }

  @Override
  public void stopInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {
    PoP composePop = poPRepository.findPoPForType("ansible");
    OperationHandlerBlockingStub composeClient = getAnsibleClient(composePop);
    ResourceIdentifier identifier =
        ResourceIdentifier.newBuilder().setResourceId(vdu.getName()).build();
    composeClient.stopContainer(identifier);
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
      throws AdapterException, IOException {
    OperationHandlerBlockingStub client = getAnsibleClient(pop);

    RuntimeMessage dockerRuntimeMessage =
        RuntimeMessage.newBuilder()
            .setResourceId(vdu.getIp())
            .addAllProperty(new ArrayList<String>())
            .addProperty("withPath")
            .addProperty("ubuntu")
            .addProperty("")
            .addProperty(hostPath)
            .addProperty(remotePath)
            .build();
    client.uploadFile(dockerRuntimeMessage);
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop)
      throws AdapterException, IOException {
    File output = Utils.convert(file);
    output.deleteOnExit();
    OperationHandlerBlockingStub client = getAnsibleClient(pop);
    if (!file.getOriginalFilename().endsWith(".tar")) {
      output = Utils.compressFileToTar(output);
    }

    RuntimeMessage dockerRuntimeMessage =
        RuntimeMessage.newBuilder()
            .setResourceId(vdu.getIp())
            .addAllProperty(new ArrayList<String>())
            .addProperty(remotePath)
            .addProperty("ubuntu")
            .addProperty("")
            .setFile(ByteString.copyFrom(FileUtils.readFileToByteArray(output)))
            .build();
    client.uploadFile(dockerRuntimeMessage);
    log.debug(String.valueOf("File deletion: " + output.delete()));
  }
}
