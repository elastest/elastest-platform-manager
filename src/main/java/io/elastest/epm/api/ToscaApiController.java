package io.elastest.epm.api;

import io.elastest.epm.tosca.parser.ToscaParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Created by rvl on 27.07.17. */
@RestController
@RequestMapping("/tosca-service-template")
public class ToscaApiController {

  @Autowired private ToscaParser toscaParser;

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<List<Object>> sendServiceTemplate(@RequestBody String serviceTemplate)
      throws Exception {
    List<Object> r = toscaParser.templateToModel(serviceTemplate);
    return new ResponseEntity<List<Object>>(r, HttpStatus.CREATED);
  }
}
