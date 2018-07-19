package io.elastest.epm.pop.model.common;

import java.util.Set;
import javax.persistence.Entity;

/*This information element defines the configurable properties of a VNFC. For a VNFC instance, the value of these properties can be modified through the VNFM.*/
@Entity
public class VnfcConfigurableProperties extends NfvEntity {

    /*It provides VNFC configurable properties.*/
    //TODO type not specified
    private Set<String> additionalVnfcConfigurableProperty;
}
