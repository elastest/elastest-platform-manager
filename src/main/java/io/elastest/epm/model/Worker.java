package io.elastest.epm.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Worker
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-12T12:05:16.662+02:00")
@Entity
public class Worker   {

  @Id
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("ip")
  private String ip = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("epmIp")
  private String epmIp = null;

  @JsonProperty("type")
  @Valid
  @ElementCollection
  private List<String> type = null;

  @JsonProperty("authCredentials")
  @OneToOne(cascade = CascadeType.ALL)
  private AuthCredentials authCredentials = null;

  public Worker id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Worker ip(String ip) {
    this.ip = ip;
    return this;
  }

  /**
   * Get ip
   * @return ip
  **/
  @ApiModelProperty(value = "")


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Worker vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * The vduId if the worker was created from a vdu.
   * @return vduId
  **/
  @ApiModelProperty(value = "The vduId if the worker was created from a vdu.")


  public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public Worker epmIp(String epmIp) {
    this.epmIp = epmIp;
    return this;
  }

  /**
   * This is the IP where the EPM is reachable for the Worker. This is needed because the Worker has to be able to reach the EPM for registering adapters.
   * @return epmIp
  **/
  @ApiModelProperty(value = "This is the IP where the EPM is reachable for the Worker. This is needed because the Worker has to be able to reach the EPM for registering adapters.")


  public String getEpmIp() {
    return epmIp;
  }

  public void setEpmIp(String epmIp) {
    this.epmIp = epmIp;
  }

  public Worker type(List<String> type) {
    this.type = type;
    return this;
  }

  public Worker addTypeItem(String typeItem) {
    if (this.type == null) {
      this.type = new ArrayList<String>();
    }
    this.type.add(typeItem);
    return this;
  }

  /**
   * The types which this worker supports at the moment when this information is requested.
   * @return type
  **/
  @ApiModelProperty(value = "The types which this worker supports at the moment when this information is requested.")


  public List<String> getType() {
    return type;
  }

  public void setType(List<String> type) {
    this.type = type;
  }

  public Worker authCredentials(AuthCredentials authCredentials) {
    this.authCredentials = authCredentials;
    return this;
  }

  /**
   * Get authCredentials
   * @return authCredentials
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AuthCredentials getAuthCredentials() {
    return authCredentials;
  }

  public void setAuthCredentials(AuthCredentials authCredentials) {
    this.authCredentials = authCredentials;
  }

    @PrePersist
    public void ensureId() {
        id = IdGenerator.createUUID();
    }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Worker worker = (Worker) o;
    return Objects.equals(this.id, worker.id) &&
        Objects.equals(this.ip, worker.ip) &&
        Objects.equals(this.vduId, worker.vduId) &&
        Objects.equals(this.epmIp, worker.epmIp) &&
        Objects.equals(this.type, worker.type) &&
        Objects.equals(this.authCredentials, worker.authCredentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ip, vduId, epmIp, type, authCredentials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Worker {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    epmIp: ").append(toIndentedString(epmIp)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    authCredentials: ").append(toIndentedString(authCredentials)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

