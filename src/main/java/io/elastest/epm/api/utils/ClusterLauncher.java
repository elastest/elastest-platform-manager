package io.elastest.epm.api.utils;

import com.google.protobuf.ByteString;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.elastest.epm.api.body.ClusterFromResourceGroup;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.model.Cluster;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.model.Worker;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.generated.InstallMessage;
import io.elastest.epm.pop.generated.Key;
import io.elastest.epm.pop.generated.MetadataEntry;
import io.elastest.epm.pop.generated.OperationHandlerGrpc;
import io.elastest.epm.pop.generated.StringResponse;
import io.elastest.epm.pop.generated.TerminateMessage;
import io.elastest.epm.properties.ElastestProperties;
import io.elastest.epm.repository.ClusterRepository;
import io.elastest.epm.repository.KeyRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.elastest.epm.repository.WorkerRepository;
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
    VduRepository vduRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    Utils utils;
    @Autowired
    WorkerLauncher workerLauncher;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private PoPRepository poPRepository;
    @Autowired
    private ElastestProperties elastestProperties;

    @Value("${et.public.host}")
    private String epmIp;

    public Cluster createCluster(ClusterFromResourceGroup clusterFromResourceGroup)
            throws InterruptedException, BadRequestException, IOException, JSchException,
            NotFoundException, SftpException {
        ResourceGroup rg =
                resourceGroupRepository.findOne(clusterFromResourceGroup.getResourceGroupId());
        if (rg == null) {
            throw new NotFoundException("No resource group with id: " + clusterFromResourceGroup.getResourceGroupId() + " was found");
        }
        List<VDU> nodes = new ArrayList<>();
        VDU masterVDU = null;
        for (VDU vdu : rg.getVdus()) {
            if (vdu.getId().equals(clusterFromResourceGroup.getMasterId())) masterVDU = vdu;
            else nodes.add(vdu);
        }

        List<String> type = clusterFromResourceGroup.getType();
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
                            utils.getAdapterClient(adapter, "ansible");
                    List<String> nodeIps = new ArrayList<>();
                    for (Worker node : cluster.getNodes()) {
                        nodeIps.add(node.getIp());
                    }
                    List<MetadataEntry> metadataEntries = new ArrayList<>();
                    log.debug(elastestProperties.toString());
                    if(elastestProperties.getEmp().getKubernetes().isEnabled()) {
                        log.debug("EPM Kubernetes is enabled");
                        metadataEntries.add(MetadataEntry.newBuilder().setKey("EMP_ENDPOINT").setValue(elastestProperties.getEmp().getKubernetes().getEndPoint()).build());
                        metadataEntries.add(MetadataEntry.newBuilder().setKey("EMP_PORT").setValue(elastestProperties.getEmp().getKubernetes().getPort()).build());
                        metadataEntries.add(MetadataEntry.newBuilder().setKey("EMP_TOPIC").setValue(elastestProperties.getEmp().getKubernetes().getTopic()).build());
                        metadataEntries.add(MetadataEntry.newBuilder().setKey("EMP_SERIES").setValue(elastestProperties.getEmp().getKubernetes().getSeriesName()).build());
                    }

                    InstallMessage createClusterMessage =
                            InstallMessage.newBuilder()
                                    .setType("kubernetes")
                                    .setMasterIp(cluster.getMaster().getIp())
                                    .addAllNodesIp(nodeIps)
                                    .setKey(Key.newBuilder().setKey(ByteString.copyFromUtf8(key.getKey())).build())
                                    .addAllMetadata(metadataEntries)
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


    public Cluster addNode(String clusterId, String id) throws InterruptedException, SftpException,
            BadRequestException, JSchException, IOException, NotFoundException {

        Cluster cluster = clusterRepository.findOne(clusterId);
        if (cluster == null) throw new NotFoundException("No cluster found with id: " + clusterId);

        VDU vdu;
        Worker worker = (vdu = vduRepository.findOne(id)) == null ? workerRepository.findOne(id) : workerLauncher.workerFromVDU(vdu, cluster.getMaster().getType());

        log.debug("Adding node to Cluster: " + cluster.getId());
        Adapter adapter = utils.getAdapter("ansible");
        try {
            io.elastest.epm.model.Key key =
                    keyRepository.findOneByName(cluster.getMaster().getAuthCredentials().getKey());

            OperationHandlerGrpc.OperationHandlerBlockingStub client =
                    utils.getAdapterClient(adapter, "ansible");

            InstallMessage addNodeMessage =
                    InstallMessage.newBuilder()
                            .setType("kubernetes-node")
                            .setMasterIp(cluster.getMaster().getIp())
                            .addNodesIp(worker.getIp())
                            .setKey(Key.newBuilder().setKey(ByteString.copyFromUtf8(key.getKey())).build())
                            .build();
            StringResponse s = client.createCluster(addNodeMessage);
            int status = Integer.parseInt(s.getResponse());
            if (status == 0) {
                // add node
                cluster.addNodesItem(worker);
            }
            return clusterRepository.save(cluster);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return cluster;
        }
    }

    public Cluster removeNode(String clusterId, String nodeId) throws NotFoundException {
        Cluster cluster = clusterRepository.findOne(clusterId);
        if (cluster == null) throw new NotFoundException("No cluster found with id: " + clusterId);

        Worker node = null;
        for( Worker w: cluster.getNodes()) {
            if ( w.getId().equals(nodeId) ) {
                node = w;
            }
        }
        if (node == null) throw new NotFoundException("No node found with id: " + nodeId);
        Adapter adapter = utils.getAdapter("ansible");
        OperationHandlerGrpc.OperationHandlerBlockingStub client =
                utils.getAdapterClient(adapter, "ansible");

        VDU nodeVDU;
        log.info("Removing node from Cluster: " + cluster.getId());

        // Disable node
        io.elastest.epm.model.Key key =
                keyRepository.findOneByName(node.getAuthCredentials().getKey());

        InstallMessage removeNodeMessage =
                InstallMessage.newBuilder()
                        .setType("kubernetes-node-remove")
                        .setMasterIp(cluster.getMaster().getIp())
                        .addNodesIp(node.getIp())
                        .setKey(Key.newBuilder().setKey(ByteString.copyFromUtf8(key.getKey())).build())
                        .build();

        StringResponse s = client.createCluster(removeNodeMessage);
        int status = Integer.parseInt(s.getResponse());
        if (status == 0) {
            // add node
            cluster.getNodes().remove(node);
        }

        /*if ((nodeVDU = vduRepository.findOne(node.getVduId())) != null) {
            PoP poP = poPRepository.findOneByName(nodeVDU.getPoPName());
            if (poP == null) throw new NotFoundException("Pop not found for VDU " + nodeVDU);

            // Shutdown node
            TerminateMessage terminateMessage = TerminateMessage.newBuilder()
                    .setResourceId(nodeVDU.getId())
                    .setPop(utils.parsePoP(poP))
                    .addVdu(utils.parseVDU(nodeVDU))
                    .build();
            client.remove(terminateMessage);


        }*/
        
        return clusterRepository.save(cluster);
    }
}
