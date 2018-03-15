package io.elastest.epm.api;

import com.google.common.collect.Lists;
import io.elastest.epm.model.Key;
import io.elastest.epm.repository.KeyRepository;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2018-03-07T16:32:01.933+01:00"
)
@Controller
public class KeysApiController implements KeysApi {

  @Autowired private KeyRepository keyManagement;

  public ResponseEntity<Key> addKey(
      @ApiParam(value = "Key in a json", required = true) @Valid @RequestBody Key body) {

    Key k = keyManagement.save(body);
    return new ResponseEntity<Key>(k, HttpStatus.OK);
  }

  public ResponseEntity<List<Key>> getAllKeys() {
    // do some magic!
    List<Key> keys = Lists.newArrayList(keyManagement.findAll());
    return new ResponseEntity<List<Key>>(keys, HttpStatus.OK);
  }
}
