package io.elastest.epm.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Everything needed for accessing a machine using SSH
 */
@ApiModel(description = "Everything needed for accessing a machine using SSH")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-12T12:05:16.662+02:00")
@Entity
public class AuthCredentials   {

  @JsonProperty("id")
  @Id
  private String id = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("user")
  private String user = null;

  @JsonProperty("passphrase")
  private String passphrase = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("port")
  private Integer port = null;

  public AuthCredentials id(String id) {
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

  public AuthCredentials key(String key) {
    this.key = key;
    return this;
  }

  /**
   * The name of the key saved in EPM, which can be used to execute runtime operations on this VDU.
   * @return key
  **/
  @ApiModelProperty(example = "vdu_1-key", value = "The name of the key saved in EPM, which can be used to execute runtime operations on this VDU.")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public AuthCredentials user(String user) {
    this.user = user;
    return this;
  }

  /**
   * This is the user, which the EPM will use when trying to ssh in to the Worker.
   * @return user
  **/
  @ApiModelProperty(example = "ubuntu", required = true, value = "This is the user, which the EPM will use when trying to ssh in to the Worker.")
  @NotNull


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public AuthCredentials passphrase(String passphrase) {
    this.passphrase = passphrase;
    return this;
  }

  /**
   * This is the Passphrase of the Key provided for connecting to the Worker.
   * @return passphrase
  **/
  @ApiModelProperty(value = "This is the Passphrase of the Key provided for connecting to the Worker.")


  public String getPassphrase() {
    return passphrase;
  }

  public void setPassphrase(String passphrase) {
    this.passphrase = passphrase;
  }

  public AuthCredentials password(String password) {
    this.password = password;
    return this;
  }

  /**
   * This is the password of the user, which can be left blank if no password is needed.
   * @return password
  **/
  @ApiModelProperty(value = "This is the password of the user, which can be left blank if no password is needed.")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AuthCredentials port(Integer port) {
    this.port = port;
    return this;
  }

  /**
   * The ssh port of the worker, where the EPM can reach it.
   * @return port
  **/
  @ApiModelProperty(value = "The ssh port of the worker, where the EPM can reach it.")


  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
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
    AuthCredentials authCredentials = (AuthCredentials) o;
    return Objects.equals(this.id, authCredentials.id) &&
        Objects.equals(this.key, authCredentials.key) &&
        Objects.equals(this.user, authCredentials.user) &&
        Objects.equals(this.passphrase, authCredentials.passphrase) &&
        Objects.equals(this.password, authCredentials.password) &&
        Objects.equals(this.port, authCredentials.port);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, user, passphrase, password, port);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthCredentials {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    passphrase: ").append(toIndentedString(passphrase)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
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

