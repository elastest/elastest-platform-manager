package io.elastest.epm.pop.model.network;

import java.util.Set;
import javax.persistence.Entity;

/*A VduCpd information element is a type of Cpd and describes network connectivity between a VNFC record (based on this VDU) and an internal VL. A VduCpd inherits from the Cpd Class (see clause 7.1.6.3). All attributes of the Cpd are also attributes of the VduCpd. */
@Entity
public class VduCpd extends Cpd {

  /*Reference of the internal VLD which this internal CPD connects to.*/
  private String intVirtualLinkDesc;

  /*Bitrate requirement on this CP.*/
  private long bitrateRequirement;

  /*Specifies requirements on a virtual network interface realising the CPs instantiated from this CPD.*/
  private Set<VirtualNetworkInterfaceRequirements> virtualNetworkInterfaceRequirements;

  public String getIntVirtualLinkDesc() {
    return intVirtualLinkDesc;
  }

  public void setIntVirtualLinkDesc(String intVirtualLinkDesc) {
    this.intVirtualLinkDesc = intVirtualLinkDesc;
  }

  public long getBitrateRequirement() {
    return bitrateRequirement;
  }

  public void setBitrateRequirement(long bitrateRequirement) {
    this.bitrateRequirement = bitrateRequirement;
  }

  public Set<VirtualNetworkInterfaceRequirements> getVirtualNetworkInterfaceRequirements() {
    return virtualNetworkInterfaceRequirements;
  }

  public void setVirtualNetworkInterfaceRequirements(
      Set<VirtualNetworkInterfaceRequirements> virtualNetworkInterfaceRequirements) {
    this.virtualNetworkInterfaceRequirements = virtualNetworkInterfaceRequirements;
  }

  @Override
  public String toString() {
    return "VduCpd{"
        + "intVirtualLinkDesc='"
        + intVirtualLinkDesc
        + '\''
        + ", bitrateRequirement="
        + bitrateRequirement
        + ", virtualNetworkInterfaceRequirements="
        + virtualNetworkInterfaceRequirements
        + '}';
  }
}
