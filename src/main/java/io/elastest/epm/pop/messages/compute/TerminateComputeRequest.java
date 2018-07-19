package io.elastest.epm.pop.messages.compute;

import java.util.List;

public class TerminateComputeRequest {

    /**
     * Identifier(s) of the virtualised compute resource(s) to be terminated.
     */
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
