package io.elastest.epm.api;

import io.elastest.epm.model.VDU;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.constraints.*;
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

  public ResponseEntity<String> deleteVdu(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  public ResponseEntity<VDU> deployVdu(
      @ApiParam(
            value = "Defintion of a VDU which defines resources that have to be deployed",
            required = true
          )
          @RequestBody
          VDU body) {
    // do some magic!
    return new ResponseEntity<VDU>(HttpStatus.OK);
  }

  public ResponseEntity<List<VDU>> getAllVdus() {
    // do some magic!
    return new ResponseEntity<List<VDU>>(HttpStatus.OK);
  }

  public ResponseEntity<VDU> getVduById(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id) {
    // do some magic!
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
