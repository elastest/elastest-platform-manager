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

import io.elastest.epm.pop.model.common.Filter;

public class QueryNetworkRequest {

  /**
   * Query filter based on e.g. name, identifier, meta- data information or status information,
   * expressing the type of information to be retrieved. It can also be used to specify one or more
   * resources to be queried by providing their identifiers.
   */
  private Filter queryNetworkFilter;

  public Filter getQueryNetworkFilter() {
    return queryNetworkFilter;
  }

  public void setQueryNetworkFilter(Filter queryNetworkFilter) {
    this.queryNetworkFilter = queryNetworkFilter;
  }

  @Override
  public String toString() {
    return "QueryNetworkRequest{" + "queryNetworkFilter=" + queryNetworkFilter + '}';
  }
}
