package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;

import java.util.List;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*The NetworkSubnetData information element encapsulates information to allocate or update virtualised sub-networks.*/
@Entity
public class NetworkSubnetData extends NfvEntity {

    /*The identifier of the virtualised network that the virtualised sub-network is attached to. The cardinality can be 0 to cover the case where this type is used to describe the L3 attributes of a network rather than a subnetwork.*/
    private String networkId;

    /*The IP version of the network/subnetwork.*/
    @NotEmpty
    private IpVersion ipVersion;

    /*Specifies the IP address of the network/subnetwork gateway when the gateway is selected by the requestor.*/
    private IpAddress gatewayIp;

    /*True when DHCP is to be enabled for this network/subnetwork, or false otherwise.*/
    private boolean isDhcpEnabled;

    /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
    private List<KeyValuePair> metadata;

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

    public List<KeyValuePair> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<KeyValuePair> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "NetworkSubnetData{"
                + "networkId='"
                + networkId
                + '\''
                + ", ipVersion="
                + ipVersion
                + ", gatewayIp="
                + gatewayIp
                + ", isDhcpEnabled="
                + isDhcpEnabled
                + ", metadata="
                + metadata
                + '}';
    }
}
