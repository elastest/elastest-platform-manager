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

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.network.NetworkSubnetData;
import io.elastest.epm.pop.model.network.VirtualNetworkData;
import io.elastest.epm.pop.model.network.VirtualNetworkPortData;
import java.util.List;

public class UpdateNetworkRequest {

  /** Identifier of the virtualised network resource to update. */
  private String networkResourceId;
  /**
   * If update is on a network resource, the element contains the fields that can be updated. See
   * clause 8.4.4.2.
   */
  private VirtualNetworkData updateNetworkData;
  /**
   * If update is on a subnet resource, the element contains the fields that can be updated. See
   * clause 8.4.4.4.
   */
  private NetworkSubnetData updateSubnetData;
  /**
   * If update is on a network port, the element a contains the fields that can be updated. See
   * clause 8.4.4.5.
   */
  private VirtualNetworkPortData updateNetworkPort;
  /**
   * List of metadata key-value pairs used by the consumer to associate meaningful metadata to the
   * related virtualised resource.
   */
  private List<KeyValuePair> metaData;

  public VirtualNetworkData getUpdateNetworkData() {
    return updateNetworkData;
  }

  public void setUpdateNetworkData(VirtualNetworkData updateNetworkData) {
    this.updateNetworkData = updateNetworkData;
  }

  public NetworkSubnetData getUpdateSubnetData() {
    return updateSubnetData;
  }

  public void setUpdateSubnetData(NetworkSubnetData updateSubnetData) {
    this.updateSubnetData = updateSubnetData;
  }

  public VirtualNetworkPortData getUpdateNetworkPort() {
    return updateNetworkPort;
  }

  public void setUpdateNetworkPort(VirtualNetworkPortData updateNetworkPort) {
    this.updateNetworkPort = updateNetworkPort;
  }

  public List<KeyValuePair> getMetaData() {
    return metaData;
  }

  public void setMetaData(List<KeyValuePair> metaData) {
    this.metaData = metaData;
  }

  public String getNetworkResourceId() {

    return networkResourceId;
  }

  public void setNetworkResourceId(String networkResourceId) {
    this.networkResourceId = networkResourceId;
  }

  @Override
  public String toString() {
    return "UpdateNetworkRequest{"
        + "networkResourceId='"
        + networkResourceId
        + '\''
        + ", updateNetworkData="
        + updateNetworkData
        + ", updateSubnetData="
        + updateSubnetData
        + ", updateNetworkPort="
        + updateNetworkPort
        + ", metaData="
        + metaData
        + '}';
  }
}
