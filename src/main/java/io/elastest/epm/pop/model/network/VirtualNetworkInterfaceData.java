package io.elastest.epm.pop.model.network;

import java.util.List;
import java.util.Map;

public class VirtualNetworkInterfaceData {

    /**
     * In the case when the virtual network interface is attached to the network, it identifies such a
     * network. The cardinality can be 0 in the case that a network interface is created without being
     * attached to any specific network.
     */
    private String networkId;
    /**
     * If the virtual network interface is attached to a specific network port, it identifies such a
     * network port. The cardinality can be 0 in the case that a network interface is created without
     * any specific network port attachment.
     */
    private String networkPortId;
    /**
     * The virtual network interface can be configured with specific IP address(es) associated to the
     * network to be attached to. The cardinality can be 0 in the case that a network interface is
     * created without being attached to any specific network, or when an IP address can be
     * automatically configured, e.g. by DHCP.
     */
    private List<IpAddress> ipAddress;
    /**
     * Type of network interface. The type allows for defining how such interface is to be realized,
     * e.g. normal virtual NIC, with direct PCI pass-through, etc.
     */
    private VNicType typeVirtualNic;
    /**
     * Extra configuration that the virtual network interface supports based on the type of virtual
     * network interface, including support for SR-IOV with configuration of virtual functions (VF).
     */
    private List<ConfigurationType> typeConfiguration;
    /**
     * The MAC address desired for the virtual network interface. The cardinality can be 0 to allow
     * for network interface without specific MAC address configuration.
     */
    private MacAddress macAddress;
    /**
     * The bandwidth of the virtual network interface (in Mbps).
     */
    private int bandwidth;
    /**
     * It specifies if the virtual network interface requires certain acceleration capabilities (e.g.
     * RDMA, packet dispatch, TCP Chimney). The cardinality can be 0, if no particular acceleration
     * capability is requested.
     */
    private List<String> accelerationCapability;
    /**
     * List of metadata key-value pairs used by the consumer to associate meaningful metadata to the
     * related virtualised resource.
     */
    private List<Map.Entry<String, String>> metadata;

    public List<String> getAccelerationCapability() {
        return accelerationCapability;
    }

    public void setAccelerationCapability(List<String> accelerationCapability) {
        this.accelerationCapability = accelerationCapability;
    }

    public List<Map.Entry<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Map.Entry<String, String>> metadata) {
        this.metadata = metadata;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getNetworkPortId() {
        return networkPortId;
    }

    public void setNetworkPortId(String networkPortId) {
        this.networkPortId = networkPortId;
    }

    public List<IpAddress> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(List<IpAddress> ipAddress) {
        this.ipAddress = ipAddress;
    }

    public VNicType getTypeVirtualNic() {
        return typeVirtualNic;
    }

    public void setTypeVirtualNic(VNicType typeVirtualNic) {
        this.typeVirtualNic = typeVirtualNic;
    }

    public List<ConfigurationType> getTypeConfiguration() {
        return typeConfiguration;
    }

    public void setTypeConfiguration(List<ConfigurationType> typeConfiguration) {
        this.typeConfiguration = typeConfiguration;
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }

    public Number getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    @Override
    public String toString() {
        return "VirtualNetworkInterfaceData{"
                + "networkId='"
                + networkId
                + '\''
                + ", networkPortId='"
                + networkPortId
                + '\''
                + ", ipAddress="
                + ipAddress
                + ", typeVirtualNic="
                + typeVirtualNic
                + ", typeConfiguration="
                + typeConfiguration
                + ", macAddress="
                + macAddress
                + ", bandwidth="
                + bandwidth
                + ", accelerationCapability="
                + accelerationCapability
                + ", metadata="
                + metadata
                + '}';
    }
}
