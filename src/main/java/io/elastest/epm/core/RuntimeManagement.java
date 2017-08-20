package io.elastest.epm.core;

import com.google.common.collect.Lists;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.Network;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import io.elastest.epm.pop.messages.network.AllocateNetworkRequest;
import io.elastest.epm.pop.messages.network.AllocateNetworkResponse;
import io.elastest.epm.pop.messages.network.TerminateNetworkRequest;
import io.elastest.epm.pop.model.network.NetworkResourceType;
import io.elastest.epm.pop.model.network.NetworkSubnet;
import io.elastest.epm.pop.model.network.NetworkSubnetData;
import io.elastest.epm.pop.model.network.VirtualNetworkData;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class RuntimeManagement {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private RuntimeManagmentInterface adapter;

  @Autowired private VduManagement vduManagement;

  @Autowired private PoPManagement poPManagement;

  public InputStream downloadFileFromInstance(String vduId, String filepath) throws AdapterException, NotFoundException {
    log.info("Downloading File from VDU: " + vduId + " -> " + filepath);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    return adapter.downloadFileFromInstance(vdu, filepath, pop);
  }

  public String executeOnInstance(String vduId, String command, boolean awaitCompletion) throws AdapterException, NotFoundException {
    log.info("Executing command on VDU: " + vduId + " -> " + command);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    String output = adapter.executeOnInstance(vdu, command, awaitCompletion, pop);
    log.debug("Output: " + output);
    return output;
  }


  public void startInstance(String vduId) throws AdapterException, NotFoundException {
    log.info("Starting VDU: " + vduId);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    adapter.startInstance(vdu, pop);
  }

  public void stopInstance(String vduId) throws AdapterException, NotFoundException {
    log.info("Stopping VDU: " + vduId);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    adapter.stopInstance(vdu, pop);
  }

  public void uploadFileToInstanceByFile(String vduId, String remotePath, MultipartFile file) throws AdapterException, IOException, NotFoundException, BadRequestException {
    log.info("Uploading file to VDU: " + vduId + " -> " + remotePath);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    if (file == null) {
      throw new BadRequestException("File to upload must be provided.");
    }
    adapter.uploadFileToInstance(vdu, remotePath, file, pop);
  }

  public void uploadFileToInstanceByPath(String vduId, String remotePath, String hostPath) throws AdapterException, IOException, NotFoundException, BadRequestException {
    log.info("Uploading file to VDU: " + vduId + " -> " + remotePath);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    if (hostPath == null) {
      throw new BadRequestException("hostPath must be provided.");
    }
    adapter.uploadFileToInstance(vdu, remotePath, hostPath, pop);
  }

}
