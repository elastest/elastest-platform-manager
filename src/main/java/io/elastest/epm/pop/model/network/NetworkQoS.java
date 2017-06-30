package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*This clause describes the attributes for the NetworkQoS information element. This type gives QoS options to be supported on the virtualised network, e.g. latency, jitter, etc.*/
@Entity
public class NetworkQoS extends NfvEntity {

  /*Name given to the QoS parameter*/
  @NotEmpty private String qosName;

  /*Value of the QoS parameter*/
  @NotEmpty private String value;

  public String getQosName() {
    return qosName;
  }

  public void setQosName(String qosName) {
    this.qosName = qosName;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "NetworkQoS{" + "qosName='" + qosName + '\'' + ", value='" + value + '\'' + '}';
  }
}
