package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.common.NfvEntity;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

/*This clause describes the attributes for the VirtualCpuPinning information element. */
public class VirtualCpuPinning extends NfvEntity {

  /*The policy can take values of "static" or "dynamic".*/
  @NotEmpty private CpuPinningPolicy cpuPinningPolicy;

  /*If CpuPinningPolicy is defined as "static", the CpuPinninMap provides the map of pinning virtual CPU cores to physical CPU cores/threads. Cardinality can be 0 if CpuPinningPolicy has a different value.*/
  private Map<String, String> cpuPinningMap;

  public CpuPinningPolicy getCpuPinningPolicy() {
    return cpuPinningPolicy;
  }

  public void setCpuPinningPolicy(CpuPinningPolicy cpuPinningPolicy) {
    this.cpuPinningPolicy = cpuPinningPolicy;
  }

  public Map<String, String> getCpuPinningMap() {
    return cpuPinningMap;
  }

  public void setCpuPinningMap(Map<String, String> cpuPinningMap) {
    this.cpuPinningMap = cpuPinningMap;
  }

  @Override
  public String toString() {
    return "VirtualCpuPinning{"
        + "cpuPinningPolicy="
        + cpuPinningPolicy
        + ", cpuPinningMap="
        + cpuPinningMap
        + '}';
  }
}
