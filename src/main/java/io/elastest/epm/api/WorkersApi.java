/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.elastest.epm.api;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Worker;
import io.swagger.annotations.*;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.apache.commons.compress.archivers.ArchiveException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-03-15T14:35:13.711+01:00"
)
@Api(value = "workers", description = "the workers API")
public interface WorkersApi {

    @ApiOperation(value = "Creates a new worker.", notes = "Receives a package that can be used for creating a new worker.", response = Worker.class, responseContainer = "List", tags={ "Package", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workers created", response = Worker.class, responseContainer = "List"),
            @ApiResponse(code = 201, message = "Created", response = Void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
            @ApiResponse(code = 404, message = "Not Found", response = String.class) })

    @RequestMapping(value = "/workers/create",
            produces = { "application/json" },
            consumes = { "multipart/form-data" },
            method = RequestMethod.POST)
    ResponseEntity<List<Worker>> createWorker(@ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) throws Exception;


    @ApiOperation(
            value = "Deletes a Resource Group.",
            notes = "Deletes the Worker that matches with a given ID.",
            response = String.class,
            tags = {
                    "Worker",
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Worker deleted successfully", response = String.class),
                    @ApiResponse(code = 204, message = "No Content", response = Void.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = String.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
                    @ApiResponse(code = 404, message = "Worker not found", response = String.class)
            }
    )
    @RequestMapping(
            value = "/workers/{id}",
            produces = {"*/*"},
            method = RequestMethod.DELETE
    )
    ResponseEntity<String> deleteWorker(
            @ApiParam(value = "ID of Worker", required = true) @PathVariable("id") String id)
            throws NotFoundException;

    @ApiOperation(
            value = "Returns all registered workers",
            notes = "",
            response = Worker.class,
            responseContainer = "List",
            tags = {
                    "Worker",
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful operation",
                            response = Worker.class,
                            responseContainer = "List"
                    ),
                    @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = Void.class)
            }
    )
    @RequestMapping(
            value = "/workers",
            produces = {"application/json"},
            method = RequestMethod.GET
    )
    ResponseEntity<List<Worker>> getAllWorkers();

    @ApiOperation(
            value = "Sets up the specified worker to install the specified type of adapter.",
            notes = "",
            response = String.class,
            tags = {
                    "Worker",
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation", response = String.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = Void.class)
            }
    )
    @RequestMapping(
            value = "/workers/{id}/{type}",
            produces = {"application/json"},
            method = RequestMethod.GET
    )
    ResponseEntity<String> installAdapter(
            @ApiParam(value = "ID of Worker", required = true) @PathVariable("id") String id,
            @ApiParam(value = "type of adapter", required = true) @PathVariable("type") String type)
            throws NotFoundException, Exception;

    @ApiOperation(
            value = "Registers the worker and saves the information.",
            notes = "This registers a worker with the information provided.",
            response = Worker.class,
            tags = {
                    "Worker",
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Worker registered OK", response = Worker.class),
                    @ApiResponse(code = 201, message = "Created", response = Void.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = Void.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
                    @ApiResponse(code = 404, message = "Not Found/keys", response = Void.class)
            }
    )
    @RequestMapping(
            value = "/workers",
            consumes = {"application/json"},
            method = RequestMethod.POST
    )
    ResponseEntity<Worker> registerWorker(
            @ApiParam(value = "worker in a json", required = true) @Valid @RequestBody Worker body)
            throws Exception;
}
