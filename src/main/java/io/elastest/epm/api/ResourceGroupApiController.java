package io.elastest.epm.api;

import io.elastest.epm.core.ResourceGroupManagement;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.ResourceGroup;
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
  date = "2017-08-03T19:03:08.268+02:00"
)
@Controller
public class ResourceGroupApiController implements ResourceGroupApi {

  @Autowired private ResourceGroupManagement resourceGroupManagement;

  public ResponseEntity<ResourceGroup> createResourceGroup(
      @ApiParam(
            value =
                "Defintion of a Resource Group which includes all VDUs, Network and respective PoPs",
            required = true
          )
          @RequestBody
          ResourceGroup body)
      throws AdapterException, BadRequestException, AllocationException, NotFoundException {
    ResourceGroup resourceGroup = resourceGroupManagement.deployResourceGroup(body);
    return new ResponseEntity<ResourceGroup>(resourceGroup, HttpStatus.OK);
  }

  public ResponseEntity<String> deleteResourceGroup(
      @ApiParam(value = "ID of ResourceGroup", required = true) @PathVariable("id") String id)
      throws AdapterException, TerminationException, NotFoundException {
    resourceGroupManagement.terminateResourceGroup(id);
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  public ResponseEntity<List<ResourceGroup>> getAllResourceGroups() {
    return new ResponseEntity<List<ResourceGroup>>(
        resourceGroupManagement.getAllResourceGroups(), HttpStatus.OK);
  }

  public ResponseEntity<ResourceGroup> getResourceGroupById(
      @ApiParam(value = "ID of ResourceGroup", required = true) @PathVariable("id") String id)
      throws NotFoundException {
    return new ResponseEntity<ResourceGroup>(
        resourceGroupManagement.getResourceGroupById(id), HttpStatus.OK);
  }

  public ResponseEntity<ResourceGroup> updateResourceGroup(
      @ApiParam(value = "ID of ResourceGroup", required = true) @PathVariable("id") String id,
      @ApiParam(value = "ResourceGroup that needs to be updated.", required = true) @RequestBody
          ResourceGroup body) {
    resourceGroupManagement.updateResourceGroup(id, body);
    return new ResponseEntity<ResourceGroup>(HttpStatus.OK);
  }
}
