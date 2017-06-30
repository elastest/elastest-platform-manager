package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The QoS information element describes QoS data for a given VL used in a DF.*/
@Entity
public class QoS extends NfvEntity {

  /*Maximum latency in ms.*/
  @NotEmpty private long latency;

  /*Maximum jitter in ms.*/
  @NotEmpty private long packetDelayVariation;

  /*Maximum packet loss ratio. Cardinality is 0 if no packetLossRatio requirement exists.*/
  private long packetLossRatio;

  /*Specifies the priority level in case of congestion on the underlying physical links.*/
  //TODO inconsistency: mentioned in IFA014 b ut not in package specs
  private int priority;

  public long getLatency() {
    return latency;
  }

  public void setLatency(long latency) {
    this.latency = latency;
  }

  public long getPacketDelayVariation() {
    return packetDelayVariation;
  }

  public void setPacketDelayVariation(long packetDelayVariation) {
    this.packetDelayVariation = packetDelayVariation;
  }

  public long getPacketLossRatio() {
    return packetLossRatio;
  }

  public void setPacketLossRatio(long packetLossRatio) {
    this.packetLossRatio = packetLossRatio;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    return "QoS{"
        + "latency="
        + latency
        + ", packetDelayVariation="
        + packetDelayVariation
        + ", packetLossRatio="
        + packetLossRatio
        + ", priority="
        + priority
        + '}';
  }
}
