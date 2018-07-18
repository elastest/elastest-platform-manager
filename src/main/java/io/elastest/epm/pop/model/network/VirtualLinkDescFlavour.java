package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;

import java.util.Set;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*The VirtualLinkDescFlavour describes additional instantiation data for a given internal VL used in a DF.
* NOTE: If not present, it is assumed that the bitrate requirements can be derived from those specified in the VduCpd
instances applicable to the internal VL. If present in both the VirtualLinkDescFlavour and the VduCpd instances
applicable to the internal VL, the highest value takes precedence. */
@Entity
public class VirtualLinkDescFlavour extends NfvEntity {

    /*Identifies a flavour within a VnfVirtualLinkDesc.*/
    @NotEmpty
    private String flavourId;

    /*Bitrate requirements for a VL created from this VirtualLinkDescFlavour. See note.*/
    private Set<LinkBitrateRequirements> bitrateRequirements;

    /*QoS of the VL.*/
    private QoS qos;

    public String getFlavourId() {
        return flavourId;
    }

    public void setFlavourId(String flavourId) {
        this.flavourId = flavourId;
    }

    public Set<LinkBitrateRequirements> getBitrateRequirements() {
        return bitrateRequirements;
    }

    public void setBitrateRequirements(Set<LinkBitrateRequirements> bitrateRequirements) {
        this.bitrateRequirements = bitrateRequirements;
    }

    public QoS getQos() {
        return qos;
    }

    public void setQos(QoS qos) {
        this.qos = qos;
    }

    @Override
    public String toString() {
        return "VirtualLinkDescFlavour{"
                + "flavourId='"
                + flavourId
                + '\''
                + ", bitrateRequirements="
                + bitrateRequirements
                + ", qos="
                + qos
                + '}';
    }
}
