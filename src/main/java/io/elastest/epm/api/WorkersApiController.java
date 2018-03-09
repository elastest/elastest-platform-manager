package io.elastest.epm.api;

import com.google.common.collect.Lists;
import io.elastest.epm.api.utils.AdapterLauncher;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Worker;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.WorkerRepository;
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
  date = "2018-03-07T18:07:29.807+01:00"
)
@Controller
public class WorkersApiController implements WorkersApi {

  @Autowired private WorkerRepository workerRepository;
  @Autowired private KeyRepository keyRepository;

  public ResponseEntity<List<Worker>> getAllWorkers() {
    // do some magic!
    List<Worker> workers = Lists.newArrayList(workerRepository.findAll());
    return new ResponseEntity<List<Worker>>(workers, HttpStatus.OK);
  }

  public ResponseEntity<Worker> registerWorker(
      @ApiParam(value = "worker in a json", required = true) @Valid @RequestBody Worker body)
      throws Exception {
    // do some magic!

    if (workerRepository.findOneByIp(body.getIp()) != null)
      throw new Exception("There is already a worker registered at the ip: " + body.getIp());

    if (keyRepository.findOneByName(body.getKeyname()) == null)
      throw new NotFoundException("The key was not found!");

    if (body.getUser().equals("") || body.getEpmIp().equals("") || body.getIp().equals(null))
      throw new NotFoundException(
          "To register a worker the PoP must provide the InferaceEndpoint"
              + " and InterfaceInfo containing user, IP of the EPM and passphrase information");

    AdapterLauncher.startAdapters(body, keyRepository.findOneByName(body.getKeyname()));
    return new ResponseEntity<Worker>(HttpStatus.OK);
  }
}
