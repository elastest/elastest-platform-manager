package io.elastest.epm.api;

import com.google.common.collect.Lists;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.api.body.WorkerFromVDU;
import io.elastest.epm.api.utils.AdapterLauncher;
import io.elastest.epm.api.utils.WorkerLauncher;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Worker;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.WorkerRepository;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.apache.commons.compress.archivers.ArchiveException;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-03-07T18:07:29.807+01:00"
)
@Controller
public class WorkersApiController implements WorkersApi {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private AdapterLauncher adapterLauncher;
    @Autowired
    private WorkerLauncher workerLauncher;

    @Override
    public ResponseEntity<Worker> createWorker(@ApiParam(value = "Body to create Worker from VDU" ,required=true )  @Valid @RequestBody WorkerFromVDU workerFromVDU) {
        try {
            log.debug("ID: " + workerFromVDU.getVduId());
            Worker worker = workerLauncher.createWorker(workerFromVDU);
            return new ResponseEntity<Worker>(worker, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Worker>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteWorker(
            @ApiParam(value = "ID of Worker", required = true) @PathVariable("id") String id) {
        // do some magic!
        if (workerRepository.findOne(id) != null) workerRepository.delete(id);
        else {
            return new ResponseEntity<String>("Could not find worker with id: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    public ResponseEntity<List<Worker>> getAllWorkers() {
        // do some magic!
        List<Worker> workers = Lists.newArrayList(workerRepository.findAll());
        return new ResponseEntity<List<Worker>>(workers, HttpStatus.OK);
    }

    public ResponseEntity<String> installAdapter(
            @ApiParam(value = "ID of Worker", required = true) @PathVariable("id") String id,
            @ApiParam(value = "type of adapter", required = true) @PathVariable("type") String type) {
        // do some magic!
        String response = null;
        try {
            response = adapterLauncher.startAdapter(id, type);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (JSchException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (SftpException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InterruptedException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    public ResponseEntity<Worker> registerWorker(
            @ApiParam(value = "worker in a json", required = true) @Valid @RequestBody Worker body) {

        Worker w = null;
        try {
            w = workerLauncher.configureWorker(body);
        } catch (Exception e) {
            return new ResponseEntity<Worker>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Worker>(w, HttpStatus.OK);
    }
}
