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
