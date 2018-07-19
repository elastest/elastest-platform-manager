package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*The LinkBitrateRequirements information element describes the requirements in terms of bitrate for a VL.
 * NOTE: The present document does not specify the means to declare different bitrate requirements for leaf connections (e.g. E-LAN leaves).*/
@Entity
public class LinkBitrateRequirements extends NfvEntity {

    /*Throughput requirement of the link (e.g. bitrate of E-Line, root bitrate of E-Tree, aggregate capacity of E-LAN).*/
    @NotEmpty
    private long root;

    /*Throughput requirement of leaf connections to the link when applicable to the connectivity type (e.g. for E-Tree and E-LAN branches). See note.*/
    private long leaf;

    public long getRoot() {
        return root;
    }

    public void setRoot(long root) {
        this.root = root;
    }

    public long getLeaf() {
        return leaf;
    }

    public void setLeaf(long leaf) {
        this.leaf = leaf;
    }

    @Override
    public String toString() {
        return "LinkBitrateRequirements{" + "root=" + root + ", leaf=" + leaf + '}';
    }
}
