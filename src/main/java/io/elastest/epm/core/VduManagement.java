package io.elastest.epm.core;

import com.google.common.collect.Lists;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.*;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.VirtualisedComputeResourcesManagmentInterface;
import io.elastest.epm.pop.messages.compute.*;
import io.elastest.epm.pop.model.network.IpAddress;
import io.elastest.epm.pop.model.network.VirtualNetworkInterface;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.VduRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class VduManagement {

  @Autowired private VirtualisedComputeResourcesManagmentInterface adapter;

  @Autowired private VduRepository vduRepository;

  @Autowired private PoPRepository poPRepository;

  @Autowired private NetworkRepository networkRepository;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  public VDU deployVdu(VDU vdu) throws AllocationException, NotFoundException {
    vdu.setId(null);
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
    vdu.getEvents().add(createEvent("INITIALIZING"));
    vdu.setComputeId("null");
    vdu.setIp("null");
    vdu = vduRepository.save(vdu);

    //Allocating computing resources
    AllocateComputeRequest allocateComputeRequest = new AllocateComputeRequest();
    allocateComputeRequest.setComputeName(vdu.getName() + "-" + (int) (Math.random() * 1000000));
    allocateComputeRequest.setVcImageId(vdu.getImageName());
    allocateComputeRequest.setMetaData(new ArrayList<KeyValuePair>());
    for (KeyValuePair keyValuePair : vdu.getMetadata()) {
      allocateComputeRequest.getMetaData().add(keyValuePair);
    }
    KeyValuePair networkMetadata = new KeyValuePair();
    networkMetadata.setKey("NETWORK");
    networkMetadata.setValue(vdu.getNetName());
    allocateComputeRequest.getMetaData().add(networkMetadata);
    vdu.setStatus(VDU.StatusEnum.INITIALIZED);
    vdu.getEvents().add(createEvent("INITIALIZED"));
    vdu.setStatus(VDU.StatusEnum.DEPLOYING);
    vdu.getEvents().add(createEvent("DEPLOYED"));
    AllocateComputeResponse allocateComputeResponse;
    try {
      log.info("Allocating compute resources: " + allocateComputeRequest);
      allocateComputeResponse =
          adapter.allocateVirtualisedComputeResource(allocateComputeRequest, poP);
      log.info("Allocated compute resources: " + allocateComputeResponse);
    } catch (AdapterException exc) {
      vdu.setStatus(VDU.StatusEnum.ERROR);
      vdu.getEvents().add(createEvent("ERROR -> " + exc.getMessage()));
      if (exc.getComputeId() != null) vdu.setComputeId(exc.getComputeId());
      vduRepository.save(vdu);
      log.error(exc.getMessage(), exc);
      throw new AllocationException(exc.getMessage());
    }

    vdu.setStatus(VDU.StatusEnum.RUNNING);
    vdu.getEvents().add(createEvent("RUNNING"));
    //    vdu.setName(allocateComputeResponse.getComputeData().getComputeName());
    vdu.setImageName(allocateComputeResponse.getComputeData().getVcImageId());
    vdu.setComputeId(allocateComputeResponse.getComputeData().getComputeId());
    for (VirtualNetworkInterface virtualNetworkInterface : allocateComputeResponse.getComputeData().getVirtualNetworkInterface()) {
      for (IpAddress ipAddress : virtualNetworkInterface.getIpAddress()) {
        if (ipAddress.getAddress()!=null) {
          vdu.setIp(ipAddress.getAddress());
          break;
        }
      }
    }
//    vdu.getMetadata().addAll(allocateComputeResponse.getComputeData().getMetadata() != null ? allocateComputeResponse.getComputeData().getMetadata() : new ArrayList<KeyValuePair>());

    //Attaching computing resource to network resource
    //    UpdateComputeRequest updateComputeRequest = new UpdateComputeRequest();
    //    updateComputeRequest.setComputeId(allocateComputeResponse.getComputeData().getComputeId());
    //    List<VirtualNetworkInterfaceData> virtualNetworkInterfaceDataList = new ArrayList<>();
    //    VirtualNetworkInterfaceData virtualNetworkInterfaceData = new VirtualNetworkInterfaceData();
    //    virtualNetworkInterfaceData.setNetworkId(network.getNetworkId());
    //    virtualNetworkInterfaceDataList.add(virtualNetworkInterfaceData);
    //    updateComputeRequest.setNetworkInterfaceNew(virtualNetworkInterfaceDataList);
    //    UpdateComputeResponse updateComputeResponse;
    //    try {
    //      log.info("Connecting compute resources to network : " + updateComputeRequest);
    //      updateComputeResponse =
    //          adapter.updateVirtualisedComputeResource(updateComputeRequest, poP);
    //      log.info("Connected compute resources to network : " + updateComputeResponse);
    //    } catch (AdapterException exc) {
    //      vdu.setStatus(VDU.StatusEnum.ERROR);
    //      if (exc.getComputeId() != null) vdu.setComputeId(exc.getComputeId());
    //      vduRepository.save(vdu);
    //      log.error(exc.getMessage(), exc);
    //      throw new AllocationException(exc.getMessage());
    //    }
    //
    //    for (VirtualNetworkInterface virtualNetworkInterface :
    //        updateComputeResponse.getComputeData().getVirtualNetworkInterface()) {
    //      log.debug("VirtualNetworkInterface: " + virtualNetworkInterface);
    //      for (IpAddress ipAddress : virtualNetworkInterface.getIpAddress()) {
    //        vdu.setIp(ipAddress.getAddress());
    //      }
    //      vdu.setNetName(vdu.getNetName());
    ////      vdu.getMetadata().addAll(virtualNetworkInterface.getMetadata());
    //    }
    vdu.setPoPName(vdu.getPoPName());
    vdu = vduRepository.save(vdu);
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
    vdu.getEvents().add(createEvent("UNDEPLOYING"));
    PoP poP = poPRepository.findOneByName(vdu.getPoPName());
    String computeId = vdu.getComputeId();
    TerminateComputeRequest terminateComputeRequest = new TerminateComputeRequest();
    List<String> vduIds = new ArrayList<>();
    vduIds.add(computeId);
    terminateComputeRequest.setComputeId(vduIds);
    try {
      adapter.terminateVirtualisedComputeResource(terminateComputeRequest, poP);
    } catch (Exception exc) {
      vdu.setStatus(VDU.StatusEnum.ERROR);
      vdu.getEvents().add(createEvent("ERROR -> " + exc.getMessage()));
      vduRepository.save(vdu);
      log.error(exc.getMessage(), exc);
      throw new TerminationException(exc.getMessage());
    }
    vdu.setStatus(VDU.StatusEnum.UNDEPLOYED);
    vdu.getEvents().add(createEvent("UNDEPLOYED"));
    vduRepository.delete(vdu);
    log.info("Terminated VDU: " + vdu);
  }

  public List<VDU> getAllVdus() {
    log.info("Listing all VDUs");
    List<VDU> allVdus = Lists.newArrayList(vduRepository.findAll());
    log.info("Listed all VDUs: " + allVdus);
    return allVdus;
  }

  public VDU getVduById(String id) throws NotFoundException {
    log.info("Get VDU: " + id);
    VDU vdu = vduRepository.findOne(id);
    if (vdu == null) throw new NotFoundException("Not found VDU " + id);
    log.info("Got VDU: " + vdu);
    return vdu;
  }

  private Event createEvent(String desc) {
    Event event = new Event();
    event.description(desc);
    event.setTimestamp(Long.toString(System.currentTimeMillis()));
    return event;
  }
}
