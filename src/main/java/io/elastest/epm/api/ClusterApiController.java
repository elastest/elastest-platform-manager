package io.elastest.epm.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.elastest.epm.api.body.ClusterFromResourceGroup;
import io.elastest.epm.api.utils.ClusterLauncher;
import io.elastest.epm.model.Cluster;
import io.elastest.epm.model.Worker;
import io.elastest.epm.repository.ClusterRepository;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-12T11:29:25.083+02:00")

@Controller
public class ClusterApiController implements ClusterApi {

    private static final Logger log = LoggerFactory.getLogger(ClusterApiController.class);

    @Autowired
    private ClusterLauncher clusterLauncher;

    @Autowired
    private ClusterRepository clusterRepository;

    public ResponseEntity<String> addWorker(@ApiParam(value = "ID of Cluster",required=true) @PathVariable("id") String id,@ApiParam(value = "The ID of either a Worker or a VDU, which will be added to the cluster",required=true) @PathVariable("machineId") String machineId) {

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Cluster> createCluster(@ApiParam(value = "Body to create Cluster from ResourceGroup" ,required=true )  @Valid @RequestBody ClusterFromResourceGroup clusterFromResourceGroup) {
        return new ResponseEntity<Cluster>(clusterLauncher.createCluster(clusterFromResourceGroup), HttpStatus.OK);

    }

    public ResponseEntity<String> deleteCluster(@ApiParam(value = "ID of Cluster",required=true) @PathVariable("id") String id) {
        clusterRepository.delete(id);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    public ResponseEntity<List<Cluster>> getAllClusters() {
        List<Cluster> clusters = Lists.newArrayList(clusterRepository.findAll());
        return new ResponseEntity<List<Cluster>>(clusters, HttpStatus.OK);
    }

    public ResponseEntity<String> setUpCluster(@ApiParam(value = "ID of Cluster",required=true) @PathVariable("id") String id,@ApiParam(value = "type of technology",required=true) @PathVariable("type") String type) {

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

}
