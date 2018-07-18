package io.elastest.epm.pop.messages.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.network.NetworkSubnetData;
import io.elastest.epm.pop.model.network.VirtualNetworkData;
import io.elastest.epm.pop.model.network.VirtualNetworkPortData;

import java.util.List;

public class UpdateNetworkRequest {

    /**
     * Identifier of the virtualised network resource to update.
     */
    private String networkResourceId;
    /**
     * If update is on a network resource, the element contains the fields that can be updated. See
     * clause 8.4.4.2.
     */
    private VirtualNetworkData updateNetworkData;
    /**
     * If update is on a subnet resource, the element contains the fields that can be updated. See
     * clause 8.4.4.4.
     */
    private NetworkSubnetData updateSubnetData;
    /**
     * If update is on a network port, the element a contains the fields that can be updated. See
     * clause 8.4.4.5.
     */
    private VirtualNetworkPortData updateNetworkPort;
    /**
     * List of metadata key-value pairs used by the consumer to associate meaningful metadata to the
     * related virtualised resource.
     */
    private List<KeyValuePair> metaData;

    public VirtualNetworkData getUpdateNetworkData() {
        return updateNetworkData;
    }

    public void setUpdateNetworkData(VirtualNetworkData updateNetworkData) {
        this.updateNetworkData = updateNetworkData;
    }

    public NetworkSubnetData getUpdateSubnetData() {
        return updateSubnetData;
    }

    public void setUpdateSubnetData(NetworkSubnetData updateSubnetData) {
        this.updateSubnetData = updateSubnetData;
    }

    public VirtualNetworkPortData getUpdateNetworkPort() {
        return updateNetworkPort;
    }

    public void setUpdateNetworkPort(VirtualNetworkPortData updateNetworkPort) {
        this.updateNetworkPort = updateNetworkPort;
    }

    public List<KeyValuePair> getMetaData() {
        return metaData;
    }

    public void setMetaData(List<KeyValuePair> metaData) {
        this.metaData = metaData;
    }

    public String getNetworkResourceId() {

        return networkResourceId;
    }

    public void setNetworkResourceId(String networkResourceId) {
        this.networkResourceId = networkResourceId;
    }

    @Override
    public String toString() {
        return "UpdateNetworkRequest{"
                + "networkResourceId='"
                + networkResourceId
                + '\''
                + ", updateNetworkData="
                + updateNetworkData
                + ", updateSubnetData="
                + updateSubnetData
                + ", updateNetworkPort="
                + updateNetworkPort
                + ", metaData="
                + metaData
                + '}';
    }
}
