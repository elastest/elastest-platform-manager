package io.elastest.epm.api;

import io.elastest.epm.model.Adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.elastest.epm.repository.AdapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2018-03-22T14:35:13.589+01:00"
)
@Controller
public class AdaptersApiController implements AdaptersApi {

    @Autowired private AdapterRepository adapterRepository;

    public ResponseEntity<List<Adapter>> getAllAdapters() {
        // do some magic!
        Iterable<Adapter> adapters = adapterRepository.findAll();
        List<Adapter> output = new ArrayList<>();
        output.addAll((Collection<? extends Adapter>) adapters);

        return new ResponseEntity<List<Adapter>>(output, HttpStatus.OK);
    }
}
