package io.elastest.epm.api;

import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.tosca.parser.ToscaParser;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2017-08-03T17:51:47.319+02:00"
)
@Controller
public class ToscaApiController implements ToscaApi {

    @Autowired
    private ToscaParser toscaParser;

    private final Logger log = LoggerFactory.getLogger(ToscaApiController.class);

    public ResponseEntity<ResourceGroup> deployToscaTemplate(
            @ApiParam(value = "TOSCA formatted template", required = true) @RequestBody
                    String serviceTemplate)
            throws Exception {
        log.debug(serviceTemplate);
        ResourceGroup resourceGroup = toscaParser.templateToModel(serviceTemplate);
        return new ResponseEntity<ResourceGroup>(resourceGroup, HttpStatus.OK);
    }
}
