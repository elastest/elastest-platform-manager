package io.elastest.epm.pop.adapter.docker;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.*;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import io.elastest.epm.properties.DockerProperties;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.rmi.CORBA.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Component
public class DockerAdapterProto implements PackageManagementInterface, RuntimeManagmentInterface {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired private Utils utils;
    @Autowired private PoPRepository poPRepository;
    @Autowired private ResourceGroupRepository resourceGroupRepository;
    @Autowired private NetworkRepository networkRepository;
    @Autowired private VduRepository vduRepository;
    @Autowired private DockerProperties dockerProperties;


    private OperationHandlerGrpc.OperationHandlerBlockingStub getDockerAdapterClient(PoP poP) throws NotFoundException {
        return utils.getAdapterClient(utils.getAdapter( "docker"));
    }

    @Override
    public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException, ArchiveException, AdapterException, AllocationException, BadRequestException {
        PoP composePoP = poPRepository.findPoPForType("docker");
        return deploy(data, composePoP);
    }

    @Override
    public ResourceGroup deploy(InputStream data, PoP poP) throws NotFoundException, IOException, AdapterException, BadRequestException, AllocationException, ArchiveException {

        OperationHandlerGrpc.OperationHandlerBlockingStub dockerClient = getDockerAdapterClient(poP);
        ByteString p = ByteString.copyFrom(IOUtils.toByteArray(data));

        io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                .newBuilder().setInterfaceEndpoint(poP.getInterfaceEndpoint()).build();

        String enabled = "False";
        String address = "";
        if (dockerProperties.getLogStash().isEnabled()) {
            enabled = "True";
            if (dockerProperties.getLogStash().getAddress() != null
                    && !dockerProperties.getLogStash().getAddress().equals(""))
                address = dockerProperties.getLogStash().getAddress();
            else address = "tcp://localhost:5000";
        }

        FileMessage composePackage =
                FileMessage.newBuilder()
                        .setFile(p)
                        .setPop(popDeployment)
                        .addAllOptions(new ArrayList<String>())
                        .addOptions(enabled)
                        .addOptions(address)
                        .build();
        ResourceGroupProto rg = dockerClient.create(composePackage);

        ResourceGroup resourceGroup = utils.parseRGProto(rg, poP);

        resourceGroupRepository.save(resourceGroup);
        return resourceGroup;
    }

    @Override
    public void terminate(String packageId) throws NotFoundException {
        ResourceGroup resourceGroup = resourceGroupRepository.findOneByName(packageId);
        PoP poP = poPRepository.findOneByName(resourceGroup.getVdus().get(0).getPoPName());
        io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                .newBuilder().setInterfaceEndpoint(poP.getInterfaceEndpoint()).build();
        String computeId = resourceGroup.getVdus().get(0).getComputeId();
        OperationHandlerGrpc.OperationHandlerBlockingStub composeClient = getDockerAdapterClient(poP);
        ResourceIdentifier resourceIdentifier = ResourceIdentifier.newBuilder().setResourceId(computeId).setPop(popDeployment).build();
        composeClient.remove(resourceIdentifier);

        vduRepository.delete(resourceGroup.getVdus());
        networkRepository.delete(resourceGroup.getNetworks());
        resourceGroupRepository.delete(resourceGroup);
    }

    @Override
    public InputStream downloadFileFromInstance(VDU vdu, String filepath, PoP pop)
            throws AdapterException, NotFoundException {
        if (isRunning(vdu.getComputeId(), pop)) {
            OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
            io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                    .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
            log.debug("Downloading file");
            RuntimeMessage dockerRuntimeMessage =
                    RuntimeMessage.newBuilder()
                            .setResourceId(vdu.getComputeId())
                            .setPop(popDeployment)
                            .addAllProperty(new ArrayList<String>())
                            .addProperty(filepath)
                            .build();
            FileMessage response = client.downloadFile(dockerRuntimeMessage);
            return new ByteArrayInputStream(response.getFile().toByteArray());
        } else throw new AdapterException("Can't download file from stopped container.");
    }

    @Override
    public String executeOnInstance(VDU vdu, String command, boolean awaitCompletion, PoP pop)
            throws AdapterException, NotFoundException {
        if (isRunning(vdu.getComputeId(), pop)) {
            OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
            io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                    .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
            RuntimeMessage dockerRuntimeMessage =
                    RuntimeMessage.newBuilder()
                            .setResourceId(vdu.getComputeId())
                            .setPop(popDeployment)
                            .addAllProperty(new ArrayList<String>())
                            .addProperty(command)
                            .build();
            StringResponse response = client.executeCommand(dockerRuntimeMessage);
            return response.getResponse();
        } else throw new AdapterException("Can't execute command on stopped container.");
    }

    @Override
    public void startInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {

        if (existsContainer(vdu.getComputeId(), pop) && !isRunning(vdu.getComputeId(), pop)) {
            io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                    .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
            OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
            ResourceIdentifier resourceIdentifier =
                    ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId()).setPop(popDeployment).build();
            client.startContainer(resourceIdentifier);
        }
    }

    @Override
    public void stopInstance(VDU vdu, PoP pop) throws AdapterException, NotFoundException {

        if (isRunning(vdu.getComputeId(), pop)) {
            OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
            io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                    .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
            ResourceIdentifier resourceIdentifier =
                    ResourceIdentifier.newBuilder().setResourceId(vdu.getComputeId()).setPop(popDeployment).build();
            client.stopContainer(resourceIdentifier);
        }
    }

    @Override
    public void uploadFileToInstance(VDU vdu, String remotePath, String hostPath, PoP pop)
            throws AdapterException, IOException, NotFoundException {
        if (isRunning(vdu.getComputeId(), pop)) {
            OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
            io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                    .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
            RuntimeMessage dockerRuntimeMessage =
                    RuntimeMessage.newBuilder()
                            .setResourceId(vdu.getComputeId())
                            .setPop(popDeployment)
                            .addAllProperty(new ArrayList<String>())
                            .addProperty("withPath")
                            .addProperty(hostPath)
                            .addProperty(remotePath)
                            .build();
            client.uploadFile(dockerRuntimeMessage);
        } else throw new AdapterException("Can't upload a file to a stopped container.");
    }

    @Override
    public void uploadFileToInstance(VDU vdu, String remotePath, MultipartFile file, PoP pop)
            throws AdapterException, IOException, NotFoundException {
        io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
        if (isRunning(vdu.getComputeId(), pop)) {
            File output = Utils.convert(file);
            output.deleteOnExit();
            OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
            if (!file.getOriginalFilename().endsWith(".tar")) {
                output = Utils.compressFileToTar(output);
            }

            RuntimeMessage dockerRuntimeMessage =
                    RuntimeMessage.newBuilder()
                            .setResourceId(vdu.getComputeId())
                            .setPop(popDeployment)
                            .addAllProperty(new ArrayList<String>())
                            .addProperty(remotePath)
                            .setFile(ByteString.copyFrom(FileUtils.readFileToByteArray(output)))
                            .build();
            client.uploadFile(dockerRuntimeMessage);
            log.debug(String.valueOf("File deletion: " + output.delete()));
        } else throw new AdapterException("Can't upload a file to a stopped container.");
    }

    private boolean existsContainer(String containerId, PoP pop) throws NotFoundException {
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
        io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
        ResourceIdentifier resourceIdentifier =
                ResourceIdentifier.newBuilder().setResourceId(containerId).setPop(popDeployment).build();
        StringResponse stringResponse = client.checkIfContainerExists(resourceIdentifier);
        return Boolean.parseBoolean(stringResponse.getResponse().toLowerCase());
    }

    private boolean isRunning(String containerId, PoP pop) throws NotFoundException {
        io.elastest.epm.pop.generated.PoP popDeployment = io.elastest.epm.pop.generated.PoP
                .newBuilder().setInterfaceEndpoint(pop.getInterfaceEndpoint()).build();
        OperationHandlerGrpc.OperationHandlerBlockingStub client = getDockerAdapterClient(pop);
        ResourceIdentifier resourceIdentifier =
                ResourceIdentifier.newBuilder().setResourceId(containerId).setPop(popDeployment).build();
        StringResponse stringResponse = client.checkIfContainerRunning(resourceIdentifier);
        return Boolean.parseBoolean(stringResponse.getResponse().toLowerCase());
    }
}
