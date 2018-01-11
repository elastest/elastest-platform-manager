package io.elastest.epm.pop.adapter.ansible;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.FileMessage;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.OperationHandlerGrpc.OperationHandlerBlockingStub;
import io.elastest.epm.pop.generated.ResourceGroupProto;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.io.InputStream;
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
  public void terminate(String packageId) throws NotFoundException {}

  @Override
  public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
      throws AdapterException {
    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }

  @Override
  public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop)
      throws AdapterException {
    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }

  @Override
  public void startInstance(VDU vdu, PoP pop) throws AdapterException {
    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }

  @Override
  public void stopInstance(VDU vdu, PoP pop) throws AdapterException {
    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
      throws AdapterException, IOException {
    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }

  @Override
  public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop)
      throws AdapterException, IOException {
    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }
}
