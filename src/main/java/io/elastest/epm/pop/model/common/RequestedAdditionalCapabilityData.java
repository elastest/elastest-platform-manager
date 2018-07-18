package io.elastest.epm.pop.model.common;

import io.elastest.epm.model.KeyValuePair;

import java.util.Set;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*This information element describes requested additional capability for a particular VDU. Such a capability may be for acceleration or specific tasks.*/
@Entity
public class RequestedAdditionalCapabilityData extends NfvEntity {

    /*Identifies a requested additional capability for the VDU. ETSI GS NFV-IFA 002 [i.1] describes acceleration capabilities.*/
    @NotEmpty
    private String requestedAdditionalCapabilityName;

    /*Indicates whether the requested additional capability is mandatory for successful operation.*/
    @NotEmpty
    private boolean supportMandatory;

    /*Identifies the minimum version of the requested additional capability.*/
    private String minRequestedAdditionalCapabilityVersion;

    /*Identifies the preferred version of the requested additional capability.*/
    private String preferredRequestedAdditionalCapabilityVersion;

    /*Identifies specific attributes, dependent on the requested additional capability type.*/
    @NotEmpty
    private Set<KeyValuePair> targetPerformanceParameters;

    public String getRequestedAdditionalCapabilityName() {
        return requestedAdditionalCapabilityName;
    }

    public void setRequestedAdditionalCapabilityName(String requestedAdditionalCapabilityName) {
        this.requestedAdditionalCapabilityName = requestedAdditionalCapabilityName;
    }

    public boolean isSupportMandatory() {
        return supportMandatory;
    }

    public void setSupportMandatory(boolean supportMandatory) {
        this.supportMandatory = supportMandatory;
    }

    public String getMinRequestedAdditionalCapabilityVersion() {
        return minRequestedAdditionalCapabilityVersion;
    }

    public void setMinRequestedAdditionalCapabilityVersion(
            String minRequestedAdditionalCapabilityVersion) {
        this.minRequestedAdditionalCapabilityVersion = minRequestedAdditionalCapabilityVersion;
    }

    public String getPreferredRequestedAdditionalCapabilityVersion() {
        return preferredRequestedAdditionalCapabilityVersion;
    }

    public void setPreferredRequestedAdditionalCapabilityVersion(
            String preferredRequestedAdditionalCapabilityVersion) {
        this.preferredRequestedAdditionalCapabilityVersion =
                preferredRequestedAdditionalCapabilityVersion;
    }

    public Set<KeyValuePair> getTargetPerformanceParameters() {
        return targetPerformanceParameters;
    }

    public void setTargetPerformanceParameters(Set<KeyValuePair> targetPerformanceParameters) {
        this.targetPerformanceParameters = targetPerformanceParameters;
    }

    @Override
    public String toString() {
        return "RequestedAdditionalCapabilityData{"
                + "requestedAdditionalCapabilityName='"
                + requestedAdditionalCapabilityName
                + '\''
                + ", supportMandatory="
                + supportMandatory
                + ", minRequestedAdditionalCapabilityVersion='"
                + minRequestedAdditionalCapabilityVersion
                + '\''
                + ", preferredRequestedAdditionalCapabilityVersion='"
                + preferredRequestedAdditionalCapabilityVersion
                + '\''
                + ", targetPerformanceParameters="
                + targetPerformanceParameters
                + '}';
    }
}
