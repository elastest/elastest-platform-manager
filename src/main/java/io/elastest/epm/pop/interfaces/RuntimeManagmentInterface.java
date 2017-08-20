package io.elastest.epm.pop.interfaces;

import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface RuntimeManagmentInterface {

  public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop) throws AdapterException;

  public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop) throws AdapterException;

  public void startInstance(VDU vdu, PoP pop) throws AdapterException;

  public void stopInstance(VDU vdu, PoP pop) throws AdapterException;

  public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop) throws AdapterException, IOException;

  public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop) throws AdapterException, IOException;
}
