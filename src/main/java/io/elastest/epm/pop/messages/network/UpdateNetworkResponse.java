package io.elastest.epm.pop.messages.network;

import io.elastest.epm.pop.model.network.NetworkSubnet;
import io.elastest.epm.pop.model.network.VirtualNetwork;
import io.elastest.epm.pop.model.network.VirtualNetworkPort;

public class UpdateNetworkResponse {

    /**
     * The identifier of the virtualised network resource that has been updated. This parameter has
     * the same value as the input parameter.
     */
    private String networkResourceId;
    /**
     * If network types are updated satisfactorily, it contains the data relative to the updated
     * network. Cardinality can be "0" if the request did not include update of such type of resource.
     * See clause 8.4.5.2.
     */
    private VirtualNetwork networkData;
    /**
     * If subnet types are updated satisfactorily, it contains the data relative to the updated
     * subnet. Cardinality can be "0" if the request did not include update of such type of resource.
     * See clause 8.4.5.3.
     */
    private NetworkSubnet subnetData;
    /**
     * If network port types are updated satisfactorily, it contains the data relative to the updated
     * network port. Cardinality can be "0" if the request did not include update of such type of
     * resource. See clause 8.4.5.4.
     */
    private VirtualNetworkPort networkPortData;

    public String getNetworkResourceId() {
        return networkResourceId;
    }

    public void setNetworkResourceId(String networkResourceId) {
        this.networkResourceId = networkResourceId;
    }

    public VirtualNetwork getNetworkData() {
        return networkData;
    }

    public void setNetworkData(VirtualNetwork networkData) {
        this.networkData = networkData;
    }

    public NetworkSubnet getSubnetData() {
        return subnetData;
    }

    public void setSubnetData(NetworkSubnet subnetData) {
        this.subnetData = subnetData;
    }

    public VirtualNetworkPort getNetworkPortData() {
        return networkPortData;
    }

    public void setNetworkPortData(VirtualNetworkPort networkPortData) {
        this.networkPortData = networkPortData;
    }

    @Override
    public String toString() {
        return "UpdateNetworkResponse{"
                + "networkResourceId='"
                + networkResourceId
                + '\''
                + ", networkData="
                + networkData
                + ", subnetData="
                + subnetData
                + ", networkPortData="
                + networkPortData
                + '}';
    }
}
