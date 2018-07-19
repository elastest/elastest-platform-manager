package io.elastest.epm.api;

import io.elastest.epm.api.body.CommandExecutionBody;
import io.elastest.epm.api.body.FileDownloadBody;
import io.elastest.epm.api.body.FileUploadBody;
import io.elastest.epm.core.RuntimeManagement;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2017-08-17T16:30:49.905+02:00"
)
@Controller
public class RuntimeApiController implements RuntimeApi {

    @Autowired
    private RuntimeManagement runtimeManagement;

    public ResponseEntity<File> downloadFileFromInstance(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
            @ApiParam(
                    value = "Contains details of the file to be downloaded from the given Instance",
                    required = true
            )
            @RequestBody
                    FileDownloadBody fileDownloadBody)
            throws AdapterException, NotFoundException, IOException {
        // do some magic!
        InputStream is = runtimeManagement.downloadFileFromInstance(id, fileDownloadBody.getPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Content-Disposition", "attachment; filename=" + fileDownloadBody.getPath());
        ResponseEntity responseEntity =
                new ResponseEntity(IOUtils.toByteArray(is), headers, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<String> executeOnInstance(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
            @ApiParam(
                    value =
                            "Contains command to be executed and a flag if for the completion of the execution should be awaited",
                    required = true
            )
            @RequestBody
                    CommandExecutionBody commandExecutionBody)
            throws AdapterException, NotFoundException {
        // do some magic!
        String output =
                runtimeManagement.executeOnInstance(
                        id, commandExecutionBody.getCommand(), commandExecutionBody.getAwaitCompletion());
        return new ResponseEntity<String>(output, HttpStatus.OK);
    }

    public ResponseEntity<Void> startInstance(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
            throws AdapterException, NotFoundException {
        // do some magic!
        runtimeManagement.startInstance(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> stopInstance(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id)
            throws AdapterException, NotFoundException {
        // do some magic!
        runtimeManagement.stopInstance(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> uploadFileToInstanceByFile(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Absolute path where the file should go on the Instance", required = true)
            @RequestPart(value = "remotePath", required = true)
                    String remotePath,
            @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file)
            throws AdapterException, BadRequestException, NotFoundException, IOException {
        // do some magic!
        runtimeManagement.uploadFileToInstanceByFile(id, remotePath, file);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> uploadFileToInstanceByPath(
            @ApiParam(value = "ID of VDU", required = true) @PathVariable("id") String id,
            @ApiParam(
                    value = "Contains details of the file with the given path to upload to the Instance",
                    required = true
            )
            @RequestBody
                    FileUploadBody fileUploadBody)
            throws AdapterException, BadRequestException, NotFoundException, IOException {
        // do some magic!
        runtimeManagement.uploadFileToInstanceByPath(
                id, fileUploadBody.getRemotePath(), fileUploadBody.getHostPath());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
