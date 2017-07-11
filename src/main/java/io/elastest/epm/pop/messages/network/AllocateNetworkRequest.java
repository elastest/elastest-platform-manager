package io.elastest.epm.pop.messages.network;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.network.NetworkResourceType;
import io.elastest.epm.pop.model.network.NetworkSubnetData;
import io.elastest.epm.pop.model.network.VirtualNetworkData;
import io.elastest.epm.pop.model.network.VirtualNetworkPortData;
import java.util.List;

public class AllocateNetworkRequest {

  /**
   * Name provided by the consumer for the virtualised network resource to allocate. It can be used
   * for identifying resources from consumer side.
   */
  private String networkResourceName;
  /**
   * Identifier of the resource reservation applicable to this virtualised resource management
   * operation.
   */
  private String reservationId;
  /**
   * Type of virtualised network resource. Possible values are: "network", "subnet", or
   * network-port.
   */
  private NetworkResourceType networkResourceType;
  /**
   * The network data provides information about the particular virtual network resource to create.
   * Cardinality can be "0" depending on the value of networkResourceType. See clause 8.4.4.2.
   */
  private VirtualNetworkData typeNetworkData;
  /**
   * The subnet data provides information about the particular sub-network resource to create.
   * Cardinality can be "0" depending on the value of networkResourceType. See clause 8.4.4.4.
   */
  private NetworkSubnetData typeSubnetData;
  /**
   * The network port data provides a information about the particular network port to create.
   * Cardinality can be "0" depending on the value of networkResourceType. See clause 8.4.4.5.
   */
  private VirtualNetworkPortData typeNetworkPortData;
  //  /**
  //   * Element with affinity information of the See clause 8.4.8.2.
  //   */
  //  private List<AffinityOrAntiAffinityConstraint> affinityConstraint;
  //  /**
  //   * Element with anti-affinity information of allocate. See clause 8.4.8.2.
  //   */
  //  private List<AffinityOrAntiAffinityConstraint> antiAffinityConstraint;
  /**
   * If present, it defines location constraints for the resource(s) to be allocated, e.g. in what
   * particular resource zone.
   */
  private String locationConstraints;
  /**
   * List of metadata key-value pairs used by the consumer to associate meaningful metadata to the
   * related virtualised resource.
   */
  private List<KeyValuePair> metaData;
  /**
   * Unique identifier of the "infrastructure resource group", logical grouping of virtual resources
   * assigned to a tenant within an Infrastructure Domain. Cardinality can be 0 if the consumer
   * credentials are implicitly associated to a specific resource group.
   */
  private String resourceGroupId;

  public String getNetworkResourceName() {
    return networkResourceName;
  }

  public void setNetworkResourceName(String networkResourceName) {
    this.networkResourceName = networkResourceName;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public NetworkResourceType getNetworkResourceType() {
    return networkResourceType;
  }

  public void setNetworkResourceType(NetworkResourceType networkResourceType) {
    this.networkResourceType = networkResourceType;
  }

  public VirtualNetworkData getTypeNetworkData() {
    return typeNetworkData;
  }

  public void setTypeNetworkData(VirtualNetworkData typeNetworkData) {
    this.typeNetworkData = typeNetworkData;
  }

  public NetworkSubnetData getTypeSubnetData() {
    return typeSubnetData;
  }

  public void setTypeSubnetData(NetworkSubnetData typeSubnetData) {
    this.typeSubnetData = typeSubnetData;
  }

  public VirtualNetworkPortData getTypeNetworkPortData() {
    return typeNetworkPortData;
  }

  public void setTypeNetworkPortData(VirtualNetworkPortData typeNetworkPortData) {
    this.typeNetworkPortData = typeNetworkPortData;
  }

  //  public List<AffinityOrAntiAffinityConstraint> getAffinityConstraint() {
  //    return affinityConstraint;
  //  }
  //
  //  public void setAffinityConstraint(List<AffinityOrAntiAffinityConstraint> affinityConstraint) {
  //    this.affinityConstraint = affinityConstraint;
  //  }
  //
  //  public List<AffinityOrAntiAffinityConstraint> getAntiAffinityConstraint() {
  //    return antiAffinityConstraint;
  //  }
  //
  //  public void setAntiAffinityConstraint(List<AffinityOrAntiAffinityConstraint> antiAffinityConstraint) {
  //    this.antiAffinityConstraint = antiAffinityConstraint;
  //  }

  public String getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(String locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public List<KeyValuePair> getMetaData() {
    return metaData;
  }

  public void setMetaData(List<KeyValuePair> metaData) {
    this.metaData = metaData;
  }

  public String getResourceGroupId() {
    return resourceGroupId;
  }

  public void setResourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
  }

  @Override
  public String toString() {
    return "AllocateNetworkRequest{"
        + "networkResourceName='"
        + networkResourceName
        + '\''
        + ", reservationId='"
        + reservationId
        + '\''
        + ", networkResourceType="
        + networkResourceType
        + ", typeNetworkData="
        + typeNetworkData
        + ", typeSubnetData="
        + typeSubnetData
        + ", typeNetworkPortData="
        + typeNetworkPortData
        +
        //           ", affinityConstraint=" +
        //           affinityConstraint +
        //           ", antiAffinityConstraint=" +
        //           antiAffinityConstraint +
        ", locationConstraints='"
        + locationConstraints
        + '\''
        + ", metaData="
        + metaData
        + ", resourceGroupId='"
        + resourceGroupId
        + '\''
        + '}';
  }
}
