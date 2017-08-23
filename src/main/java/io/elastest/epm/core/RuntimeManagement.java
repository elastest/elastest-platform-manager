package io.elastest.epm.core;

import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Event;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

import io.elastest.epm.repository.VduRepository;
import javafx.animation.Animation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RuntimeManagement {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private RuntimeManagmentInterface adapter;

  @Autowired private VduManagement vduManagement;

  @Autowired private VduRepository vduRepository;

  @Autowired private PoPManagement poPManagement;

  public InputStream downloadFileFromInstance(String vduId, String filepath)
      throws AdapterException, NotFoundException {
    log.info("Downloading File from VDU: " + vduId + " -> " + filepath);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    vdu.getEvents().add(createEvent("Downloading file: " + filepath));
    vdu = vduRepository.save(vdu);
    InputStream inputStream = adapter.downloadFileFromInstance(vdu, filepath, pop);
    vdu.getEvents().add(createEvent("Downloaded file: " + filepath));
    vduRepository.save(vdu);
    return inputStream;
  }

  public String executeOnInstance(String vduId, String command, boolean awaitCompletion)
      throws AdapterException, NotFoundException {
    log.info("Executing command on VDU: " + vduId + " -> " + command);
    VDU vdu = vduManagement.getVduById(vduId);
    vdu.getEvents().add(createEvent("Executing command: " + command));
    vdu = vduRepository.save(vdu);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    String output = adapter.executeOnInstance(vdu, command, awaitCompletion, pop);
    vdu.getEvents().add(createEvent("Executed command: " + command));
    log.debug("Output: " + output);
    vduRepository.save(vdu);
    return output;
  }

  public void startInstance(String vduId) throws AdapterException, NotFoundException {
    log.info("Starting VDU: " + vduId);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    vdu.getEvents().add(createEvent("Starting"));
    vdu = vduRepository.save(vdu);
    adapter.startInstance(vdu, pop);
    vdu.setStatus(VDU.StatusEnum.RUNNING);
    vdu.getEvents().add(createEvent("Started"));
    vduRepository.save(vdu);
  }

  public void stopInstance(String vduId) throws AdapterException, NotFoundException {
    log.info("Stopping VDU: " + vduId);
    VDU vdu = vduManagement.getVduById(vduId);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    vdu.getEvents().add(createEvent("Stopping"));
    vdu = vduRepository.save(vdu);
    adapter.stopInstance(vdu, pop);
    vdu.setStatus(VDU.StatusEnum.DEPLOYED);
    vdu.getEvents().add(createEvent("Stopped"));
    vduRepository.save(vdu);
  }

  public void uploadFileToInstanceByFile(String vduId, String remotePath, MultipartFile file)
      throws AdapterException, IOException, NotFoundException, BadRequestException {
    log.info("Uploading file to VDU: " + vduId + " -> " + remotePath);
    VDU vdu = vduManagement.getVduById(vduId);
    vdu.getEvents().add(createEvent("Uploading file " + file.getOriginalFilename() + " to " + remotePath));
    vdu = vduRepository.save(vdu);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    if (file == null) {
      throw new BadRequestException("File to upload must be provided.");
    }
    adapter.uploadFileToInstance(vdu, remotePath, file, pop);
    vdu.getEvents().add(createEvent("Uploaded file " + file.getOriginalFilename() + " to " + remotePath));
    vduRepository.save(vdu);
  }

  public void uploadFileToInstanceByPath(String vduId, String remotePath, String hostPath)
      throws AdapterException, IOException, NotFoundException, BadRequestException {
    log.info("Uploading file to VDU: " + vduId + " -> " + remotePath);
    VDU vdu = vduManagement.getVduById(vduId);
    vdu.getEvents().add(createEvent("Uploading file from " + hostPath + " to " + remotePath));
    vdu = vduRepository.save(vdu);
    PoP pop = poPManagement.getPoPByName(vdu.getPoPName());
    if (hostPath == null) {
      throw new BadRequestException("hostPath must be provided.");
    }
    adapter.uploadFileToInstance(vdu, remotePath, hostPath, pop);
    vdu.getEvents().add(createEvent("Uploaded file from " + hostPath + " to " + remotePath));
    vduRepository.save(vdu);
  }

  private Event createEvent(String desc) {
    Event event = new Event();
    event.description(desc);
    event.setTimestamp(Long.toString(System.currentTimeMillis()));
    return event;
  }

}
