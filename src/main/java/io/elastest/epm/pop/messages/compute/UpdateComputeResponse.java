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

import io.elastest.epm.pop.model.compute.VirtualCompute;

public class UpdateComputeResponse {

  /**
   * The identifier of the virtualised compute resource that has been updated. This parameter has
   * the same value as the input parameter.
   */
  private String computeId;
  /**
   * Element containing information of the updated attributes of the instantiated virtualised
   * compute resource. See clause 8.4.3.2.
   */
  private VirtualCompute computeData;

  public String getComputeId() {
    return computeId;
  }

  public void setComputeId(String computeId) {
    this.computeId = computeId;
  }

  public VirtualCompute getComputeData() {
    return computeData;
  }

  public void setComputeData(VirtualCompute computeData) {
    this.computeData = computeData;
  }

  @Override
  public String toString() {
    return "UpdateComputeResponse{"
        + "computeId='"
        + computeId
        + '\''
        + ", computeData="
        + computeData
        + '}';
  }
}
