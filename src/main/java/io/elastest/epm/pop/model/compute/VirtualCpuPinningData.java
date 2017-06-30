package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.common.NfvEntity;
import java.util.Map;
import javax.persistence.Entity;

/*The VirtualCpuPinningData information element supports the specification of requirements related to the virtual CPU pinning configuration of a virtual compute resource.*/
@Entity
public class VirtualCpuPinningData extends NfvEntity {

  /*Indicates the policy for CPU pinning. The policy can take values of "static" or "dynamic". The cardinality can be 0 during the allocation request, if no particular value is requested.*/
  private CpuPinningPolicy cpuPinningPolicy;

  /*If cpuPinningPolicy is defined as "static", the cpuPinningMap provides the map of pinning virtual CPU cores to physical CPU cores/threads. Cardinality is 0 if cpuPinningPolicy has a different value than "static".*/
  //TODO type not specified
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
    return "VirtualCpuPinningData{"
        + "cpuPinningPolicy="
        + cpuPinningPolicy
        + ", cpuPinningMap="
        + cpuPinningMap
        + '}';
  }
}
