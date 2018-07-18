package io.elastest.epm.pop.model.compute;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*The VirtualCpuData information element supports the specification of requirements related to virtual CPU(s) of a virtual compute resource.*/
@Entity
public class VirtualCpuData {

    /*CPU architecture type. Examples are x86, ARM. The cardinality can be 0 during the allocation request, if no particular CPU architecture type is requested.*/
    private String cpuArchitecture;

    /*Number of virtual CPUs.*/
    @NotEmpty
    private int numVirtualCpu;

    /*Minimum virtual CPU clock rate (e.g. in MHz). The cardinality can be 0 during the allocation request, if no particular value is requested.*/
    private long virtualCpuClock;

    /*The CPU core oversubscription policy e.g. the relation of virtual CPU cores to physical CPU cores/threads. The cardinality can be 0 during the allocation request, if no particular value is requested.*/
    //TODO type not specified
    private VirtualCpuOversubscriptionPolicy virtualCpuOversubscriptionPolicy;

    /*The virtual CPU pinning configuration for the virtualised compute resource. See clause 7.1.9.2.4.*/
    private VirtualCpuPinningData virtualCpuPinning;

    public String getCpuArchitecture() {
        return cpuArchitecture;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public int getNumVirtualCpu() {
        return numVirtualCpu;
    }

    public void setNumVirtualCpu(int numVirtualCpu) {
        this.numVirtualCpu = numVirtualCpu;
    }

    public long getVirtualCpuClock() {
        return virtualCpuClock;
    }

    public void setVirtualCpuClock(long virtualCpuClock) {
        this.virtualCpuClock = virtualCpuClock;
    }

    public VirtualCpuOversubscriptionPolicy getVirtualCpuOversubscriptionPolicy() {
        return virtualCpuOversubscriptionPolicy;
    }

    public void setVirtualCpuOversubscriptionPolicy(
            VirtualCpuOversubscriptionPolicy virtualCpuOversubscriptionPolicy) {
        this.virtualCpuOversubscriptionPolicy = virtualCpuOversubscriptionPolicy;
    }

    public VirtualCpuPinningData getVirtualCpuPinning() {
        return virtualCpuPinning;
    }

    public void setVirtualCpuPinning(VirtualCpuPinningData virtualCpuPinning) {
        this.virtualCpuPinning = virtualCpuPinning;
    }

    @Override
    public String toString() {
        return "VirtualCpuData{"
                + "cpuArchitecture='"
                + cpuArchitecture
                + '\''
                + ", numVirtualCpu="
                + numVirtualCpu
                + ", virtualCpuClock="
                + virtualCpuClock
                + ", virtualCpuOversubscriptionPolicy="
                + virtualCpuOversubscriptionPolicy
                + ", virtualCpuPinning="
                + virtualCpuPinning
                + '}';
    }
}
