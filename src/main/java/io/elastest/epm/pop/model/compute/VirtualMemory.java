package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.common.NfvEntity;
import org.hibernate.validator.constraints.NotEmpty;

/*This clause describes the attributes for the VirtualMemory information element. */
public class VirtualMemory extends NfvEntity {

    /*Amount of virtual Memory (e.g. in MB).*/
    @NotEmpty
    private long virtualMemSize;

    /*The memory core oversubscription policy in terms of virtual memory to physical memory on the platform. The cardinality can be 0 if no policy has been defined during the allocation request.*/
    private VirtualMemOversubscriptionPolicy virtualMemOversubscriptionPolicy;

    /*It specifies the memory allocation to be cognisant of the relevant process/core allocation.*/
    @NotEmpty
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
        return "VirtualMemory{"
                + "virtualMemSize="
                + virtualMemSize
                + ", virtualMemOversubscriptionPolicy="
                + virtualMemOversubscriptionPolicy
                + ", numaEnabled="
                + numaEnabled
                + '}';
    }
}
