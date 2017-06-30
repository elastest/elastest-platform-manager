package io.elastest.epm.api;

import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "pop", description = "the PoP API")
public interface PoPApi {

  @ApiOperation(
    value = "Unregisters a PoP.",
    notes = "Unregisters the PoP that matches with a given ID.",
    response = String.class,
    tags = {
      "PoP",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "PoP unregistered successfully", response = String.class),
      @ApiResponse(code = 400, message = "Bad Request", response = String.class),
      @ApiResponse(code = 404, message = "PoP not found", response = String.class)
    }
  )
  @RequestMapping(value = "/pop/{id}", method = RequestMethod.DELETE)
  default ResponseEntity<String> unregisterPoP(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id)
      throws AdapterException {
    // do some magic!
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Registers a new PoP",
    notes = "Registers a new Point-of-Presence represented by a PoP",
    response = PoP.class,
    tags = {
      "PoP",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "PoP registration OK", response = PoP.class),
      @ApiResponse(code = 400, message = "Bad Request", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST
  )
  default ResponseEntity<PoP> registerPoP(
      @ApiParam(
            value = "Defintion of a PoP which defines a Point-of-Presence to allocate resource",
            required = true
          )
          @RequestBody
          PoP body)
      throws AdapterException {
    // do some magic!
    return new ResponseEntity<PoP>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Returns all PoPs.",
    notes = "Returns all PoPs with all its details.",
    response = PoP.class,
    responseContainer = "List",
    tags = {
      "PoP",
    }
  )
  @ApiResponses(
    value = {@ApiResponse(code = 200, message = "Successful operation", response = PoP.class)}
  )
  @RequestMapping(
    value = "/pop",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  default ResponseEntity<List<PoP>> getAllPoPs() {
    // do some magic!
    return new ResponseEntity<List<PoP>>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Returns a PoP.",
    notes = "Returns the PoP with the given ID. Returns all its details.",
    response = PoP.class,
    tags = {
      "PoP",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = PoP.class),
      @ApiResponse(code = 404, message = "PoP not found.", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop/{id}",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  default ResponseEntity<PoP> getPoPById(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<PoP>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Updates a PoP.",
    notes = "Updates an already registered PoP.",
    response = PoP.class,
    tags = {
      "PoP",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "PoP Update OK", response = PoP.class),
      @ApiResponse(code = 400, message = "Bad Request", response = PoP.class),
      @ApiResponse(code = 404, message = "PoP not found", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PATCH
  )
  default ResponseEntity<PoP> updatePoP(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id,
      @ApiParam(value = "PoP object that needs to be updated.", required = true) @RequestBody
          PoP body) {
    // do some magic!
    return new ResponseEntity<PoP>(HttpStatus.OK);
  }
}
