package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.OperationalState;

import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/*This clause describes the attributes for the VirtualNetwork information element.*/
public class VirtualNetwork extends NfvEntity {

    /*Identifier of the virtualised network resource.*/
    @NotEmpty
    private String networkResourceId;

    /*Name of the virtualised network resource.*/
    private String networkResourceName;

    /*Only present if the network provides layer 3 connectivity. See clause 8.4.5.3.*/
    private List<NetworkSubnet> subnet;

    /*Element providing information of an instantiated virtual network port*/
    private Set<VirtualNetworkPort> networkPort;

    /*Minimum network bandwidth (in Mbps).*/
    @NotEmpty
    private long bandwidth;

    /*The type of network that maps to the virtualised network. This list is extensible. Examples are: "local", "vlan", "vxlan", "gre", "l3-vpn", etc.*/
    @NotEmpty
    private String networkType;

    /*The isolated segment for the virtualised network. For instance, for a "vlan" networkType, it corresponds to the vlan identifier; and for a "gre" networkType, this corresponds to a gre key. The cardinality can be "0" for flat networks without any specific segmentation.*/
    private String segmentType;

    /*Element providing information about Quality of Service attributes that the network supports. See clause 8.4.4.3. Cardinality can be "0" for virtual network without any QoS requirements.*/
    private Set<NetworkQoS> networkQoS;

    /*It defines whether the virtualised network is shared among consumers.*/
    @NotEmpty
    private boolean isShared;

    /*Only present for shared networks. Indicate the sharing criteria for this network. This criteria might be a list of authorized consumers.*/
    //TODO type not specified
    private String sharingCriteria;

    /*If present, it identifies the Resource Zone where the virtual network resources have been allocated.*/
    private String zoneId;

    /*The operational state of the virtualised network.*/
    @NotEmpty
    private OperationalState operationalState;

    /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
    private Set<KeyValuePair> metadata;

    public String getNetworkResourceId() {
        return networkResourceId;
    }

    public void setNetworkResourceId(String networkResourceId) {
        this.networkResourceId = networkResourceId;
    }

    public String getNetworkResourceName() {
        return networkResourceName;
    }

    public void setNetworkResourceName(String networkResourceName) {
        this.networkResourceName = networkResourceName;
    }

    public List<NetworkSubnet> getSubnet() {
        return subnet;
    }

    public void setSubnet(List<NetworkSubnet> subnet) {
        this.subnet = subnet;
    }

    public Set<VirtualNetworkPort> getNetworkPort() {
        return networkPort;
    }

    public void setNetworkPort(Set<VirtualNetworkPort> networkPort) {
        this.networkPort = networkPort;
    }

    public long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(long bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }

    public Set<NetworkQoS> getNetworkQoS() {
        return networkQoS;
    }

    public void setNetworkQoS(Set<NetworkQoS> networkQoS) {
        this.networkQoS = networkQoS;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public String getSharingCriteria() {
        return sharingCriteria;
    }

    public void setSharingCriteria(String sharingCriteria) {
        this.sharingCriteria = sharingCriteria;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public OperationalState getOperationalState() {
        return operationalState;
    }

    public void setOperationalState(OperationalState operationalState) {
        this.operationalState = operationalState;
    }

    public Set<KeyValuePair> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<KeyValuePair> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "VirtualNetwork{"
                + "networkResourceId='"
                + networkResourceId
                + '\''
                + ", networkResourceName='"
                + networkResourceName
                + '\''
                + ", subnet="
                + subnet
                + ", networkPort="
                + networkPort
                + ", bandwidth="
                + bandwidth
                + ", networkType='"
                + networkType
                + '\''
                + ", segmentType='"
                + segmentType
                + '\''
                + ", networkQoS="
                + networkQoS
                + ", isShared="
                + isShared
                + ", sharingCriteria='"
                + sharingCriteria
                + '\''
                + ", zoneId='"
                + zoneId
                + '\''
                + ", operationalState="
                + operationalState
                + ", metadata="
                + metadata
                + '}';
    }
}
