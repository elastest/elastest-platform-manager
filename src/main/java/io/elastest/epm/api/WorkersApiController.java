package io.elastest.epm.api;

import com.google.common.collect.Lists;
import io.elastest.epm.api.utils.AdapterLauncher;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Worker;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.WorkerRepository;
import io.swagger.annotations.ApiParam;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-03-07T18:07:29.807+01:00"
)
@Controller
public class WorkersApiController implements WorkersApi {

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private AdapterLauncher adapterLauncher;

    public ResponseEntity<String> deleteWorker(
            @ApiParam(value = "ID of Worker", required = true) @PathVariable("id") String id)
            throws NotFoundException {
        // do some magic!
        if (workerRepository.findOne(id) != null) workerRepository.delete(id);
        else throw new NotFoundException("No worker with id: " + id + " registered.");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    public ResponseEntity<List<Worker>> getAllWorkers() {
        // do some magic!
        List<Worker> workers = Lists.newArrayList(workerRepository.findAll());
        return new ResponseEntity<List<Worker>>(workers, HttpStatus.OK);
    }

    public ResponseEntity<String> installAdapter(
            @ApiParam(value = "ID of Worker", required = true) @PathVariable("id") String id,
            @ApiParam(value = "type of adapter", required = true) @PathVariable("type") String type)
            throws Exception {
        // do some magic!
        String response = adapterLauncher.startAdapter(id, type);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<Worker> registerWorker(
            @ApiParam(value = "worker in a json", required = true) @Valid @RequestBody Worker body)
            throws Exception {

        Worker w = adapterLauncher.configureWorker(body, keyRepository.findOneByName(body.getKeyname()));
        return new ResponseEntity<Worker>(w, HttpStatus.OK);
    }
}
