package io.elastest.epm.api;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-08-16T18:36:12.064+02:00"
)
@Api(value = "pop", description = "the pop API")
public interface PoPApi {

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
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = PoP.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = PoP.class),
      @ApiResponse(code = 403, message = "Forbidden", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop",
    produces = {"application/json"},
    method = RequestMethod.GET
  )
  ResponseEntity<List<PoP>> getAllPoPs();

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
      @ApiResponse(code = 401, message = "Unauthorized", response = PoP.class),
      @ApiResponse(code = 403, message = "Forbidden", response = PoP.class),
      @ApiResponse(code = 404, message = "PoP not found.", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.GET
  )
  ResponseEntity<PoP> getPoPById(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id);

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
      @ApiResponse(code = 201, message = "Created", response = PoP.class),
      @ApiResponse(code = 400, message = "Bad Request", response = PoP.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = PoP.class),
      @ApiResponse(code = 403, message = "Forbidden", response = PoP.class),
      @ApiResponse(code = 404, message = "Not Found", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.POST
  )
  ResponseEntity<PoP> registerPoP(
      @ApiParam(
            value = "Defintion of a PoP which defines a Point-of-Presence used to host resources",
            required = true
          )
          @Valid
          @RequestBody
          PoP body)
      throws AdapterException;

  @ApiOperation(
    value = "Starts a worker",
    notes =
        "Provides the private key for executing the commands needed for starting the adapters inside a worker",
    response = PoP.class,
    tags = {
      "PoP",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Worker Registration OK", response = PoP.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
      @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
      @ApiResponse(code = 404, message = "Not Found", response = String.class)
    }
  )
  @RequestMapping(
    value = "/pop/{id}",
    produces = {"application/json"},
    consumes = {"multipart/form-data"},
    method = RequestMethod.POST
  )
  ResponseEntity<PoP> registerWorker(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id,
      @ApiParam(value = "file detail") @RequestPart("privateKey") MultipartFile privateKey)
      throws AdapterException, NotFoundException, IOException, JSchException, SftpException;

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
      @ApiResponse(code = 204, message = "No Content", response = String.class),
      @ApiResponse(code = 400, message = "Bad Request", response = String.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
      @ApiResponse(code = 403, message = "Forbidden", response = String.class),
      @ApiResponse(code = 404, message = "PoP not found", response = String.class)
    }
  )
  @RequestMapping(
    value = "/pop/{id}",
    produces = {"*/*"},
    consumes = {"application/json"},
    method = RequestMethod.DELETE
  )
  ResponseEntity<String> unregisterPoP(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id)
      throws AdapterException, NotFoundException;

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
      @ApiResponse(code = 204, message = "No Content", response = PoP.class),
      @ApiResponse(code = 400, message = "Bad Request", response = PoP.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = PoP.class),
      @ApiResponse(code = 403, message = "Forbidden", response = PoP.class),
      @ApiResponse(code = 404, message = "PoP not found", response = PoP.class)
    }
  )
  @RequestMapping(
    value = "/pop/{id}",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PATCH
  )
  ResponseEntity<PoP> updatePoP(
      @ApiParam(value = "ID of PoP", required = true) @PathVariable("id") String id,
      @ApiParam(value = "PoP object that needs to be updated.", required = true) @RequestBody
          PoP body);
}
