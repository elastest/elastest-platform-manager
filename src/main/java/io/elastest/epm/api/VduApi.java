package io.elastest.epm.api;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.VDU;
import io.swagger.annotations.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2017-08-16T18:36:12.064+02:00"
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
                    @ApiResponse(code = 204, message = "No Content", response = String.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = String.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = String.class),
                    @ApiResponse(code = 404, message = "VDU not found", response = String.class)
            }
    )
    @RequestMapping(value = "/vdu/{id}", method = RequestMethod.DELETE)
    ResponseEntity<String> deleteVdu(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
            throws TerminationException, NotFoundException;

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
                    @ApiResponse(code = 201, message = "Created", response = VDU.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = VDU.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = VDU.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = VDU.class),
                    @ApiResponse(code = 404, message = "Not Found", response = VDU.class)
            }
    )
    @RequestMapping(
            value = "/vdu",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST
    )
    ResponseEntity<VDU> deployVdu(
            @ApiParam(
                    value = "Defintion of a VDU which defines resources that have to be deployed",
                    required = true
            )
            @RequestBody
                    VDU body)
            throws AllocationException, NotFoundException;

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
            value = {
                    @ApiResponse(code = 200, message = "Successful operation", response = VDU.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = VDU.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = VDU.class),
                    @ApiResponse(code = 404, message = "Not Found", response = VDU.class)
            }
    )
    @RequestMapping(
            value = "/vdu",
            produces = {"application/json"},
            method = RequestMethod.GET
    )
    ResponseEntity<List<VDU>> getAllVdus();

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
                    @ApiResponse(code = 401, message = "Unauthorized", response = VDU.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = VDU.class),
                    @ApiResponse(code = 404, message = "VDU not found.", response = VDU.class)
            }
    )
    @RequestMapping(
            value = "/vdu/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET
    )
    ResponseEntity<VDU> getVduById(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
            throws NotFoundException;

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
                    @ApiResponse(code = 204, message = "No Content", response = VDU.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = VDU.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = VDU.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = VDU.class),
                    @ApiResponse(code = 404, message = "VDU not found", response = VDU.class)
            }
    )
    @RequestMapping(
            value = "/vdu/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH
    )
    ResponseEntity<VDU> updateVdu(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
            @ApiParam(value = "VDU object that needs to be updated.", required = true) @RequestBody
                    VDU body);
}
