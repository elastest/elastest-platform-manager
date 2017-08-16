package io.elastest.epm.api;

import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Network;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-16T18:36:12.064+02:00")

@Api(value = "network", description = "the network API")
public interface NetworkApi {

    @ApiOperation(value = "Creates a new network.", notes = "Creates a new network in the target cloud environment with the given CIDR.", response = Network.class, tags={ "Network", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Network creation OK", response = Network.class),
        @ApiResponse(code = 201, message = "Created", response = Network.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Network.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Network.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Network.class),
        @ApiResponse(code = 404, message = "Not Found", response = Network.class) })
    @RequestMapping(value = "/network",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Network> createNetwork(@ApiParam(value = "Defintion of a Network which has to be created on a certain PoP", required = true) @RequestBody Network body) throws AdapterException, BadRequestException, NotFoundException;


    @ApiOperation(value = "Deletes a network.", notes = "Deletes the network that matches with a given ID.", response = String.class, tags={ "Network", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Network deleted successfully", response = String.class),
        @ApiResponse(code = 204, message = "No Content", response = String.class),
        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
        @ApiResponse(code = 403, message = "Forbidden", response = String.class),
        @ApiResponse(code = 404, message = "Network not found", response = String.class) })
    @RequestMapping(value = "/network/{id}",
        produces = { "*/*" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<String> deleteNetwork(@ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id) throws AdapterException;


    @ApiOperation(value = "Returns all existing networks.", notes = "Returns all networks with all details.", response = Network.class, tags={ "Network", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = Network.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Network.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Network.class),
        @ApiResponse(code = 404, message = "Not Found", response = Network.class) })
    @RequestMapping(value = "/network",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Network>> getAllNetworks();


    @ApiOperation(value = "Returns a network.", notes = "Returns the network with the given ID. Returns all its details.", response = Network.class, tags={ "Network", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = Network.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Network.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Network.class),
        @ApiResponse(code = 404, message = "Network not found", response = Network.class) })
    @RequestMapping(value = "/network/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Network> getNetworkById(@ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id);


    @ApiOperation(value = "Updates a Network.", notes = "Updates an existing Network.", response = Network.class, tags={ "Network", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Network Update OK", response = Network.class),
        @ApiResponse(code = 204, message = "No Content", response = Network.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Network.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Network.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Network.class),
        @ApiResponse(code = 404, message = "Network not found", response = Network.class) })
    @RequestMapping(value = "/network/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    ResponseEntity<Network> updateNetwork(@ApiParam(value = "ID of Network", required = true) @PathVariable("id") String id, @ApiParam(value = "Network that needs to be updated.", required = true) @RequestBody Network body);

}
