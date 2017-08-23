package io.elastest.epm.api;

import io.elastest.epm.api.body.CommandExecutionBody;
import io.elastest.epm.api.body.FileDownloadBody;
import io.elastest.epm.api.body.FileUploadBody;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.*;
import java.io.File;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-08-17T16:30:49.905+02:00"
)
@Api(value = "runtime", description = "the runtime API")
public interface RuntimeApi {

  @ApiOperation(
    value = "Downloads a file from a VDU.",
    notes = "Download a file with the given filepath from the given VDU.",
    response = File.class,
    tags = {
      "Runtime",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = File.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = File.class),
      @ApiResponse(code = 403, message = "Forbidden", response = File.class),
      @ApiResponse(code = 404, message = "VDU/File not found.", response = File.class)
    }
  )
  @RequestMapping(
    value = "/runtime/{id}/file",
    produces = {"multipart/form-data"},
    consumes = {"application/json"},
    method = RequestMethod.GET
  )
  ResponseEntity<File> downloadFileFromInstance(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
      @ApiParam(
            value = "Contains details of the file to be downloaded from the given Instance",
            required = true
          )
          @RequestBody
          FileDownloadBody fileDownloadBody)
      throws AdapterException, NotFoundException, IOException;

  @ApiOperation(
    value = "Executes given command on the given VDU.",
    notes = "Executes the given command on the VDU with the given ID.",
    response = String.class,
    tags = {
      "Runtime",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = String.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
      @ApiResponse(code = 403, message = "Forbidden", response = String.class),
      @ApiResponse(code = 404, message = "VDU not found.", response = String.class)
    }
  )
  @RequestMapping(
    value = "/runtime/{id}/action/execute",
    produces = {"application/json"},
    consumes = {"application/json"},
    method = RequestMethod.PUT
  )
  ResponseEntity<String> executeOnInstance(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
      @ApiParam(
            value =
                "Contains command to be executed and a flag if for the completion of the execution should be awaited",
            required = true
          )
          @RequestBody
          CommandExecutionBody commandExecutionBody)
      throws AdapterException, NotFoundException;

  @ApiOperation(
    value = "Starts the given VDU.",
    notes = "Starts the VDU with the given ID.",
    response = Void.class,
    tags = {
      "Runtime",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
      @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
      @ApiResponse(code = 404, message = "VDU/File not found.", response = Void.class)
    }
  )
  @RequestMapping(value = "/runtime/{id}/action/start", method = RequestMethod.PUT)
  ResponseEntity<Void> startInstance(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
      throws AdapterException, NotFoundException;

  @ApiOperation(
    value = "Stops the given VDU.",
    notes = "Stops the VDU with the given ID.",
    response = Void.class,
    tags = {
      "Runtime",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
      @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
      @ApiResponse(code = 404, message = "VDU/File not found.", response = Void.class)
    }
  )
  @RequestMapping(value = "/runtime/{id}/action/stop", method = RequestMethod.PUT)
  ResponseEntity<Void> stopInstance(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
      throws AdapterException, NotFoundException;

  @ApiOperation(
    value = "Uploads a file to a VDU.",
    notes = "Uploads a given file to the given filepath to the given VDU.",
    response = Void.class,
    tags = {
      "Runtime",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
      @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
      @ApiResponse(code = 404, message = "VDU/Path not found.", response = Void.class)
    }
  )
  @RequestMapping(
    value = "/runtime/{id}/file",
    consumes = {"multipart/form-data"},
    method = RequestMethod.POST
  )
  ResponseEntity<Void> uploadFileToInstanceByFile(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Absolute path where the file should go on the Instance", required = true)
          @RequestPart(value = "remotePath", required = true)
          String remotePath,
      @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file)
      throws AdapterException, BadRequestException, NotFoundException, IOException;

  @ApiOperation(
    value = "Uploads a file to a VDU.",
    notes = "Uploads a given file from the host path to the given file path to the given VDU.",
    response = Void.class,
    tags = {
      "Runtime",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
      @ApiResponse(code = 400, message = "Bad request", response = Void.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
      @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
      @ApiResponse(code = 404, message = "VDU/Path not found.", response = Void.class)
    }
  )
  @RequestMapping(
    value = "/runtime/{id}/path",
    consumes = {"application/json"},
    method = RequestMethod.POST
  )
  ResponseEntity<Void> uploadFileToInstanceByPath(
      @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
      @ApiParam(
            value = "Contains details of the file with the given path to upload to the Instance",
            required = true
          )
          @RequestBody
          FileUploadBody fileUploadBody)
      throws AdapterException, BadRequestException, NotFoundException, IOException;
}
