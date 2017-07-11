package io.elastest.epm.pop.messages.compute;

import io.elastest.epm.pop.model.compute.VirtualCompute;

public class UpdateComputeResponse {

  /**
   * The identifier of the virtualised compute resource that has been updated. This parameter has
   * the same value as the input parameter.
   */
  private String computeId;
  /**
   * Element containing information of the updated attributes of the instantiated virtualised
   * compute resource. See clause 8.4.3.2.
   */
  private VirtualCompute computeData;

  public String getComputeId() {
    return computeId;
  }

  public void setComputeId(String computeId) {
    this.computeId = computeId;
  }

  public VirtualCompute getComputeData() {
    return computeData;
  }

  public void setComputeData(VirtualCompute computeData) {
    this.computeData = computeData;
  }

  @Override
  public String toString() {
    return "UpdateComputeResponse{"
        + "computeId='"
        + computeId
        + '\''
        + ", computeData="
        + computeData
        + '}';
  }
}
