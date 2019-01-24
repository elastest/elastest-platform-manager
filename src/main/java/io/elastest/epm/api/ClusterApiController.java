package io.elastest.epm.api;

import com.google.common.collect.Lists;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.api.body.ClusterFromResourceGroup;
import io.elastest.epm.api.utils.ClusterLauncher;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Cluster;
import io.elastest.epm.repository.ClusterRepository;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-10-12T11:29:25.083+02:00"
)
@Controller
public class ClusterApiController implements ClusterApi {

    private static final Logger log = LoggerFactory.getLogger(ClusterApiController.class);

    @Autowired
    private ClusterLauncher clusterLauncher;

    @Autowired
    private ClusterRepository clusterRepository;

    public ResponseEntity<String> addWorker(
            @ApiParam(value = "ID of Cluster", required = true) @PathVariable("id") String id,
            @ApiParam(
                    value = "The ID of either a Worker or a VDU, which will be added to the cluster",
                    required = true
            )
            @PathVariable("machineId")
                    String machineId) {
        try {
            Cluster cluster = clusterLauncher.addNode(id, machineId);
            return new ResponseEntity<String>(cluster.toString(), HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } catch (SftpException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } catch (JSchException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Cluster> createCluster(
            @ApiParam(value = "Body to create Cluster from ResourceGroup", required = true)
            @Valid
            @RequestBody
                    ClusterFromResourceGroup clusterFromResourceGroup) {
        try {
            return new ResponseEntity<Cluster>(
                    clusterLauncher.createCluster(clusterFromResourceGroup), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Cluster>(HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<Cluster>(HttpStatus.BAD_REQUEST);
        } catch (SftpException e) {
            e.printStackTrace();
            return new ResponseEntity<Cluster>(HttpStatus.BAD_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity<Cluster>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<Cluster>(HttpStatus.NOT_FOUND);
        } catch (JSchException e) {
            e.printStackTrace();
            return new ResponseEntity<Cluster>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteCluster(
            @ApiParam(value = "ID of Cluster", required = true) @PathVariable("id") String id) {
        clusterRepository.delete(id);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    public ResponseEntity<List<Cluster>> getAllClusters() {
        List<Cluster> clusters = Lists.newArrayList(clusterRepository.findAll());
        return new ResponseEntity<List<Cluster>>(clusters, HttpStatus.OK);
    }

    public ResponseEntity<Cluster> removeNode(@ApiParam(value = "ID of Cluster",required=true) @PathVariable("id") String id,
                                             @ApiParam(value = "The ID of a Worker",required=true) @PathVariable("workerId") String workerId) {

        try {
            Cluster cluster = clusterLauncher.removeNode(id, workerId);
            return new ResponseEntity<Cluster>(cluster, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<Cluster>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> setUpCluster(
            @ApiParam(value = "ID of Cluster", required = true) @PathVariable("id") String id,
            @ApiParam(value = "type of technology", required = true) @PathVariable("type") String type) {
        try {
            Cluster cluster = clusterLauncher.installCluster(id, type);
            return new ResponseEntity<String>(cluster.toString(), HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(
                    "Couldnt find a cluster with id " + id, HttpStatus.NOT_FOUND);
        }
    }
}
