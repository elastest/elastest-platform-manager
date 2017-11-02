package io.elastest.epm.pop.adapter.compose;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.compose.generated.ComposeHandlerGrpc;
import io.elastest.epm.pop.adapter.compose.generated.ComposeHandlerGrpc.ComposeHandlerBlockingStub;
import io.elastest.epm.pop.adapter.compose.generated.ComposeIdentifier;
import io.elastest.epm.pop.adapter.compose.generated.ComposePackage;
import io.elastest.epm.pop.adapter.compose.generated.ResourceGroupCompose;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DockerComposeAdapter implements PackageManagementInterface {

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

  private PoP findComposePoP() throws NotFoundException {
    PoP composePoP = null;
    Iterable<PoP> pops = poPRepository.findAll();
    for (PoP pop : pops) {
      for (KeyValuePair kvp : pop.getInterfaceInfo()) {
        if (kvp.getKey().equals("type") && kvp.getValue().equals("docker-compose")) {
          composePoP = pop;
          break;
        }
      }
    }

    if (composePoP == null || composePoP.getInterfaceEndpoint() == null)
      throw new NotFoundException("No docker-compose pop was registered!");
    return composePoP;
  }

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    PoP composePoP = findComposePoP();
    ComposeHandlerBlockingStub composeClient = getDockerComposeClient(composePoP);

    ByteString yamlFile = ByteString.copyFrom(IOUtils.toByteArray(data));
    ComposePackage composePackage = ComposePackage.newBuilder().setComposeFile(yamlFile).build();
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
    ComposeIdentifier composeIdentifier =
        ComposeIdentifier.newBuilder().setComposeId(packageId).build();

    PoP composePop = findComposePoP();
    ComposeHandlerBlockingStub composeClient = getDockerComposeClient(composePop);
    composeClient.removeCompose(composeIdentifier);

    ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);

    vduRepository.delete(resourceGroup.getVdus());
    networkRepository.delete(resourceGroup.getNetworks());
    resourceGroupRepository.delete(resourceGroup);
  }
}
