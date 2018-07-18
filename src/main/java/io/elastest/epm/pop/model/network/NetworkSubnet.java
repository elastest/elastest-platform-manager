package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.OperationalState;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/*The NetworkSubnet information element encapsulates information of an instantiated virtualised sub-network.*/
public class NetworkSubnet extends NfvEntity {

    /*Identifier of the virtualised sub-network.*/
    @NotEmpty
    private String resourceId;

    /*The identifier of the virtualised network that the virtualised sub-network is attached to. The cardinality can be 0 to cover the case where this type is used to describe the L3 attributes of a network rather than a subnetwork.*/
    private String networkId;

    /*The IP version of the network/subnetwork.*/
    @NotEmpty
    private IpVersion ipVersion;

    /*The IP address of the network/subnetwork gateway.*/
    @NotEmpty
    private IpAddress gatewayIp;

    /*True when DHCP is enabled for this network/subnetwork, or false otherwise.*/
    @NotEmpty
    private boolean isDhcpEnabled;

    /*The operational state of the virtualised sub-network.*/
    @NotEmpty
    private OperationalState operationalState;

    /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
    private List<KeyValuePair> metadata;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public IpVersion getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(IpVersion ipVersion) {
        this.ipVersion = ipVersion;
    }

    public IpAddress getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(IpAddress gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public boolean isDhcpEnabled() {
        return isDhcpEnabled;
    }

    public void setDhcpEnabled(boolean dhcpEnabled) {
        isDhcpEnabled = dhcpEnabled;
    }

    public OperationalState getOperationalState() {
        return operationalState;
    }

    public void setOperationalState(OperationalState operationalState) {
        this.operationalState = operationalState;
    }

    public List<KeyValuePair> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<KeyValuePair> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "NetworkSubnet{"
                + "resourceId='"
                + resourceId
                + '\''
                + ", networkId='"
                + networkId
                + '\''
                + ", ipVersion="
                + ipVersion
                + ", gatewayIp="
                + gatewayIp
                + ", isDhcpEnabled="
                + isDhcpEnabled
                + ", operationalState="
                + operationalState
                + ", metadata="
                + metadata
                + '}';
    }
}
