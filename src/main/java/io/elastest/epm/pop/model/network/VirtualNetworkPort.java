package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.OperationalState;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

/*The VirtualNetworkPort information element encapsulates information of an instantiated virtual network port. A network port resource is a communication endpoint instantiated under a network resource. */
public class VirtualNetworkPort extends NfvEntity {

  /*Identifier of the virtual network port.*/
  @NotEmpty private String resourceId;

  /*Identifier of the network that the port belongs to. When creating a port, such port needs to be part of a network.*/
  @NotEmpty private String networkId;

  /*Identifier of the attached resource to the network port (e.g. a virtualised compute resource, or identifier of the virtual network interface). The cardinality can be "0" if there is no specific resource connected to the network port.*/
  private String attachedResourceId;

  /*Type of network port. Examples of types are access ports (layer 2 or 3), or trunk ports (layer 1) that become transport for multiple layer 2 or layer 3 networks.*/
  @NotEmpty private String portType;

  /*The isolated segment the network port belongs to. For instance, for a "vlan", it corresponds to the vlan identifier; and for a "gre", this corresponds to a gre key. The cardinality can be "0" for flat networks without any specific segmentation.*/
  private String segmentId;

  /*The bandwidth of the virtual network port (in Mbps). Cardinality can be "0" for virtual network ports without any specific allocated bandwidth.*/
  private long bandwidth;

  /*The operational state of the virtualised network port.*/
  @NotEmpty private OperationalState operationalState;

  /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
  private Set<KeyValuePair> metadata;

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

  public String getAttachedResourceId() {
    return attachedResourceId;
  }

  public void setAttachedResourceId(String attachedResourceId) {
    this.attachedResourceId = attachedResourceId;
  }

  public String getPortType() {
    return portType;
  }

  public void setPortType(String portType) {
    this.portType = portType;
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
    return "VirtualNetworkPort{"
        + "resourceId='"
        + resourceId
        + '\''
        + ", networkId='"
        + networkId
        + '\''
        + ", attachedResourceId='"
        + attachedResourceId
        + '\''
        + ", portType='"
        + portType
        + '\''
        + ", segmentId='"
        + segmentId
        + '\''
        + ", bandwidth="
        + bandwidth
        + ", operationalState="
        + operationalState
        + ", metadata="
        + metadata
        + '}';
  }
}
