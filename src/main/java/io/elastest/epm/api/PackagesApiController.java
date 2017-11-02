package io.elastest.epm.api;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.adapter.compose.DockerComposeAdapter;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.validation.constraints.*;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-10-16T13:08:30.986+02:00"
)
@Controller
public class PackagesApiController implements PackagesApi {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private DockerComposeAdapter dockerComposeAdapter;
  @Autowired private ResourceGroupRepository resourceGroupRepository;

  public ResponseEntity<Void> deletePackage(
      @ApiParam(value = "ID of Package", required = true) @PathVariable("id") String id) {
    ResourceGroup resourceGroup = resourceGroupRepository.findOne(id);
    if (resourceGroup != null) {
      try {
        dockerComposeAdapter.terminate(resourceGroup.getName());
      } catch (NotFoundException e) {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<ResourceGroup> receivePackage(
      @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file) {
    log.debug("Received package");
    log.debug("Name: " + file.getOriginalFilename());

    try {
      ResourceGroup resourceGroup = dockerComposeAdapter.deploy(file.getInputStream());
      return new ResponseEntity<ResourceGroup>(HttpStatus.OK).ok(resourceGroup);
    } catch (IOException exception) {
      return new ResponseEntity<ResourceGroup>(HttpStatus.BAD_REQUEST);
    } catch (NotFoundException e) {
      e.printStackTrace();
      return new ResponseEntity<ResourceGroup>(HttpStatus.NOT_FOUND);
    }
  }
}
