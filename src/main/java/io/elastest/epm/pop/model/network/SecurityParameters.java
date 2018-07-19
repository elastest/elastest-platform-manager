package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*The SecurityParameters information element contains the signature of a NSD, VLD, PNFD or VNFFGD instance together with information required to validate the signature.
 * NOTE: Cardinality of 0 corresponds to the case where the certificate is provided by means outside the NSD.*/
@Entity
public class SecurityParameters extends NfvEntity {

    /*Provides the signature of the signed part of the descriptor.*/
    @NotEmpty
    private String signature;

    /*Identifies the algorithm used to compute the signature.*/
    @NotEmpty
    private String algorithm;

    /*Provides a certificate or a reference to a certificate to validate the signature. See note.*/
    //TODO type not specified
    private String certificate;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "SecurityParameters{"
                + "signature='"
                + signature
                + '\''
                + ", algorithm='"
                + algorithm
                + '\''
                + ", certificate='"
                + certificate
                + '\''
                + '}';
    }
}
