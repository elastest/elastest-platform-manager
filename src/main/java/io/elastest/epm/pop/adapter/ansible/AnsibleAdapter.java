package io.elastest.epm.pop.adapter.ansible;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.ComposeHandlerGrpc;
import io.elastest.epm.pop.generated.FileMessage;
import io.elastest.epm.pop.generated.ResourceGroupCompose;
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

  private ComposeHandlerGrpc.ComposeHandlerBlockingStub getAnsibleClient(PoP poP) {

    ManagedChannelBuilder<?> channelBuilder =
        ManagedChannelBuilder.forAddress(poP.getInterfaceEndpoint(), 50052).usePlaintext(true);
    ManagedChannel channel = channelBuilder.build();
    return ComposeHandlerGrpc.newBlockingStub(channel);
  }

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    PoP composePoP = poPRepository.findPoPForType("ansible");
    ComposeHandlerGrpc.ComposeHandlerBlockingStub composeClient = getAnsibleClient(composePoP);

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
