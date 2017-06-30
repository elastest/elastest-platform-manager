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

package io.elastest.epm.pop.messages.compute;

import io.elastest.epm.pop.model.network.VirtualNetworkInterface;
import io.elastest.epm.pop.model.network.VirtualNetworkInterfaceData;
import java.util.List;
import java.util.Map.Entry;

/**
 * NOTE: Cardinality can be "0", as it is recommended that only one type of update either to add new
 * virtual network interfaces (see "networkInterfaceNew" input) or update existing ones (see
 * "networkInterfaceUpdate" input) is made in a single operation request.
 */
public class UpdateComputeRequest {

  /** Identifier of the virtualised compute resource to update. */
  private String computeId;
  /**
   * The new virtual network interface(s) to add to the compute resource. See note. See clause
   * 8.4.2.6.
   */
  private List<VirtualNetworkInterfaceData> networkInterfaceNew;
  /**
   * The virtual network interface(s) to update on the compute resource. This can include, for
   * instance, attaching/detaching a virtual network interface to/from its port, or re-attaching to
   * another network port. See note. See clause 8.4.3.6.
   */
  private List<VirtualNetworkInterface> networkInterfaceUpdate;
  /**
   * List of metadata key-value pairs used by the consumer to associate meaningful metadata to the
   * related virtualised resource.
   */
  private List<Entry<String, String>> metaData;

  public String getComputeId() {
    return computeId;
  }

  public void setComputeId(String computeId) {
    this.computeId = computeId;
  }

  public List<VirtualNetworkInterfaceData> getNetworkInterfaceNew() {
    return networkInterfaceNew;
  }

  public void setNetworkInterfaceNew(List<VirtualNetworkInterfaceData> networkInterfaceNew) {
    this.networkInterfaceNew = networkInterfaceNew;
  }

  public List<VirtualNetworkInterface> getNetworkInterfaceUpdate() {
    return networkInterfaceUpdate;
  }

  public void setNetworkInterfaceUpdate(List<VirtualNetworkInterface> networkInterfaceUpdate) {
    this.networkInterfaceUpdate = networkInterfaceUpdate;
  }

  public List<Entry<String, String>> getMetaData() {
    return metaData;
  }

  public void setMetaData(List<Entry<String, String>> metaData) {
    this.metaData = metaData;
  }

  @Override
  public String toString() {
    return "UpdateComputeRequest{"
        + "computeId='"
        + computeId
        + '\''
        + ", networkInterfaceNew="
        + networkInterfaceNew
        + ", networkInterfaceUpdate="
        + networkInterfaceUpdate
        + ", metaData="
        + metaData
        + '}';
  }
}
