package io.elastest.epm.api;

import io.elastest.epm.model.Network;
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
public class NetworkApiController implements NetworkApi {

  public ResponseEntity<Network> createNetwork(
      @ApiParam(
            value = "Defintion of a VDU which defines resources that have to be deployed",
            required = true
          )
          @RequestBody
          Network body) {
    // do some magic!
    return new ResponseEntity<Network>(HttpStatus.OK);
  }

  public ResponseEntity<String> deleteNetwork(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  public ResponseEntity<List<Network>> getAllNetworks() {
    // do some magic!
    return new ResponseEntity<List<Network>>(HttpStatus.OK);
  }

  public ResponseEntity<Network> getNetworkById(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<Network>(HttpStatus.OK);
  }

  public ResponseEntity<Network> updateNetwork(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Network that needs to be updated.", required = true) @RequestBody
          Network body) {
    // do some magic!
    return new ResponseEntity<Network>(HttpStatus.OK);
  }
}
