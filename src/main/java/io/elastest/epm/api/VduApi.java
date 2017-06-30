package io.elastest.epm.api;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.*;
import java.util.List;
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
@Api(value = "vdu", description = "the vdu API")
public interface VduApi {

  @ApiOperation(
    value = "Terminates a VDU.",
    notes = "Terminates the VDU that matches with a given ID.",
    response = String.class,
    tags = {
      "VDU",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "VDU terminated successfully", response = String.class),
      @ApiResponse(code = 400, message = "Bad Request", response = String.class),
      @ApiResponse(code = 404, message = "VDU not found", response = String.class)
    }
  )
  @RequestMapping(value = "/vdu/{id}", method = RequestMethod.DELETE)
  default ResponseEntity<String> deleteVdu(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
      throws AdapterException, TerminationException, NotFoundException {
    // do some magic!
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Allocates resources in the target cloud.",
    notes =
        "Allocates resources defined as a VDU in the cloud to be deployed in the target cloud environment. It instantiates computing resources, deploys artifacts on them and manages the their lifecycle",
    response = VDU.class,
    tags = {
      "VDU",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "VDU allocation OK", response = VDU.class),
      @ApiResponse(code = 400, message = "Bad Request", response = VDU.class)
    }
  )
  @RequestMapping(
    value = "/vdu",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST
  )
  default ResponseEntity<VDU> deployVdu(
      @ApiParam(
            value = "Defintion of a VDU which defines resources that have to be deployed",
            required = true
          )
          @RequestBody
          VDU body)
      throws AdapterException, AllocationException, NotFoundException {
    // do some magic!
    return new ResponseEntity<VDU>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Returns all VDUs.",
    notes = "Returns all VDUs with all its details.",
    response = VDU.class,
    responseContainer = "List",
    tags = {
      "VDU",
    }
  )
  @ApiResponses(
    value = {@ApiResponse(code = 200, message = "Successful operation", response = VDU.class)}
  )
  @RequestMapping(
    value = "/vdu",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  default ResponseEntity<List<VDU>> getAllVdus() {
    // do some magic!
    return new ResponseEntity<List<VDU>>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Returns a VDU.",
    notes = "Returns the VDU with the given ID. Returns all its details.",
    response = VDU.class,
    tags = {
      "VDU",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = VDU.class),
      @ApiResponse(code = 404, message = "VDU not found.", response = VDU.class)
    }
  )
  @RequestMapping(
    value = "/vdu/{id}",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  default ResponseEntity<VDU> getVduById(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<VDU>(HttpStatus.OK);
  }

  @ApiOperation(
    value = "Updates a VDU.",
    notes = "Updates an already deployed VDU.",
    response = VDU.class,
    tags = {
      "VDU",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "VDU Update OK", response = VDU.class),
      @ApiResponse(code = 400, message = "Bad Request", response = VDU.class),
      @ApiResponse(code = 404, message = "VDU not found", response = VDU.class)
    }
  )
  @RequestMapping(
    value = "/vdu/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PATCH
  )
  default ResponseEntity<VDU> updateVdu(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
      @ApiParam(value = "VDU object that needs to be updated.", required = true) @RequestBody
          VDU body) {
    // do some magic!
    return new ResponseEntity<VDU>(HttpStatus.OK);
  }
}
