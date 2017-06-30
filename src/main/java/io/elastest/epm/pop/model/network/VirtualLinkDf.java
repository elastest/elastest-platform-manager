package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The VirtualLinkDf information element specifies properties for instantiating a VL according to a specific flavour.*/
@Entity
public class VirtualLinkDf extends NfvEntity {

  /*Identifies this VirtualLinkDf information element within a VLD.*/
  @NotEmpty private String flavourId;

  /*See clause 6.5.6.*/
  private QoS qos;

  /*Specifies one of the three levels defined in ETSI GS NFV-REL 001 [i.5]: Level 1, Level 2, Level 3.*/
  private ServiceAvaibilityLevel serviceAvaibilityLevel;

  public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
  }

  public QoS getQos() {
    return qos;
  }

  public void setQos(QoS qos) {
    this.qos = qos;
  }

  public ServiceAvaibilityLevel getServiceAvaibilityLevel() {
    return serviceAvaibilityLevel;
  }

  public void setServiceAvaibilityLevel(ServiceAvaibilityLevel serviceAvaibilityLevel) {
    this.serviceAvaibilityLevel = serviceAvaibilityLevel;
  }

  @Override
  public String toString() {
    return "VirtualLinkDf{"
        + "flavourId='"
        + flavourId
        + '\''
        + ", qos="
        + qos
        + ", serviceAvaibilityLevel="
        + serviceAvaibilityLevel
        + '}';
  }
}
