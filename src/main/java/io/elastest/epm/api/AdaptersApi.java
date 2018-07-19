/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.elastest.epm.api;

import io.elastest.epm.model.Adapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-03-22T14:35:13.589+01:00"
)
@Api(value = "adapters", description = "the adapters API")
public interface AdaptersApi {

    @ApiOperation(
            value = "Returns all registered adapters",
            notes = "",
            response = Adapter.class,
            responseContainer = "List",
            tags = {
                    "Adapter",
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 200,
                            message = "Successful operation",
                            response = Adapter.class,
                            responseContainer = "List"
                    ),
                    @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = Void.class)
            }
    )
    @RequestMapping(
            value = "/adapters",
            produces = {"application/json"},
            method = RequestMethod.GET
    )
    ResponseEntity<List<Adapter>> getAllAdapters();
}
