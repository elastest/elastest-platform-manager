package io.elastest.epm.core;

import com.google.common.collect.Lists;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.Network;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.VirtualisedNetworkResourceManagementInterface;
import io.elastest.epm.pop.messages.network.AllocateNetworkRequest;
import io.elastest.epm.pop.messages.network.AllocateNetworkResponse;
import io.elastest.epm.pop.messages.network.TerminateNetworkRequest;
import io.elastest.epm.pop.model.network.*;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NetworkManagement {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private VirtualisedNetworkResourceManagementInterface adapter;

  @Autowired private NetworkRepository networkRepository;

  @Autowired private PoPRepository poPRepository;

  public Network createNetwork(Network network)
      throws AdapterException, BadRequestException, NotFoundException {
    log.info("Creating new Network: " + network);
    for (Network networkToCheck : networkRepository.findByName(network.getName())) {
      if (networkToCheck.getPoPName().equals(network.getPoPName())) {
        log.warn(
            "Network "
                + network.getName()
                + " exists already managed by PoP "
                + network.getPoPName());
                throw new BadRequestException(
                    "Network "
                        + network.getName()
                        + " exists already managed by PoP "
                        + network.getPoPName());
//        return networkToCheck;
      }
    }

    PoP poP = poPRepository.findOneByName(network.getPoPName());
    if (poP == null) {
      log.error("Not found PoP " + network.getPoPName());
      throw new NotFoundException("Not found PoP " + network.getPoPName());
    }

    AllocateNetworkRequest allocateNetworkRequest = new AllocateNetworkRequest();
    allocateNetworkRequest.setNetworkResourceType(NetworkResourceType.NETWORK);
    allocateNetworkRequest.setNetworkResourceName(network.getName());
//    allocateNetworkRequest.setNetworkResourceName(network.getName() + "-" + (int) (Math.random() * 1000000));
    VirtualNetworkData virtualNetworkData = new VirtualNetworkData();
    List<KeyValuePair> metadataNetwork = new ArrayList<>();
    //        metadata.add(new KeyValuePair("DOCKER_NETWORK_DRIVER", "bridge"));
    virtualNetworkData.setMetadata(metadataNetwork);
    List<NetworkSubnetData> networkSubnetDatas = new ArrayList<>();
    NetworkSubnetData networkSubnetData = new NetworkSubnetData();
    List<KeyValuePair> metadataSubnet = new ArrayList<>();
    if (network.getCidr() != null && !network.getCidr().isEmpty())
      metadataSubnet.add(new KeyValuePair("CIDR", network.getCidr()));
    networkSubnetData.setMetadata(metadataSubnet);
    networkSubnetDatas.add(networkSubnetData);
    virtualNetworkData.setLayer3Attributes(networkSubnetDatas);
    allocateNetworkRequest.setTypeNetworkData(virtualNetworkData);
    AllocateNetworkResponse allocateNetworkResponse =
        adapter.allocateVirtualisedNetworkResource(allocateNetworkRequest, poP);
    network.setNetworkId(allocateNetworkResponse.getNetworkData().getNetworkResourceId());
    for (NetworkSubnet subnet : allocateNetworkResponse.getNetworkData().getSubnet()) {
      for (KeyValuePair keyValuePair : subnet.getMetadata()) {
        if (keyValuePair.getKey().equals("CIDR")) {
          network.setCidr(keyValuePair.getValue());
          break;
        }
      }
    }
    networkRepository.save(network);
    log.info("Created Network: " + network);
    return network;
  }

  public void deleteNetwork(String networkId) throws AdapterException {
    log.info("Deleting Network: " + networkId);
    Network network = networkRepository.findOne(networkId);
    PoP poP = poPRepository.findOneByName(network.getPoPName());
    TerminateNetworkRequest terminateNetworkRequest = new TerminateNetworkRequest();
    List<String> networkIds = new ArrayList<>();
    networkIds.add(network.getNetworkId());
    terminateNetworkRequest.setNetworkResourceId(networkIds);
    adapter.terminateVirtualisedNetworkResource(terminateNetworkRequest, poP);
    networkRepository.delete(networkId);
    log.info("Deleted Network: " + networkId);
  }

  public Network updateNetwork(String networkId, Network network) {
    log.info("Updating Network: " + networkId);
    //        network.setId(networkId);
    //        networkRepository.save(network);
    log.info("Updated Network: " + network);
    throw new UnsupportedOperationException();
  }

  public List<Network> getAllNetworks() {
    log.info("Listing all Networks...");
    List<Network> allNetworks = Lists.newArrayList(networkRepository.findAll());
    log.info("Listed all Networks: " + allNetworks);
    return allNetworks;
  }

  public Network getNetworkById(String id) {
    log.info("Getting network: " + id);
    Network network = networkRepository.findOne(id);
    log.info("Got Network: " + network);
    return network;
  }
}
