package io.elastest.epm.pop.adapter.docker;

import com.google.protobuf.ByteString;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.generated.FileMessage;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.ResourceGroupProto;
import io.elastest.epm.pop.generated.ResourceIdentifier;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Component
public class DockerAdapterProto implements PackageManagementInterface {

    @Autowired private Utils utils;
    @Autowired private PoPRepository poPRepository;
    @Autowired private ResourceGroupRepository resourceGroupRepository;
    @Autowired private NetworkRepository networkRepository;
    @Autowired private VduRepository vduRepository;

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

        FileMessage composePackage =
                FileMessage.newBuilder()
                        .setFile(p)
                        .setPop(popDeployment)
                        .addAllOptions(new ArrayList<String>())
                        //.addOptions(enabled)
                        //.addOptions(address)
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
}
