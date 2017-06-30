package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The VirtualMemoryData information element supports the specification of requirements related to virtual memory of a virtual compute resource.*/
@Entity
public class VirtualMemoryData extends NfvEntity {

  /*Amount of virtual Memory (e.g. in MB).*/
  @NotEmpty private long virtualMemSize;

  /*The memory core oversubscription policy in terms of virtual memory to physical memory on the platform. The cardinality can be 0 during the allocation request, if no particular value is requested.*/
  //TODO type not specified
  private VirtualMemOversubscriptionPolicy virtualMemOversubscriptionPolicy;

  /*It specifies the memory allocation to be cognisant of the relevant process/core allocation. The cardinality can be 0 during the allocation request, if no particular value is requested.*/
  private boolean numaEnabled;

  public long getVirtualMemSize() {
    return virtualMemSize;
  }

  public void setVirtualMemSize(long virtualMemSize) {
    this.virtualMemSize = virtualMemSize;
  }

  public VirtualMemOversubscriptionPolicy getVirtualMemOversubscriptionPolicy() {
    return virtualMemOversubscriptionPolicy;
  }

  public void setVirtualMemOversubscriptionPolicy(
      VirtualMemOversubscriptionPolicy virtualMemOversubscriptionPolicy) {
    this.virtualMemOversubscriptionPolicy = virtualMemOversubscriptionPolicy;
  }

  public boolean isNumaEnabled() {
    return numaEnabled;
  }

  public void setNumaEnabled(boolean numaEnabled) {
    this.numaEnabled = numaEnabled;
  }

  @Override
  public String toString() {
    return "VirtualMemoryData{"
        + "virtualMemSize="
        + virtualMemSize
        + ", virtualMemOversubscriptionPolicy="
        + virtualMemOversubscriptionPolicy
        + ", numaEnabled="
        + numaEnabled
        + '}';
  }
}
