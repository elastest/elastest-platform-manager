package io.elastest.epm.pop.messages.compute;

import java.util.List;

public class TerminateComputeResponse {

    /**
     * Identifier(s) of the virtualised compute resource(s) successfully terminated.
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
        return "TerminateComputeResponse{" + "computeId=" + computeId + '}';
    }
}
