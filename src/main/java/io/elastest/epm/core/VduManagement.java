package io.elastest.epm.core;

import com.google.common.collect.Lists;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.Network;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.messages.compute.*;
import io.elastest.epm.pop.model.network.IpAddress;
import io.elastest.epm.pop.model.network.VirtualNetworkInterface;
import io.elastest.epm.pop.model.network.VirtualNetworkInterfaceData;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.VduRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VduManagement {

  @Autowired private DockerAdapter dockerAdapter;

  @Autowired private VduRepository vduRepository;

  @Autowired private PoPRepository poPRepository;

  @Autowired private NetworkRepository networkRepository;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  public VDU deployVdu(VDU vdu) throws AllocationException, NotFoundException {
    log.info("Deploying VDU: " + vdu);
    log.debug("Check if PoP exists...");
    PoP poP = poPRepository.findOneByName(vdu.getPoPName());
    if (poP == null) {
      log.error("Not found PoP " + vdu.getPoPName());
      throw new NotFoundException("Not found PoP " + vdu.getPoPName());
    }
    log.debug("Check if network exists...");
    Network network = networkRepository.findOneByName(vdu.getNetName());
    if (network == null) {
      log.error("Not found network " + vdu.getNetName());
      throw new NotFoundException("Not found network " + vdu.getNetName());
    }
    vdu.setStatus(VDU.StatusEnum.INITIALIZING);
    vdu.setComputeId("null");
    vdu.setIp("null");
    vduRepository.save(vdu);

    //Allocating computing resources
    AllocateComputeRequest allocateComputeRequest = new AllocateComputeRequest();
    allocateComputeRequest.setComputeName(vdu.getName() + "-" + (int) (Math.random() * 1000000));
    allocateComputeRequest.setVcImageId(vdu.getImageName());
    allocateComputeRequest.setMetaData(vdu.getMetadata());
    vdu.setStatus(VDU.StatusEnum.INITIALIZED);
    vdu.setStatus(VDU.StatusEnum.DEPLOYING);
    AllocateComputeResponse allocateComputeResponse;
    try {
      log.info("Allocating compute resources: " + allocateComputeRequest);
      allocateComputeResponse =
          dockerAdapter.allocateVirtualisedComputeResource(allocateComputeRequest, poP);
      log.info("Allocated compute resources: " + allocateComputeResponse);
    } catch (AdapterException exc) {
      vdu.setStatus(VDU.StatusEnum.ERROR);
      if (exc.getComputeId() != null) vdu.setComputeId(exc.getComputeId());
      vduRepository.save(vdu);
      log.error(exc.getMessage(), exc);
      throw new AllocationException(exc.getMessage());
    }

    vdu.setStatus(VDU.StatusEnum.DEPLOYED);
    vdu.setName(allocateComputeResponse.getComputeData().getComputeName());
    vdu.setImageName(allocateComputeResponse.getComputeData().getVcImageId());
    vdu.setComputeId(allocateComputeResponse.getComputeData().getComputeId());
    vdu.getMetadata()
            .addAll(
                    allocateComputeResponse.getComputeData().getMetadata() != null
                            ? allocateComputeResponse.getComputeData().getMetadata()
                            : new ArrayList<KeyValuePair>());

    //Attaching computing resource to network resource
    UpdateComputeRequest updateComputeRequest = new UpdateComputeRequest();
    updateComputeRequest.setComputeId(allocateComputeResponse.getComputeData().getComputeId());
    List<VirtualNetworkInterfaceData> virtualNetworkInterfaceDataList = new ArrayList<>();
    VirtualNetworkInterfaceData virtualNetworkInterfaceData = new VirtualNetworkInterfaceData();
    virtualNetworkInterfaceData.setNetworkId(network.getNetworkId());
    virtualNetworkInterfaceDataList.add(virtualNetworkInterfaceData);
    updateComputeRequest.setNetworkInterfaceNew(virtualNetworkInterfaceDataList);
    UpdateComputeResponse updateComputeResponse;
    try {
      log.info("Connecting compute resources to network : " + updateComputeRequest);
      updateComputeResponse =
          dockerAdapter.updateVirtualisedComputeResource(updateComputeRequest, poP);
      log.info("Connected compute resources to network : " + updateComputeResponse);
    } catch (AdapterException exc) {
      vdu.setStatus(VDU.StatusEnum.ERROR);
      if (exc.getComputeId() != null) vdu.setComputeId(exc.getComputeId());
      vduRepository.save(vdu);
      log.error(exc.getMessage(), exc);
      throw new AllocationException(exc.getMessage());
    }

    for (VirtualNetworkInterface virtualNetworkInterface :
        updateComputeResponse.getComputeData().getVirtualNetworkInterface()) {
      log.debug("VirtualNetworkInterface: " + virtualNetworkInterface);
      for (IpAddress ipAddress : virtualNetworkInterface.getIpAddress()) {
        vdu.setIp(ipAddress.getAddress());
      }
      vdu.setNetName(vdu.getNetName());
      vdu.getMetadata().addAll(virtualNetworkInterface.getMetadata());
    }
    vdu.setPoPName(vdu.getPoPName());
    vduRepository.save(vdu);
    log.info("Deployed VDU: " + vdu);
    return vdu;
  }

  public void terminateVdu(String id) throws TerminationException, NotFoundException {
    log.info("Terminating VDU: " + id);
    VDU vdu = vduRepository.findOne(id);
    log.debug("Check if VDU exists...");
    if (vdu == null) {
      log.error("Not found VDU " + vdu.getName());
      throw new NotFoundException("Not found VDU " + vdu.getName());
    }
    vdu.setStatus(VDU.StatusEnum.UNDEPLOYING);
    PoP poP = poPRepository.findOneByName(vdu.getPoPName());
    String computeId = vdu.getComputeId();
    TerminateComputeRequest terminateComputeRequest = new TerminateComputeRequest();
    List<String> vduIds = new ArrayList<>();
    vduIds.add(computeId);
    terminateComputeRequest.setComputeId(vduIds);
    try {
      dockerAdapter.terminateVirtualisedComputeResource(terminateComputeRequest, poP);
    } catch (Exception exc) {
      vdu.setStatus(VDU.StatusEnum.ERROR);
      vduRepository.save(vdu);
      log.error(exc.getMessage(), exc);
      throw new TerminationException(exc.getMessage());
    }
    vdu.setStatus(VDU.StatusEnum.UNDEPLOYED);
    vduRepository.delete(vdu);
    log.info("Terminated VDU: " + vdu);
  }

  public List<VDU> getAllVdus() {
    log.info("Listing all VDUs");
    List<VDU> allVdus = Lists.newArrayList(vduRepository.findAll());
    log.info("Listed all VDUs: " + allVdus);
    return allVdus;
  }

  public VDU getVduById(String id) {
    log.info("Get VDU:" + id);
    VDU vdu = vduRepository.findOne(id);
    log.info("Got VDU:" + vdu);
    return vdu;
  }
}
