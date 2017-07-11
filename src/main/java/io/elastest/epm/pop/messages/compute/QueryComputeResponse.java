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
