package io.elastest.epm.api;

import io.elastest.epm.core.VduManagement;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.VDU;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
@Controller
public class VduApiController implements VduApi {

  @Autowired private VduManagement vduManagement;

  public ResponseEntity<String> deleteVdu(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
      throws TerminationException, NotFoundException {
    // do some magic!
    vduManagement.terminateVdu(id);
    return new ResponseEntity<String>("VDU terminated successfully", HttpStatus.OK);
  }

  public ResponseEntity<VDU> deployVdu(
      @ApiParam(
            value = "Defintion of a VDU which defines resources that have to be deployed",
            required = true
          )
          @RequestBody
          VDU body)
      throws AllocationException, NotFoundException {
    // do some magic!
    VDU vdu = vduManagement.deployVdu(body);
    return new ResponseEntity<VDU>(vdu, HttpStatus.OK);
  }

  public ResponseEntity<List<VDU>> getAllVdus() {
    // do some magic!
    List<VDU> allVdus = vduManagement.getAllVdus();
    return new ResponseEntity<List<VDU>>(allVdus, HttpStatus.OK);
  }

  public ResponseEntity<VDU> getVduById(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id) {
    // do some magic!
    VDU vdu = vduManagement.getVduById(id);
    return new ResponseEntity<VDU>(HttpStatus.OK);
  }

  public ResponseEntity<VDU> updateVdu(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
      @ApiParam(value = "VDU object that needs to be updated.", required = true) @RequestBody
          VDU body) {
    // do some magic!
    return new ResponseEntity<VDU>(HttpStatus.OK);
  }
}
