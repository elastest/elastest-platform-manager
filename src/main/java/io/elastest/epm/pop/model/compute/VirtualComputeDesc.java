package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.RequestedAdditionalCapabilityData;
import java.util.Set;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/** Created by mpa on 27.12.16. */
/*The VirtualComputeDesc information element supports the specification of requirements related to virtual compute resources.*/
@Entity
public class VirtualComputeDesc extends NfvEntity {

  /*Unique identifier of this VirtualComputeDesc in the VNFD.*/
  @NotEmpty private String virtualComputeDescId;

  /*Specifies requirements for additional capabilities. These may be for a range of purposes. One example is acceleration related capabilities. See clause 7.1.9.5.*/
  private Set<RequestedAdditionalCapabilityData> requestAdditionalCapabilities;

  /*The virtual memory of the virtualised compute. See clause 7.1.9.3.*/
  @NotEmpty private VirtualMemoryData virtualMemory;

  /*The virtual CPU(s) of the virtualised compute. See clause 7.1.9.2.*/
  @NotEmpty private VirtualCpuData virtualCpu;

  public String getVirtualComputeDescId() {
    return virtualComputeDescId;
  }

  public void setVirtualComputeDescId(String virtualComputeDescId) {
    this.virtualComputeDescId = virtualComputeDescId;
  }

  public Set<RequestedAdditionalCapabilityData> getRequestAdditionalCapabilities() {
    return requestAdditionalCapabilities;
  }

  public void setRequestAdditionalCapabilities(
      Set<RequestedAdditionalCapabilityData> requestAdditionalCapabilities) {
    this.requestAdditionalCapabilities = requestAdditionalCapabilities;
  }

  public VirtualMemoryData getVirtualMemory() {
    return virtualMemory;
  }

  public void setVirtualMemory(VirtualMemoryData virtualMemory) {
    this.virtualMemory = virtualMemory;
  }

  public VirtualCpuData getVirtualCpu() {
    return virtualCpu;
  }

  public void setVirtualCpu(VirtualCpuData virtualCpu) {
    this.virtualCpu = virtualCpu;
  }

  @Override
  public String toString() {
    return "VirtualComputeDesc{"
        + "virtualComputeDescId='"
        + virtualComputeDescId
        + '\''
        + ", requestAdditionalCapabilities="
        + requestAdditionalCapabilities
        + ", virtualMemory="
        + virtualMemory
        + ", virtualCpu="
        + virtualCpu
        + '}';
  }
}
