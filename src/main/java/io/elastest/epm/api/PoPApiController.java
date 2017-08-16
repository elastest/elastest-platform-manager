package io.elastest.epm.api;

import io.elastest.epm.core.PoPManagement;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
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
public class PoPApiController implements PopApi {

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
          @RequestBody
          PoP body)
      throws AdapterException {
    // do some magic!
    PoP poP = popManagement.registerPoP(body);
    return new ResponseEntity<PoP>(poP, HttpStatus.OK);
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
