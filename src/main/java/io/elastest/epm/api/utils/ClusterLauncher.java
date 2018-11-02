package io.elastest.epm.api.utils;

import com.google.protobuf.ByteString;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.api.body.ClusterFromResourceGroup;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.Cluster;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.model.Worker;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.generated.CreateClusterMessage;
import io.elastest.epm.pop.generated.Key;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.StringResponse;
import io.elastest.epm.repository.ClusterRepository;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClusterLauncher {

    @Autowired
    ResourceGroupRepository resourceGroupRepository;
    @Autowired
    Utils utils;
    @Autowired
    WorkerLauncher workerLauncher;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Value("${et.public.host}")
    private String epmIp;

    public Cluster createCluster(ClusterFromResourceGroup clusterFromResourceGroup)
            throws InterruptedException, BadRequestException, IOException, JSchException,
            NotFoundException, SftpException {
        ResourceGroup rg =
                resourceGroupRepository.findOne(clusterFromResourceGroup.getResourceGroupId());
        List<VDU> nodes = new ArrayList<>();
        VDU masterVDU = null;
        for (VDU vdu : rg.getVdus()) {
            if (vdu.getId().equals(clusterFromResourceGroup.getMasterId())) masterVDU = vdu;
            else nodes.add(vdu);
        }

        List<String> type = clusterFromResourceGroup.getType();
        //type.add("kubernetes");
        Cluster cluster = new Cluster();
        Worker master = workerLauncher.workerFromVDU(masterVDU, type);
        cluster.setMaster(master);
        for (VDU node : nodes) cluster.addNodesItem(workerLauncher.workerFromVDU(node, type));
        cluster.setResourceGroupId(clusterFromResourceGroup.getResourceGroupId());

        for (String t : type) {
            cluster = installCluster(cluster, t);
        }
        return cluster;
    }

    public Cluster installCluster(String id, String type) throws NotFoundException {
        Cluster cluster = clusterRepository.findOne(id);
        if (cluster != null) {
            return installCluster(cluster, type);
        } else throw new NotFoundException("Not able to find cluster with id: " + id);
    }

    private Cluster installCluster(Cluster cluster, String type) {
        switch (type) {
            case "kubernetes":
                log.debug("Installing K8s");
                Adapter adapter = utils.getAdapter("ansible");
                try {
                    io.elastest.epm.model.Key key =
                            keyRepository.findOneByName(cluster.getMaster().getAuthCredentials().getKey());
                    OperationHandlerGrpc.OperationHandlerBlockingStub client =
                            utils.getAdapterClient(adapter);
                    List<String> nodeIps = new ArrayList<>();
                    for (Worker node : cluster.getNodes()) {
                        nodeIps.add(node.getIp());
                    }
                    CreateClusterMessage createClusterMessage =
                            CreateClusterMessage.newBuilder()
                                    .setMasterIp(cluster.getMaster().getIp())
                                    .addAllNodesIp(nodeIps)
                                    .setKey(Key.newBuilder().setKey(ByteString.copyFromUtf8(key.getKey())).build())
                                    .build();
                    StringResponse s = client.createCluster(createClusterMessage);
                    int status = Integer.parseInt(s.getResponse());
                    if (status == 0) {
                        cluster.setType("kubernetes");
                    }
                    return clusterRepository.save(cluster);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            default:
                return cluster;
        }
    }
}
