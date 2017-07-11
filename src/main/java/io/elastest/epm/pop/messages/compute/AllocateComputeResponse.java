package io.elastest.epm.pop.messages.compute;

import io.elastest.epm.pop.model.compute.VirtualCompute;

public class AllocateComputeResponse {

  /**
   * Element containing information of the newly instantiated virtualised compute resource. See
   * clause 8.4.3.2.
   */
  private VirtualCompute computeData;

  public VirtualCompute getComputeData() {
    return computeData;
  }

  public void setComputeData(VirtualCompute computeData) {
    this.computeData = computeData;
  }

  @Override
  public String toString() {
    return "AllocateComputeResponse{" + "computeData=" + computeData + '}';
  }
}
