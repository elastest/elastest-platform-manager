package io.elastest.epm.pop.messages.compute;

import io.elastest.epm.pop.model.common.Filter;

public class QueryComputeRequest {

    /**
     * Query filter based on e.g. name, identifier, meta-data information or status information,
     * expressing the type of information to be retrieved. It can also be used to specify one or more
     * resources to be queried by providing their identifiers.
     */
    private Filter queryComputeFilter;

    public Filter getQueryComputeFilter() {
        return queryComputeFilter;
    }

    public void setQueryComputeFilter(Filter queryComputeFilter) {
        this.queryComputeFilter = queryComputeFilter;
    }

    @Override
    public String toString() {
        return "QueryComputeRequest{" + "queryComputeFilter=" + queryComputeFilter + '}';
    }
}
