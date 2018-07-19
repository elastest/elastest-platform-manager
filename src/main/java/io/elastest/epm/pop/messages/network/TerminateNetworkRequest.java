package io.elastest.epm.pop.messages.network;

import java.util.List;

public class TerminateNetworkRequest {

    /**
     * Identifier of the virtualised network resource(s) to be terminated.
     */
    private List<String> networkResourceId;

    public List<String> getNetworkResourceId() {
        return networkResourceId;
    }

    public void setNetworkResourceId(List<String> networkResourceId) {
        this.networkResourceId = networkResourceId;
    }

    @Override
    public String toString() {
        return "TerminateNetworkRequest{" + "networkResourceId=" + networkResourceId + '}';
    }
}
