package io.elastest.epm.api;

import io.elastest.epm.model.Network;
import io.swagger.annotations.*;
import java.util.List;
import javax.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
@Api(value = "network", description = "the network API")
public interface NetworkApi {

  @ApiOperation(
    value = "Creates a new network",
    notes = "Creates a new network in the target cloud environment with the given IP range",
    response = Network.class,
    tags = {
      "Network",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Network creation OK", response = Network.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Network.class)
    }
  )
  @RequestMapping(
    value = "/network",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST
  )
  default ResponseEntity<Network> createNetwork(
      @ApiParam(
            value = "Defintion of a VDU which defines resources that have to be deployed",
            required = true
          )
          @RequestBody
          Network body) {
    // do some magic!
    return new ResponseEntity<Network>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Deletes a network.",
    notes = "Deletes the network that matches with a given ID.",
    response = String.class,
    tags = {
      "Network",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Network deleted successfully", response = String.class),
      @ApiResponse(code = 400, message = "Bad Request", response = String.class),
      @ApiResponse(code = 404, message = "Network not found", response = String.class)
    }
  )
  @RequestMapping(value = "/network/{id}", method = RequestMethod.DELETE)
  default ResponseEntity<String> deleteNetwork(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Returns all existing networks.",
    notes = "Returns all networks with all details.",
    response = Network.class,
    responseContainer = "List",
    tags = {
      "Network",
    }
  )
  @ApiResponses(
    value = {@ApiResponse(code = 200, message = "Successful operation", response = Network.class)}
  )
  @RequestMapping(
    value = "/network",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  default ResponseEntity<List<Network>> getAllNetworks() {
    // do some magic!
    return new ResponseEntity<List<Network>>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Returns a network.",
    notes = "Returns the network with the given ID. Returns all its details.",
    response = Network.class,
    tags = {
      "Network",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Network.class),
      @ApiResponse(code = 404, message = "Network not found", response = Network.class)
    }
  )
  @RequestMapping(
    value = "/network/{id}",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  default ResponseEntity<Network> getNetworkById(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<Network>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Updates a Network.",
    notes = "Updates an existing Network.",
    response = Network.class,
    tags = {
      "Network",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Network Update OK", response = Network.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Network.class),
      @ApiResponse(code = 404, message = "Network not found", response = Network.class)
    }
  )
  @RequestMapping(
    value = "/network/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PATCH
  )
  default ResponseEntity<Network> updateNetwork(
      @ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Network that needs to be updated.", required = true) @RequestBody
          Network body) {
    // do some magic!
    return new ResponseEntity<Network>(HttpStatus.OK);
  }
}
