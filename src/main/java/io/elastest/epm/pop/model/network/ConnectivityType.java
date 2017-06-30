package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The contents of a ConnectivityType type shall comply with the indications provided in table 7.1.7.3.2-1.*/
@Entity
public class ConnectivityType extends NfvEntity {

  /*Identifies the protocol this VL gives access to (Ethernet, MPLS, ODU2, IPV4, IPV6, Pseudo-Wire).*/
  @NotEmpty private LayerProtocol layerProtocol;

  /*Identifies the flow pattern of the connectivity (Line, Tree, Mesh).*/
  private String flowPattern;

  public LayerProtocol getLayerProtocol() {
    return layerProtocol;
  }

  public void setLayerProtocol(LayerProtocol layerProtocol) {
    this.layerProtocol = layerProtocol;
  }

  public String getFlowPattern() {
    return flowPattern;
  }

  public void setFlowPattern(String flowPattern) {
    this.flowPattern = flowPattern;
  }

  @Override
  public String toString() {
    return "ConnectivityType{"
        + "layerProtocol="
        + layerProtocol
        + ", flowPattern='"
        + flowPattern
        + '\''
        + '}';
  }
}
