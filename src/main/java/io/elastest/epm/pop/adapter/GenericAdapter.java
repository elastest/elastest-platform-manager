package io.elastest.epm.pop.adapter;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.*;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.elastest.epm.tosca.templates.service.Metadata;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

@Component
public class GenericAdapter implements PackageManagementInterface, RuntimeManagmentInterface {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PoPRepository poPRepository;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private VduRepository vduRepository;

    @Autowired
    private ResourceGroupRepository resourceGroupRepository;

    @Autowired
    private DockerProperties dockerProperties;

    @Autowired
    private Utils utils;

    private OperationHandlerGrpc.OperationHandlerBlockingStub getClient(PoP poP) throws NotFoundException {
        return utils.getAdapterClient(utils.getAdapterSpecific(poP));
    }

    @Override
    public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException, ArchiveException, AdapterException, AllocationException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = data.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
        InputStream is2 = new ByteArrayInputStream(baos.toByteArray());

        Map<String, Object> values = Utils.extractMetadata(is1);
        if (values == null) {
            throw new NotFoundException("No metadata found in the package: The metadata has to be in the root of the tar.");
        }

        String type = "";
        if (values.containsKey("type")) {
            type = String.valueOf(values.get("type"));
        }
        if (type.equals(""))
            throw new NotFoundException("No type found in the metadata of the package. As a minimum you need to specify either the type or the pop.");

        PoP poP;
        if (values.containsKey("pop")) {
            log.debug("Pop specified: " + String.valueOf(values.get("pop")));
            poP = poPRepository.findOneByName(String.valueOf(values.get("pop")));
        } else {
            poP = poPRepository.findPoPForType(type);
        }

        if (poP != null) return deploy(is2, poP);
        else
            throw new NotFoundException("No pop of type: " + type + " was found! Please start an adapter of type: " + type + " " +
                    "or make sure the adapter was able to reach the EPM so that it registers itself.");
    }

    @Override
    public ResourceGroup deploy(InputStream data, PoP poP) throws NotFoundException, IOException, AdapterException, AllocationException, ArchiveException {
        log.debug("Deploying package on pop: " + poP.toString());
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(poP);
        ByteString p = ByteString.copyFrom(IOUtils.toByteArray(data));
        log.debug("Creating message");
        FileMessage fileMessage =
                FileMessage.newBuilder()
                        .setFile(p)
                        .setPop(parsePoP(poP))
                        .addAllMetadata(parseLaunchOptions())
                        .build();
        log.debug("Sending message");
        ResourceGroupProto rg = client.create(fileMessage);
        ResourceGroup resourceGroup = utils.parseRGProto(rg, poP);
        resourceGroupRepository.save(resourceGroup);
        return resourceGroup;
    }

    @Override
    public void terminate(String packageId) throws NotFoundException {
        ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);
        PoP poP = poPRepository.findOneByName(resourceGroup.getVdus().get(0).getPoPName());
        OperationHandlerGrpc.OperationHandlerBlockingStub composeClient = getClient(poP);
        TerminateMessage terminateMessage = TerminateMessage.newBuilder()
                .setResourceId(resourceGroup.getName())
                .setPop(parsePoP(poP))
                .setVdu(parseVDU(resourceGroup.getVdus().get(0)))
                .build();
        composeClient.remove(terminateMessage);

        vduRepository.delete(resourceGroup.getVdus());
        networkRepository.delete(resourceGroup.getNetworks());
        resourceGroupRepository.delete(resourceGroup);
    }

    @Override
    public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop) throws AdapterException, NotFoundException {
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(pop);
        log.debug("Downloading file");
        RuntimeMessage dockerRuntimeMessage =
                RuntimeMessage.newBuilder()
                        .setVdu(parseVDU(vdu))
                        .setPop(parsePoP(pop))
                        .addAllProperty(new ArrayList<String>())
                        .addProperty(filepath)
                        .build();
        FileMessage response = client.downloadFile(dockerRuntimeMessage);
        return new ByteArrayInputStream(response.getFile().toByteArray());
    }

    @Override
    public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop) throws AdapterException, NotFoundException {
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(pop);

        RuntimeMessage dockerRuntimeMessage =
                RuntimeMessage.newBuilder()
                        .setVdu(parseVDU(vdu))
                        .setPop(parsePoP(pop))
                        .addAllProperty(new ArrayList<String>())
                        .addProperty(command)
                        .build();
        StringResponse response = client.executeCommand(dockerRuntimeMessage);
        return response.getResponse();
    }

    @Override
    public void startInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(pop);
        ResourceIdentifier resourceIdentifier =
                ResourceIdentifier.newBuilder()
                        .setResourceId(vdu.getComputeId())
                        .setPop(parsePoP(pop))
                        .setVdu(parseVDU(vdu))
                        .build();
        client.start(resourceIdentifier);
    }

    @Override
    public void stopInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(pop);
        ResourceIdentifier resourceIdentifier =
                ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId())
                        .setPop(parsePoP(pop))
                        .setVdu(parseVDU(vdu)).build();
        client.stop(resourceIdentifier);
    }

    @Override
    public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop) throws AdapterException, IOException, NotFoundException {
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(pop);

        RuntimeMessage dockerRuntimeMessage =
                RuntimeMessage.newBuilder()
                        .setVdu(parseVDU(vdu))
                        .setPop(parsePoP(pop))
                        .addAllProperty(new ArrayList<String>())
                        .addProperty("withPath")
                        .addProperty(hostPath)
                        .addProperty(remotePath)
                        .build();
        client.uploadFile(dockerRuntimeMessage);
    }

    @Override
    public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop) throws AdapterException, IOException, NotFoundException {
        File output = Utils.convert(file);
        output.deleteOnExit();
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getClient(pop);
        if (!file.getOriginalFilename().endsWith(".tar")) {
            output = Utils.compressFileToTar(output);
        }

        RuntimeMessage dockerRuntimeMessage =
                RuntimeMessage.newBuilder()
                        .setVdu(parseVDU(vdu))
                        .setPop(parsePoP(pop))
                        .addAllProperty(new ArrayList<String>())
                        .addProperty(remotePath)
                        .setFile(ByteString.copyFrom(FileUtils.readFileToByteArray(output)))
                        .build();
        client.uploadFile(dockerRuntimeMessage);
        log.debug(String.valueOf("File deletion: " + output.delete()));
    }

    private io.elastest.epm.pop.generated.PoP parsePoP(PoP poP) {
        return io.elastest.epm.pop.generated.PoP.newBuilder()
                .setName(poP.getName())
                .setInterfaceEndpoint(poP.getInterfaceEndpoint())
                .build();
    }

    private io.elastest.epm.pop.generated.VDU parseVDU(VDU vdu) {

        ArrayList<MetadataEntry> metadataEntries = new ArrayList<>();
        for(KeyValuePair kvp : vdu.getMetadata()) {
            metadataEntries.add(MetadataEntry.newBuilder().setKey(kvp.getKey()).setValue(kvp.getValue()).build());
        }

        return io.elastest.epm.pop.generated.VDU.newBuilder()
                .setName(vdu.getName())
                .setImageName(vdu.getName())
                .setComputeId(vdu.getComputeId())
                .setIp(vdu.getIp())
                .setNetName(vdu.getName())
                .setPoPName(vdu.getPoPName())
                .addAllMetadata(metadataEntries)
                .build();
    }

    private ArrayList<MetadataEntry> parseLaunchOptions() {
        String enabled = "False";
        String address = "";
        if (dockerProperties.getLogStash().isEnabled()) {
            enabled = "True";
            if (dockerProperties.getLogStash().getAddress() != null
                    && !dockerProperties.getLogStash().getAddress().equals(""))
                address = dockerProperties.getLogStash().getAddress();
            else address = "tcp://localhost:5000";
        }

        ArrayList<MetadataEntry> out = new ArrayList<>();
        out.add(MetadataEntry.newBuilder().setKey("enabled").setValue(enabled).build());
        out.add(MetadataEntry.newBuilder().setKey("address").setValue(address).build());
        return out;
    }
}
