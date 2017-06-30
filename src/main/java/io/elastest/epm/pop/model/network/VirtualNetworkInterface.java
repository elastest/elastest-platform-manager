package io.elastest.epm.pop.model.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.OperationalState;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

/*A virtual network interface resource is a communication endpoint under an instantiated compute resource.*/
public class VirtualNetworkInterface extends NfvEntity {

  /*Identifier Identifier of the virtual network interface.*/
  @NotEmpty private String resourceId;

  /*Identifier Identifier of the owner of the network interface (e.g. a virtualised compute resource).*/
  @NotEmpty String ownerId;

  /*In the case when the virtual network interface is attached to the network, it identifies such a network. The cardinality can be 0 in the case that a network interface is created without being attached to any specific network.*/
  private String networkId;

  /*If the virtual network interface is attached to a specific network port, it identifies such a network port. The cardinality can be 0 in the case that a network interface is created without any specific network port attachment.*/
  private String networkPortId;

  /*The virtual network interface can be configured with specific IP address(es) associated to the network to be attached to. The cardinality can be 0 in the case that a network interface is created without being attached to any specific network, or when an IP address can be automatically configured, e.g. by DHCP.*/
  private Set<IpAddress> ipAddress;

  /*Type of network interface. The type allows for defining how such interface is to be realized, e.g. normal virtual NIC, with direct PCI passthrough, etc.*/
  @NotEmpty private String typeVirtualNic;

  /*Extra configuration that the virtual network interface supports based on the type of virtual network interface, including support for SRIOV with configuration of virtual functions (VF).*/
  //TODO type not specified
  private Set<String> typeConfiguration;

  /*The MAC address of the virtual network interface.*/
  @NotEmpty private MacAddress macAddress;

  /*The bandwidth of the virtual network interface (in Mbps).*/
  @NotEmpty private long bandwidth;

  /*It specifies if the virtual network interface requires certain acceleration capabilities (e.g. RDMA, packet dispatch, TCP Chimney). The cardinality can be 0, if no particular acceleration capability is provided.*/
  //TODO type not specified
  private Set<String> accelerationCapability;

  /*The operational state of the virtual network interface.*/
  @NotEmpty private OperationalState operationalState;

  /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
  private Set<KeyValuePair> metadata;

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
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

  public Set<IpAddress> getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(Set<IpAddress> ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getTypeVirtualNic() {
    return typeVirtualNic;
  }

  public void setTypeVirtualNic(String typeVirtualNic) {
    this.typeVirtualNic = typeVirtualNic;
  }

  public Set<String> getTypeConfiguration() {
    return typeConfiguration;
  }

  public void setTypeConfiguration(Set<String> typeConfiguration) {
    this.typeConfiguration = typeConfiguration;
  }

  public MacAddress getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(MacAddress macAddress) {
    this.macAddress = macAddress;
  }

  public long getBandwidth() {
    return bandwidth;
  }

  public void setBandwidth(long bandwidth) {
    this.bandwidth = bandwidth;
  }

  public Set<String> getAccelerationCapability() {
    return accelerationCapability;
  }

  public void setAccelerationCapability(Set<String> accelerationCapability) {
    this.accelerationCapability = accelerationCapability;
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
    return "VirtualNetworkInterface{"
        + "resourceId='"
        + resourceId
        + '\''
        + ", ownerId='"
        + ownerId
        + '\''
        + ", networkId='"
        + networkId
        + '\''
        + ", networkPortId='"
        + networkPortId
        + '\''
        + ", ipAddress="
        + ipAddress
        + ", typeVirtualNic='"
        + typeVirtualNic
        + '\''
        + ", typeConfiguration="
        + typeConfiguration
        + ", macAddress="
        + macAddress
        + ", bandwidth="
        + bandwidth
        + ", accelerationCapability="
        + accelerationCapability
        + ", operationalState="
        + operationalState
        + ", metadata="
        + metadata
        + '}';
  }
}
