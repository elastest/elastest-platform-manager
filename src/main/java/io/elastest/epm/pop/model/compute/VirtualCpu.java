package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.common.NfvEntity;
import org.hibernate.validator.constraints.NotEmpty;

/*The virtual CPU(s) of the virtualised compute. */
public class VirtualCpu extends NfvEntity {

  /*String CPU architecture type. Examples are x86, ARM.*/
  @NotEmpty private String cpuArchitecture;

  /*Number of virtual CPUs.*/
  @NotEmpty private int numVirtualCpu;

  /*Minimum virtual CPU clock rate (e.g. in MHz).*/
  @NotEmpty long virtualCpuClock;

  /*The CPU core oversubscription policy, e.g. the relation of virtual CPU cores to physical CPU cores/threads. The cardinality can be 0 if no policy has been defined during the allocation request.*/
  private VirtualCpuOversubscriptionPolicy virtualCpuOversubscriptionPolicy;

  /* The virtual CPU pinning configuration for the virtualised compute resource. See clause 8.4.3.4.*/
  private VirtualCpuPinning virtualCpuPinning;
}
