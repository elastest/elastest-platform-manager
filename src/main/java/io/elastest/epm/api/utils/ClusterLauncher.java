package io.elastest.epm.api.utils;

import io.elastest.epm.api.body.ClusterFromResourceGroup;
import io.elastest.epm.model.Cluster;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.repository.ClusterRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClusterLauncher {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClusterRepository clusterRepository;

    @Autowired
    ResourceGroupRepository resourceGroupRepository;

    @Value("${et.public.host}")
    private String epmIp;

    public Cluster createCluster(ClusterFromResourceGroup clusterFromResourceGroup) {
        ResourceGroup rg = resourceGroupRepository.findOne(clusterFromResourceGroup.getResourceGroupId());
        List<VDU> nodes = new ArrayList<>();
        VDU master = null;
        for (VDU vdu : rg.getVdus())
        {
            if (vdu.getId().equals(clusterFromResourceGroup.getMasterId())) master = vdu;
            else nodes.add(vdu);
        }
        installMaster(master, clusterFromResourceGroup.getType());
        installNodes(nodes, clusterFromResourceGroup.getType());
        return null;
    }

    public String addWorker(String clusterId, String workerId) {
        return "Not Implemented";
    }

    public String install(String id, String type) {
        return "";
    }


    private void installMaster(VDU vdu, List<String> type) {
        for (String t : type) {
            if (t.equals("kubernetes") || t.equals("k8s")) {

            }
            else log.warn("Type: " + t + " not supported yet.");
        }
    }

    private void installNodes(List<VDU> nodes, List<String> type) {
        for (String t : type) {
            if (t.equals("kubernetes") || t.equals("k8s")) {

            }
            else log.warn("Type: " + t + " not supported yet.");
        }
    }

    private void setupNodes() {

    }

    private void setupMaster() {

    }
}
