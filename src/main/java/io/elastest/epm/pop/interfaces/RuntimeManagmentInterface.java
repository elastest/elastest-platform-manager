package io.elastest.epm.pop.interfaces;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface RuntimeManagmentInterface {

  InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
      throws AdapterException, NotFoundException;

  String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop)
      throws AdapterException, NotFoundException;

  void startInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException;

  void stopInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException;

  void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
      throws AdapterException, IOException, NotFoundException;

  void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop)
      throws AdapterException, IOException, NotFoundException;
}
