package io.elastest.epm.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A worker object for registering a machine where adapters can be deployed.
 */
@ApiModel(description = "A worker object for registering a machine where adapters can be deployed.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-07T18:07:29.807+01:00")
@Entity
public class Worker   {
  @JsonProperty("id")
  @Id
  private String id = null;

  @JsonProperty("ip")
  private String ip = null;

  @JsonProperty("user")
  private String user = null;

  @JsonProperty("passphrase")
  private String passphrase = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("epmIp")
  private String epmIp = null;

  @JsonProperty("keyname")
  private String keyname = null;

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
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Worker user(String user) {
    this.user = user;
    return this;
  }

   /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Worker passphrase(String passphrase) {
    this.passphrase = passphrase;
    return this;
  }

   /**
   * Get passphrase
   * @return passphrase
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPassphrase() {
    return passphrase;
  }

  public void setPassphrase(String passphrase) {
    this.passphrase = passphrase;
  }

  public Worker password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(value = "")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Worker epmIp(String epmIp) {
    this.epmIp = epmIp;
    return this;
  }

   /**
   * Get epmIp
   * @return epmIp
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getEpmIp() {
    return epmIp;
  }

  public void setEpmIp(String epmIp) {
    this.epmIp = epmIp;
  }

  public Worker keyname(String keyname) {
    this.keyname = keyname;
    return this;
  }

   /**
   * Get keyname
   * @return keyname
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getKeyname() {
    return keyname;
  }

  public void setKeyname(String keyname) {
    this.keyname = keyname;
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
        Objects.equals(this.user, worker.user) &&
        Objects.equals(this.passphrase, worker.passphrase) &&
        Objects.equals(this.password, worker.password) &&
        Objects.equals(this.epmIp, worker.epmIp) &&
        Objects.equals(this.keyname, worker.keyname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ip, user, passphrase, password, epmIp, keyname);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Worker {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    passphrase: ").append(toIndentedString(passphrase)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    epmIp: ").append(toIndentedString(epmIp)).append("\n");
    sb.append("    keyname: ").append(toIndentedString(keyname)).append("\n");
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

