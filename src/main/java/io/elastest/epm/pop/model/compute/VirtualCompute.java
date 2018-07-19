package io.elastest.epm.pop.model.compute;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.OperationalState;
import io.elastest.epm.pop.model.network.VirtualNetworkInterface;
import io.elastest.epm.pop.model.storage.VirtualStorage;

import java.util.Set;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mpa on 27.12.16.
 */
/*The information elements in this group encapsulate data of an instantiated virtualised compute resource.*/
@Entity
public class VirtualCompute extends NfvEntity {

    /*Identifier of the virtualised compute resource*/
    @NotEmpty
    private String computeId;

    /*Name of the virtualised compute resource.*/
    private String computeName;

    /*Identifier of the given compute flavour used to instantiate this virtual compute.*/
    @NotEmpty
    private String flavourId;

    /*Selected acceleration capabilities (e.g. crypto, GPU) from the set of capabilities offered by the compute node acceleration resources. The cardinality can be 0, if no particular acceleration capability is provided.*/
    private Set<String> accelerationCapability;

    /*VirtualCpu The virtual CPU(s) of the virtualised compute. See clause 8.4.3.3.*/
    @NotEmpty
    private VirtualCpu virtualCpu;

    /*The virtual memory of the compute. See clause 8.4.3.5.*/
    @NotEmpty
    private VirtualMemory virtualMemory;

    /*Element with information of the instantiated virtual network interfaces of the compute resource. See clause 8.4.3.6.*/
    private Set<VirtualNetworkInterface> virtualNetworkInterface;

    /*Element with information of the virtualised storage resources (volumes, ephemeral that are attached to the compute resource. See clause 8.4.7.2.*/
    @NotEmpty
    private Set<VirtualStorage> virtualDisks;

    /*Identifier of the virtualisation container software image(e.g. virtual machine image) .Cardinality can be 0 if an"empty" virtualisation container is allocated.*/
    private String vcImageId;

    /*If present, it identifies the Resource Zone where the virtual compute resources have been allocated.*/
    private String zoneId;

    /* Identifier of the host the virtualised compute resource is allocated on. operationalState M 1Enum:*/
    @NotEmpty
    private String hostId;

    /*Operational state of the compute resource.*/
    @NotEmpty
    private OperationalState operationalState;

    /*KeyValuePair List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
    private Set<KeyValuePair> metadata;

    public String getComputeId() {
        return computeId;
    }

    public void setComputeId(String computeId) {
        this.computeId = computeId;
    }

    public String getComputeName() {
        return computeName;
    }

    public void setComputeName(String computeName) {
        this.computeName = computeName;
    }

    public String getFlavourId() {
        return flavourId;
    }

    public void setFlavourId(String flavourId) {
        this.flavourId = flavourId;
    }

    public Set<String> getAccelerationCapability() {
        return accelerationCapability;
    }

    public void setAccelerationCapability(Set<String> accelerationCapability) {
        this.accelerationCapability = accelerationCapability;
    }

    public VirtualCpu getVirtualCpu() {
        return virtualCpu;
    }

    public void setVirtualCpu(VirtualCpu virtualCpu) {
        this.virtualCpu = virtualCpu;
    }

    public VirtualMemory getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(VirtualMemory virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public Set<VirtualNetworkInterface> getVirtualNetworkInterface() {
        return virtualNetworkInterface;
    }

    public void setVirtualNetworkInterface(Set<VirtualNetworkInterface> virtualNetworkInterface) {
        this.virtualNetworkInterface = virtualNetworkInterface;
    }

    public Set<VirtualStorage> getVirtualDisks() {
        return virtualDisks;
    }

    public void setVirtualDisks(Set<VirtualStorage> virtualDisks) {
        this.virtualDisks = virtualDisks;
    }

    public String getVcImageId() {
        return vcImageId;
    }

    public void setVcImageId(String vcImageId) {
        this.vcImageId = vcImageId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public OperationalState getOperationalState() {
        return operationalState;
    }

    public void setOperationalState(OperationalState operationalState) {
        this.operationalState = operationalState;
    }

    public Set<KeyValuePair> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<KeyValuePair> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "VirtualCompute{"
                + "computeId='"
                + computeId
                + '\''
                + ", computeName='"
                + computeName
                + '\''
                + ", flavourId='"
                + flavourId
                + '\''
                + ", accelerationCapability="
                + accelerationCapability
                + ", virtualCpu="
                + virtualCpu
                + ", virtualMemory="
                + virtualMemory
                + ", virtualNetworkInterface="
                + virtualNetworkInterface
                + ", virtualDisks="
                + virtualDisks
                + ", vcImageId='"
                + vcImageId
                + '\''
                + ", zoneId='"
                + zoneId
                + '\''
                + ", hostId='"
                + hostId
                + '\''
                + ", operationalState="
                + operationalState
                + ", metadata="
                + metadata
                + '}';
    }
}
