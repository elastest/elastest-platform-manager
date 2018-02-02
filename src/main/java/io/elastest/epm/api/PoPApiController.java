package io.elastest.epm.api;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.api.utils.AdapterLauncher;
import io.elastest.epm.core.PoPManagement;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
@Controller
public class PoPApiController implements PoPApi {

  @Autowired private PoPManagement popManagement;

  public ResponseEntity<String> unregisterPoP(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id)
      throws AdapterException, NotFoundException {
    // do some magic!
    popManagement.unregisterPoP(id);
    return new ResponseEntity<String>("PoP unregistered successfully", HttpStatus.OK);
  }

  public ResponseEntity<PoP> registerPoP(
      @ApiParam(
            value = "Defintion of a PoP which defines a Point-of-Presence used to host resources",
            required = true
          )
          @Valid
          @RequestBody
          PoP body)
      throws AdapterException {
    // do some magic!
    PoP poP = popManagement.registerPoP(body);
    return new ResponseEntity<PoP>(poP, HttpStatus.OK);
  }

  public ResponseEntity<PoP> registerWorker(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id,
      @ApiParam(value = "file detail") @RequestPart("file") MultipartFile privateKey)
      throws AdapterException, NotFoundException, IOException, JSchException, SftpException {
    // do some magic!
    PoP poP = popManagement.getPoPById(id);

    boolean worker = false;
    for (KeyValuePair kvp : poP.getInterfaceInfo()) {
      if (kvp.getKey().equals("type") && kvp.getValue().equals("worker")) worker = true;
    }
    if (!worker)
      throw new AdapterException(
          "You can register a worker only on a worker pop. The type of the pop must be worker");

    String user = "";
    String passphrase = "";
    String password = "";
    String host = poP.getInterfaceEndpoint();
    String epmIp = "";

    for (KeyValuePair kvp : poP.getInterfaceInfo()) {
      if (kvp.getKey().equals("user")) user = kvp.getValue();
      if (kvp.getKey().equals("passphrase")) passphrase = kvp.getValue();
      if (kvp.getKey().equals("password")) password = kvp.getValue();
      if (kvp.getKey().equals("epmIP")) epmIp = kvp.getValue();
    }

    if (user.equals("") || epmIp.equals("") || host.equals(null))
      throw new NotFoundException(
          "To register a worker the PoP must provide the InferaceEndpoint"
              + " and InterfaceInfo containing user, IP of the EPM and passphrase information");

    AdapterLauncher.startAdapters(privateKey.getInputStream(), host, user, passphrase, password, epmIp);

    return new ResponseEntity<PoP>(HttpStatus.OK);
  }

  public ResponseEntity<List<PoP>> getAllPoPs() {
    // do some magic!
    List<PoP> allPoPs = popManagement.getAllPoPs();
    return new ResponseEntity<List<PoP>>(allPoPs, HttpStatus.OK);
  }

  public ResponseEntity<PoP> getPoPById(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id) {
    // do some magic!
    PoP poP = popManagement.getPoPById(id);
    return new ResponseEntity<PoP>(poP, HttpStatus.OK);
  }

  public ResponseEntity<PoP> updatePoP(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id,
      @ApiParam(value = "PoP object that needs to be updated.", required = true) @RequestBody
          PoP body) {
    // do some magic!
    PoP poP = popManagement.updatePoP(id, body);
    return new ResponseEntity<PoP>(poP, HttpStatus.OK);
  }
}
