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
import java.util.List;

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

  private OperationHandlerBlockingStub getAnsibleClient() throws NotFoundException {

      return utils.getAdapterClient(utils.getAdapter( "ansible"));
  }

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    PoP ansiblePoP = poPRepository.findOneByName("ansible-dummy");
    OperationHandlerBlockingStub ansibleClient = getAnsibleClient();

    ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));
    FileMessage composePackage = FileMessage.newBuilder().setFile(yamlFile).build();
    ResourceGroupProto rg = ansibleClient.create(composePackage);

    ResourceGroup resourceGroup = utils.parseRGProto(rg, ansiblePoP);

    resourceGroupRepository.save(resourceGroup);
    return resourceGroup;
  }

  @Override
  public ResourceGroup deploy(InputStream data, PoP poP) throws NotFoundException, IOException {

      OperationHandlerBlockingStub ansibleClient = getAnsibleClient();
      ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));
      List<String> options = extractOptions(poP);
      if (options == null) {
          throw new NotFoundException("Couldnt find all required credentials in the interface info of the pop: auth_url, project_name, username, password!");
      }
      FileMessage composePackage = FileMessage.newBuilder()
              .setFile(yamlFile).addAllOptions(options).build();
      ResourceGroupProto rg = ansibleClient.create(composePackage);
      ResourceGroup resourceGroup = utils.parseRGProto(rg, poP);
      resourceGroupRepository.save(resourceGroup);
      return resourceGroup;
  }

  @Override
  public void terminate(String packageId) throws NotFoundException {
    ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);
    OperationHandlerBlockingStub ansibleClient = getAnsibleClient();

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
          throws AdapterException, NotFoundException {
    OperationHandlerBlockingStub client = getAnsibleClient();
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
          throws AdapterException, NotFoundException {
    OperationHandlerBlockingStub client = getAnsibleClient();

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
    OperationHandlerBlockingStub composeClient = getAnsibleClient();
    ResourceIdentifier identifier =
        ResourceIdentifier.newBuilder().setResourceId(vdu.getName()).build();
    composeClient.startContainer(identifier);
  }

  @Override
  public void stopInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {
    PoP ansiblePop = poPRepository.findPoPForType("ansible");
    OperationHandlerBlockingStub composeClient = getAnsibleClient();
    ResourceIdentifier identifier =
        ResourceIdentifier.newBuilder().setResourceId(vdu.getName()).build();
    composeClient.stopContainer(identifier);
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
          throws AdapterException, IOException, NotFoundException {
    OperationHandlerBlockingStub client = getAnsibleClient();

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
          throws AdapterException, IOException, NotFoundException {
    File output = Utils.convert(file);
    output.deleteOnExit();
    OperationHandlerBlockingStub client = getAnsibleClient();
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

  private List<String> extractOptions(PoP ansiblePop){
      String authURL = "";
      String username = "";
      String password = "";
      String project = "";

      for (KeyValuePair kvp : ansiblePop.getInterfaceInfo()){
          switch (kvp.getKey()){
              case "auth_url":
                  authURL = kvp.getValue();
                  break;
              case "username":
                  username = kvp.getValue();
                  break;
              case "password":
                  password = kvp.getValue();
                  break;
              case "project_name":
                  project = kvp.getValue();
                  break;
              default:
                  break;
          }
      }

      if (!authURL.equals("") && !username.equals("") && !password.equals("") && !project.equals("")) {
          List<String> output = new ArrayList<>();
          output.add(authURL);
          output.add(project);
          output.add(username);
          output.add(password);
          return output;
      }
      else return null;
    }
}
