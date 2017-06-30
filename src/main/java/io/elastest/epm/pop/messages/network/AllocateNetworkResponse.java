/*
 *
 *  * (C) Copyright 2016 NUBOMEDIA (http://www.nubomedia.eu)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

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
