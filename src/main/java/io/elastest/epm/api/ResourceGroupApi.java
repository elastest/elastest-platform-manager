package io.elastest.epm.api;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-16T18:36:12.064+02:00")

@Api(value = "resourceGroup", description = "the resourceGroup API")
public interface ResourceGroupApi {

    @ApiOperation(value = "Creates a new Resource Group.", notes = "Creates a new Resource Group and allocates the defined resources in the defined PoPs.", response = ResourceGroup.class, tags={ "ResourceGroup", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Resource Group creation OK", response = ResourceGroup.class),
        @ApiResponse(code = 201, message = "Created", response = ResourceGroup.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ResourceGroup.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ResourceGroup.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ResourceGroup.class),
        @ApiResponse(code = 404, message = "Not Found", response = ResourceGroup.class) })
    @RequestMapping(value = "/resourceGroup",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ResourceGroup> createResourceGroup(@ApiParam(value = "Defintion of a Resource Group which includes all VDUs, Network and respective PoPs", required = true) @RequestBody ResourceGroup body) throws AdapterException, BadRequestException, AllocationException, NotFoundException;


    @ApiOperation(value = "Deletes a Resource Group.", notes = "Deletes the Resource Group that matches with a given ID.", response = String.class, tags={ "ResourceGroup", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ResourceGroup deleted successfully", response = String.class),
        @ApiResponse(code = 204, message = "No Content", response = String.class),
        @ApiResponse(code = 400, message = "Bad Request", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
        @ApiResponse(code = 403, message = "Forbidden", response = String.class),
        @ApiResponse(code = 404, message = "ResourceGroup not found", response = String.class) })
    @RequestMapping(value = "/resourceGroup/{id}",
        produces = { "*/*" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<String> deleteResourceGroup(@ApiParam(value = "ID of ResourceGroup", required = true) @PathVariable("id") String id) throws AdapterException, TerminationException, NotFoundException;


    @ApiOperation(value = "Returns all Resource Groups.", notes = "Returns all Resource Groups with all details.", response = ResourceGroup.class, tags={ "ResourceGroup", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = ResourceGroup.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ResourceGroup.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ResourceGroup.class),
        @ApiResponse(code = 404, message = "Not Found", response = ResourceGroup.class) })
    @RequestMapping(value = "/resourceGroup",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<ResourceGroup>> getAllResourceGroups();


    @ApiOperation(value = "Returns a Resource Group.", notes = "Returns the Resource Group with the given ID. Returns all its details.", response = ResourceGroup.class, tags={ "ResourceGroup", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = ResourceGroup.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ResourceGroup.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ResourceGroup.class),
        @ApiResponse(code = 404, message = "ResourceGroup not found", response = ResourceGroup.class) })
    @RequestMapping(value = "/resourceGroup/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<ResourceGroup> getResourceGroupById(@ApiParam(value = "ID of ResourceGroup", required = true) @PathVariable("id") String id) throws NotFoundException;


    @ApiOperation(value = "Updates a ResourceGroup.", notes = "Updates an existing ResourceGroup.", response = ResourceGroup.class, tags={ "ResourceGroup", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ResourceGroup Update OK", response = ResourceGroup.class),
        @ApiResponse(code = 204, message = "No Content", response = ResourceGroup.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ResourceGroup.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ResourceGroup.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ResourceGroup.class),
        @ApiResponse(code = 404, message = "ResourceGroup not found", response = ResourceGroup.class) })
    @RequestMapping(value = "/resourceGroup/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    ResponseEntity<ResourceGroup> updateResourceGroup(@ApiParam(value = "ID of ResourceGroup", required = true) @PathVariable("id") String id, @ApiParam(value = "ResourceGroup that needs to be updated.", required = true) @RequestBody ResourceGroup body);

}
