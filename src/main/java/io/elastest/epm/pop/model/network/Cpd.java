package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;

import java.util.Set;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*A Cpd information element describes network connectivity to a compute resource or a VL. This is an abstract class used as parent for the various Cpd classes.
 * NOTE: This information determines, amongst other things, which type of address to assign to the access point at instantiation time.*/
@Entity
public abstract class Cpd extends NfvEntity {

    /*Identifier of this Cpd information element*/
    @NotEmpty
    private String cpdId;

    /*Identifies which protocol the CP uses for connectivity purposes (Ethernet, MPLS, ODU2, IPV4, IPV6, Pseudo-Wire, etc.). See note.*/
    //TODO in IFA014 specified as 1..N
    @NotEmpty
    private Set<LayerProtocol> layerProtocol;

    /*Identifies the role of the port in the context of the traffic flow patterns in the VNF or parent NS. For example a VNF with a tree flow pattern within the VNF will have legal cpRoles of ROOT and LEAF.*/
    private String cpRole;

    /*Provides human-readable information on the purpose of the CP (e.g. CP for control plane traffic).*/
    private String description;

    /*Provides information on the addresses to be assigned to the CP(s) instantiated from this CPD.*/
    private Set<AddressData> addressData;

    public String getCpdId() {
        return cpdId;
    }

    public void setCpdId(String cpdId) {
        this.cpdId = cpdId;
    }

    public Set<LayerProtocol> getLayerProtocol() {
        return layerProtocol;
    }

    public void setLayerProtocol(Set<LayerProtocol> layerProtocol) {
        this.layerProtocol = layerProtocol;
    }

    public String getCpRole() {
        return cpRole;
    }

    public void setCpRole(String cpRole) {
        this.cpRole = cpRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AddressData> getAddressData() {
        return addressData;
    }

    public void setAddressData(Set<AddressData> addressData) {
        this.addressData = addressData;
    }

    @Override
    public String toString() {
        return "Cpd{"
                + "cpdId='"
                + cpdId
                + '\''
                + ", layerProtocol="
                + layerProtocol
                + ", cpRole='"
                + cpRole
                + '\''
                + ", description='"
                + description
                + '\''
                + ", addressData="
                + addressData
                + '}';
    }
}
