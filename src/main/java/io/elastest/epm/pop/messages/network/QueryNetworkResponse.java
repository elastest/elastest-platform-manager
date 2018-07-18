package io.elastest.epm.pop.messages.network;

import io.elastest.epm.pop.model.network.VirtualNetwork;

import java.util.List;

public class QueryNetworkResponse {

    /**
     * Element containing information about the virtual network resource(s) matching the filter. The
     * cardinality can be 0 if no matching network resources exist. See clause 8.4.5.2.
     */
    private List<VirtualNetwork> queryResult;

    public List<VirtualNetwork> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(List<VirtualNetwork> queryResult) {
        this.queryResult = queryResult;
    }

    @Override
    public String toString() {
        return "QueryNetworkResponse{" + "queryResult=" + queryResult + '}';
    }
}
