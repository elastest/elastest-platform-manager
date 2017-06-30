package io.elastest.epm.pop.model.compute;

import io.elastest.epm.pop.model.network.VirtualNetworkInterfaceData;
import io.elastest.epm.pop.model.storage.VirtualStorageData;
import java.util.List;

public class VirtualComputeFlavour {

  /** Identifier given to the compute flavour. */
  private String flavourId;
  /**
   * Selected acceleration capabilities y (e.g. crypto, GPU) from the set of capabilities offered by
   * the compute node acceleration resources. The cardinality can be 0, if no particular
   * acceleration capability is requested.
   */
  private List<String> accelerationCapabilit;
  /** The virtual memory of the virtualised compute. See clause 8.4.3.5. */
  private VirtualMemoryData virtualMemory;
  /** The virtual CPU(s) of the virtualised compute. See clause 8.4.3.3. */
  private VirtualCpuData virtualCpu;
  /**
   * Element containing information about the size of virtualised storage resource (e.g. size of
   * volume, in GB), the type of storage (e.g. volume, object), and support for RDMA. See clause
   * 8.4.3.6.
   */
  private List<VirtualStorageData> storageAttributes;
  /** The virtual network interfaces of the */
  private List<VirtualNetworkInterfaceData> virtualNetworkInterfaceData;

  public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
  }

  public List<String> getAccelerationCapabilit() {
    return accelerationCapabilit;
  }

  public void setAccelerationCapabilit(List<String> accelerationCapabilit) {
    this.accelerationCapabilit = accelerationCapabilit;
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

  public List<VirtualStorageData> getStorageAttributes() {
    return storageAttributes;
  }

  public void setStorageAttributes(List<VirtualStorageData> storageAttributes) {
    this.storageAttributes = storageAttributes;
  }

  public List<VirtualNetworkInterfaceData> getVirtualNetworkInterfaceData() {
    return virtualNetworkInterfaceData;
  }

  public void setVirtualNetworkInterfaceData(
      List<VirtualNetworkInterfaceData> virtualNetworkInterfaceData) {
    this.virtualNetworkInterfaceData = virtualNetworkInterfaceData;
  }

  @Override
  public String toString() {
    return "VirtualComputeFlavour{"
        + "flavourId='"
        + flavourId
        + '\''
        + ", accelerationCapabilit="
        + accelerationCapabilit
        + ", virtualMemory="
        + virtualMemory
        + ", virtualCpu="
        + virtualCpu
        + ", storageAttributes="
        + storageAttributes
        + ", virtualNetworkInterfaceData="
        + virtualNetworkInterfaceData
        + '}';
  }
}
