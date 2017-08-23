package io.elastest.epm.api;

import io.elastest.epm.model.ResourceGroup;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-08-16T18:36:12.064+02:00"
)
@Api(value = "tosca", description = "the tosca API")
public interface ToscaApi {

  @ApiOperation(
    value = "Deploys a Tosca template.",
    notes =
        "The TOSCA template defines VDUs, Networks and the PoPs where to allocate the virtual resources",
    response = ResourceGroup.class,
    tags = {
      "TOSCA",
    }
  )
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "Successful operation", response = ResourceGroup.class),
      @ApiResponse(code = 201, message = "Created", response = ResourceGroup.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResourceGroup.class),
      @ApiResponse(code = 401, message = "Unauthorized", response = ResourceGroup.class),
      @ApiResponse(code = 403, message = "Forbidden", response = ResourceGroup.class),
      @ApiResponse(code = 404, message = "Not Found", response = ResourceGroup.class)
    }
  )
  @RequestMapping(
    value = "/tosca",
    produces = {"application/json"},
    consumes = {"text/yaml"},
    method = RequestMethod.POST
  )
  ResponseEntity<ResourceGroup> deployToscaTemplate(
      @ApiParam(value = "TOSCA formatted template", required = true) @RequestBody
          String serviceTemplate)
      throws Exception;
}
