package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import java.util.Set;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The VirtualNetworkPortData information element encapsulates information to allocate or update virtual network ports for network resources. A network port is a communication endpoint under a network.*/
@Entity
public class VirtualNetworkPortData extends NfvEntity {

  /*Type of network port. Examples of types are access ports (layer 2 or 3), or trunk ports (layer 1) that become transport for multiple layer 2 or layer 3 networks.*/
  @NotEmpty private String portType;

  /*Identifier of the network that the port belongs to. When creating a port, such port needs to be part of a network.*/
  @NotEmpty private String networkId;

  /*The isolated segment the network port belongs to. For instance, for a "vlan", it corresponds to the vlan identifier; and for a "gre", this corresponds to a gre key. The cardinality can be "0" to allow for flat networks without any specific segmentation.*/
  private String segmentId;

  /*Number The bandwidth of the virtual network port (in Mbps). Cardinality can be "0" to allow for virtual network ports without any specified bandwidth requirements.*/
  private long bandwidth;

  /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
  private Set<KeyValuePair> metadata;

  public String getPortType() {
    return portType;
  }

  public void setPortType(String portType) {
    this.portType = portType;
  }

  public String getNetworkId() {
    return networkId;
  }

  public void setNetworkId(String networkId) {
    this.networkId = networkId;
  }

  public String getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(String segmentId) {
    this.segmentId = segmentId;
  }

  public long getBandwidth() {
    return bandwidth;
  }

  public void setBandwidth(long bandwidth) {
    this.bandwidth = bandwidth;
  }

  public Set<KeyValuePair> getMetadata() {
    return metadata;
  }

  public void setMetadata(Set<KeyValuePair> metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "VirtualNetworkPortData{"
        + "portType='"
        + portType
        + '\''
        + ", networkId='"
        + networkId
        + '\''
        + ", segmentId='"
        + segmentId
        + '\''
        + ", bandwidth="
        + bandwidth
        + ", metadata="
        + metadata
        + '}';
  }
}
