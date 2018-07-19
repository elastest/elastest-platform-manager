package io.elastest.epm.api;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.adapter.GenericAdapter;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.interfaces.AdapterBrokerInterface;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.swagger.annotations.*;

import java.io.IOException;
import java.util.Map;
import javax.validation.constraints.*;

import org.apache.commons.compress.archivers.ArchiveException;
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

    @Autowired
    private GenericAdapter adapter;
    @Autowired
    private ResourceGroupRepository resourceGroupRepository;
    @Autowired
    private PoPRepository poPRepository;

    public ResponseEntity<Void> deletePackage(
            @ApiParam(value = "ID of Package", required = true) @PathVariable("id") String id) {
        ResourceGroup resourceGroup = resourceGroupRepository.findOne(id);
        if (resourceGroup == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        if (resourceGroup != null) {
            try {
                adapter.terminate(resourceGroup.getName());
            } catch (NotFoundException e) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResourceGroup> receivePackage(
            @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file)
            throws IOException, ArchiveException {
        log.debug("Received package");
        log.debug("Name: " + file.getOriginalFilename());

        try {
            ResourceGroup resourceGroup;
            resourceGroup = adapter.deploy(file.getInputStream());
            return new ResponseEntity<ResourceGroup>(HttpStatus.OK).ok(resourceGroup);
        } catch (IOException exception) {
            return new ResponseEntity<ResourceGroup>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<ResourceGroup>(HttpStatus.NOT_FOUND);
        } catch (AllocationException e) {
            e.printStackTrace();
            return new ResponseEntity<ResourceGroup>(HttpStatus.BAD_REQUEST);
        } catch (AdapterException e) {
            e.printStackTrace();
            return new ResponseEntity<ResourceGroup>(HttpStatus.BAD_REQUEST);
        }
    }
}
