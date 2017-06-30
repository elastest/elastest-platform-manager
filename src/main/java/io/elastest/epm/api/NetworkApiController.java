package io.elastest.epm.api;

import io.elastest.epm.core.NetworkManagement;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Network;
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
public class NetworkApiController implements NetworkApi {

  @Autowired private NetworkManagement networkManagement;

  public ResponseEntity<Network> createNetwork(
      @ApiParam(
            value = "Defintion of a Network which has to be created on a certain PoP",
            required = true
          )
          @RequestBody
          Network body)
      throws AdapterException, BadRequestException, NotFoundException {
    // do some magic!
    Network network = networkManagement.createNetwork(body);
    return new ResponseEntity<Network>(network, HttpStatus.OK);
  }

  public ResponseEntity<String> deleteNetwork(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id)
      throws AdapterException {
    // do some magic!
    networkManagement.deleteNetwork(id);
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  public ResponseEntity<List<Network>> getAllNetworks() {
    // do some magic!
    List<Network> allNetworks = networkManagement.getAllNetworks();
    return new ResponseEntity<List<Network>>(allNetworks, HttpStatus.OK);
  }

  public ResponseEntity<Network> getNetworkById(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id) {
    // do some magic!
    Network network = networkManagement.getNetworkById(id);
    return new ResponseEntity<Network>(network, HttpStatus.OK);
  }

  public ResponseEntity<Network> updateNetwork(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Network that needs to be updated.", required = true) @RequestBody
          Network body) {
    // do some magic!
    Network network = networkManagement.updateNetwork(id, body);
    return new ResponseEntity<Network>(network, HttpStatus.OK);
  }
}
