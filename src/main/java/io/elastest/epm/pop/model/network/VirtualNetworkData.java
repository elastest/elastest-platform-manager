package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;

import java.util.List;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*This clause describes the attributes for the VirtualNetworkData information element.*/
@Entity
public class VirtualNetworkData extends NfvEntity {

    /*Minimum network bandwidth (in Mbps).*/
    @NotEmpty
    private long bandwidth;

    /*The type of network that maps to the virtualised network. This list is extensible. Examples are: "local", "vlan", "vxlan", "gre", "l3-vpn", etc. The cardinality can be "0" to cover the case where this attribute is not required to create the virtualised network.*/
    private String networkType;

    /*The isolated segment for the virtualised network. For instance, for a "vlan" networkType, it corresponds to the vlan identifier; and for a "gre" networkType, this corresponds to a gre key. The cardinality can be "0" to allow for flat networks without any specific segmentation.*/
    private String segmentType;

    /*Element providing information about Quality of Service attributes that the network shall support. See clause 8.4.4.3. The cardinality can be "0" to allow for networks without any specified QoS requirements.*/
    private List<NetworkQoS> networkQoS;

    /*It defines whether the virtualised network is shared among consumers.*/
    private boolean isShared;

    /*Only present for shared networks. Indicate the sharing criteria for this network. This criteria might be a list of authorized consumers.*/
    //TODO type not specified
    private String sharingCriteria;

    /*The attribute allows setting up a network providing defined layer 3 connectivity. See clause 8.4.4.4 for further information on the attributes required for layer 3 connectivity.*/
    private List<NetworkSubnetData> layer3Attributes;

    /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
    private List<KeyValuePair> metadata;

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

    public List<NetworkQoS> getNetworkQoS() {
        return networkQoS;
    }

    public void setNetworkQoS(List<NetworkQoS> networkQoS) {
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

    public List<NetworkSubnetData> getLayer3Attributes() {
        return layer3Attributes;
    }

    public void setLayer3Attributes(List<NetworkSubnetData> layer3Attributes) {
        this.layer3Attributes = layer3Attributes;
    }

    public List<KeyValuePair> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<KeyValuePair> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "VirtualNetworkData{"
                + "bandwidth="
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
                + ", layer3Attributes="
                + layer3Attributes
                + ", metadata="
                + metadata
                + '}';
    }
}
