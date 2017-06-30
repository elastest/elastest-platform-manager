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

import java.util.List;

public class TerminateComputeRequest {

  /** Identifier(s) of the virtualised compute resource(s) to be terminated. */
  private List<String> computeId;

  public List<String> getComputeId() {
    return computeId;
  }

  public void setComputeId(List<String> computeId) {
    this.computeId = computeId;
  }

  @Override
  public String toString() {
    return "TerminateComputeRequest{" + "computeId=" + computeId + '}';
  }
}
