package io.elastest.epm.pop.messages.network;

import io.elastest.epm.pop.model.network.NetworkSubnet;
import io.elastest.epm.pop.model.network.VirtualNetwork;
import io.elastest.epm.pop.model.network.VirtualNetworkPort;

public class AllocateNetworkResponse {

  /**
   * If network types are created satisfactorily, it contains the data relative to the instantiated
   * virtualised network resource. Cardinality can be "0" if the request did not include creation of
   * such type of resource. See clause 8.4.5.2.
   */
  private VirtualNetwork networkData;
  /**
   * If subnet types are created satisfactorily, it contains the data relative to the allocated
   * subnet. Cardinality can be "0" if the request did not include creation of such type of
   * resource. See clause 8.4.5.3.
   */
  private NetworkSubnet subnetData;
  /**
   * If network port types are created satisfactorily, it contains the data relative to the
   * allocated network port. Cardinality can be "0" if the request did not include creation of such
   * type of resource. See clause 8.4.5.4.
   */
  private VirtualNetworkPort networkPortData;

  public NetworkSubnet getSubnetData() {
    return subnetData;
  }

  public void setSubnetData(NetworkSubnet subnetData) {
    this.subnetData = subnetData;
  }

  public VirtualNetworkPort getNetworkPortData() {
    return networkPortData;
  }

  public void setNetworkPortData(VirtualNetworkPort networkPortData) {
    this.networkPortData = networkPortData;
  }

  public VirtualNetwork getNetworkData() {

    return networkData;
  }

  public void setNetworkData(VirtualNetwork networkData) {
    this.networkData = networkData;
  }

  @Override
  public String toString() {
    return "AllocateNetworkResponse{"
        + "networkData="
        + networkData
        + ", subnetData="
        + subnetData
        + ", networkPortData="
        + networkPortData
        + '}';
  }
}
