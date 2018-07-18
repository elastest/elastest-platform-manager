package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*This information element specifies requirements on a virtual network interface.*/
@Entity
public class VirtualNetworkInterfaceRequirements extends NfvEntity {

    /*Provides a human readable name for the requirement.*/
    private String name;

    /*Provides a human readable description of the requirement.*/
    private String description;

    /*Indicates whether fulfilling the constraint is mandatory (TRUE) for successful operation or desirable (FALSE).*/
    @NotEmpty
    private boolean supportMandatory;

    /*Specifies a requirement such as the support of SR-IOV, a particular data plane acceleration library, an API to be exposed by a NIC, etc.*/
    //TODO type not specified
    @NotEmpty
    private String requirement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSupportMandatory() {
        return supportMandatory;
    }

    public void setSupportMandatory(boolean supportMandatory) {
        this.supportMandatory = supportMandatory;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "VirtualNetworkInterfaceRequirements{"
                + "name='"
                + name
                + '\''
                + ", description='"
                + description
                + '\''
                + ", supportMandatory="
                + supportMandatory
                + ", requirement='"
                + requirement
                + '\''
                + '}';
    }
}
