package io.elastest.epm.pop.messages.compute;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.compute.VirtualComputeFlavour;
import java.util.List;

public class AllocateComputeRequest {

  /**
   * Name provided by the consumer for the virtualised compute resource to allocate. It can be used
   * for identifying resources from consumer side.
   */
  private String computeName;
  /**
   * Identifier of the resource reservation applicable to this virtualised resource management
   * operation.
   */
  private String reservationId;
  //  /**
  //   * Element with affinity information of the straint virtualised compute resource to allocate. See
  //   * clause 8.4.8.2.
  //   */
  //  private List<AffinityOrAntiAffinityConstraint> affinityConstraint;
  //  /**
  //   * Element with anti-affinity information of straint the virtualised compute resource to allocate. See clause
  //   * 8.4.8.2.
  //   */
  //  private List<AffinityOrAntiAffinityConstraint> antiAffinityConstraint;
  /**
   * The compute data provides information about the particular memory, CPU and about the particular
   * memory, CPU and disk resources for virtualised compute disk resources for virtualised compute
   * resource to allocate. See resource to allocate. See clause 8.4.2.2.
   */
  private VirtualComputeFlavour computeData;
  /**
   * Identifier of the virtualisation container software image (e.g. a virtual machine image).
   * Cardinality can be 0 if an "empty" virtualisation container is allocated.
   */
  private String vcImageId;
  /**
   * List of metadata key-value pairs used by the consumer to associate meaningful metadata to the
   * related virtualised resource.
   */
  private List<KeyValuePair> metaData;
  /**
   * Unique identifier of the "infrastructure resource group", logical grouping of resource group",
   * logical grouping of virtual resources assigned to a tenant virtual resources assigned to a
   * tenant within an Infrastructure Domain. within an Infrastructure Domain. Cardinality can be 0
   * if the consumer Cardinality can be 0 if the consumer credentials are implicitly associated to a
   * credentials are implicitly associated to a specific resource group. specific resource group.
   */
  private String resourceGroupId;
  /**
   * If present, it defines location constraints for the resource(s) is (are) requested to be
   * allocated, e.g. in what particular resource zone.
   */
  private String locationConstraints;

  public String getComputeName() {
    return computeName;
  }

  public void setComputeName(String computeName) {
    this.computeName = computeName;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
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

  public VirtualComputeFlavour getComputeData() {
    return computeData;
  }

  public void setComputeData(VirtualComputeFlavour computeData) {
    this.computeData = computeData;
  }

  public String getVcImageId() {
    return vcImageId;
  }

  public void setVcImageId(String vcImageId) {
    this.vcImageId = vcImageId;
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

  public String getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(String locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  @Override
  public String toString() {
    return "AllocateComputeRequest{"
        + "computeName='"
        + computeName
        + '\''
        + ", reservationId='"
        + reservationId
        + '\''
        +
        //           ", affinityConstraint=" +
        //           affinityConstraint +
        //           ", antiAffinityConstraint=" +
        //           antiAffinityConstraint +
        ", computeData="
        + computeData
        + ", vcImageId='"
        + vcImageId
        + '\''
        + ", metaData="
        + metaData
        + ", resourceGroupId='"
        + resourceGroupId
        + '\''
        + ", locationConstraints='"
        + locationConstraints
        + '\''
        + '}';
  }
}
