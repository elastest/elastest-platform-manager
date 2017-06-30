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
import java.util.List;

public class QueryComputeResponse {

  /**
   * Element containing information about the virtual compute resource(s) matching the filter. The
   * cardinality can be 0 if no matching compute resources exist. See clause 8.4.3.2.
   */
  private List<VirtualCompute> queryResult;

  public List<VirtualCompute> getQueryResult() {
    return queryResult;
  }

  public void setQueryResult(List<VirtualCompute> queryResult) {
    this.queryResult = queryResult;
  }

  @Override
  public String toString() {
    return "QueryComputeResponse{" + "queryResult=" + queryResult + '}';
  }
}
