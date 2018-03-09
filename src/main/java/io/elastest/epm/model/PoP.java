package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;
/** This entity contains information about the Point-of-Presence (PoP) */
@ApiModel(description = "This entity contains information about the Point-of-Presence (PoP)")
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
@Entity
public class PoP {
  @Id
  @JsonProperty("id")
  private String id = null;

  @Column(unique = true)
  @JsonProperty("name")
  private String name = null;

  @OneToMany(cascade = CascadeType.ALL)
  @JsonProperty("interfaceInfo")
  private List<KeyValuePair> interfaceInfo = new ArrayList<KeyValuePair>();

  @OneToMany(cascade = CascadeType.ALL)
  @JsonProperty("accessInfo")
  private List<KeyValuePair> accessInfo = new ArrayList<KeyValuePair>();

  @JsonProperty("interfaceEndpoint")
  private String interfaceEndpoint = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    CONFIGURE("configure"),

    ACTIVE("active");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }



  public PoP id(String id) {
    this.id = id;
    return this;
  }

  @PrePersist
  public void ensureId() {
    id = IdGenerator.createUUID();
  }

  /**
   * Identifier of the PoP
   *
   * @return id
   */
  @ApiModelProperty(example = "1234-abcd", value = "Identifier of the PoP")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PoP name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable identifier of this PoP information element
   *
   * @return name
   */
  @ApiModelProperty(
    example = "testPoPName",
    required = true,
    value = "Human-readable identifier of this PoP information element"
  )
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PoP interfaceInfo(List<KeyValuePair> interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
    return this;
  }

  public PoP addInterfaceInfoItem(KeyValuePair interfaceInfoItem) {
    this.interfaceInfo.add(interfaceInfoItem);
    return this;
  }

  /**
   * Information about the interface(s) to the PoP, including PoP provider type, API version, and
   * protocol type.
   *
   * @return interfaceInfo
   */
  @ApiModelProperty(
    example = "[{&quot;key&quot;:&quot;type&quot;,&quot;value&quot;:&quot;docker&quot;}]",
    required = true,
    value =
        "Information about the interface(s) to the PoP, including PoP provider type, API version, and protocol type."
  )
  @NotNull
  public List<KeyValuePair> getInterfaceInfo() {
    return interfaceInfo;
  }

  public void setInterfaceInfo(List<KeyValuePair> interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
  }

  public PoP accessInfo(List<KeyValuePair> accessInfo) {
    this.accessInfo = accessInfo;
    return this;
  }

  public PoP addAccessInfoItem(KeyValuePair accessInfoItem) {
    this.accessInfo.add(accessInfoItem);
    return this;
  }

  /**
   * Authentication credentials for accessing the PoP. Examples may include those to support
   * different authentication schemes, e.g. OAuth, Token, etc.
   *
   * @return accessInfo
   */
  @ApiModelProperty(
    required = true,
    value =
        "Authentication credentials for accessing the PoP. Examples may include those to support different authentication schemes, e.g. OAuth, Token, etc."
  )
  @NotNull
  public List<KeyValuePair> getAccessInfo() {
    return accessInfo;
  }

  public void setAccessInfo(List<KeyValuePair> accessInfo) {
    this.accessInfo = accessInfo;
  }

  public PoP interfaceEndpoint(String interfaceEndpoint) {
    this.interfaceEndpoint = interfaceEndpoint;
    return this;
  }

  /**
   * Information about the interface endpoint. An example is a URL.
   *
   * @return interfaceEndpoint
   */
  @ApiModelProperty(
    example = "localhost",
    required = true,
    value = "Information about the interface endpoint. An example is a URL."
  )
  @NotNull
  public String getInterfaceEndpoint() {
    return interfaceEndpoint;
  }

  public void setInterfaceEndpoint(String interfaceEndpoint) {
    this.interfaceEndpoint = interfaceEndpoint;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PoP poP = (PoP) o;
    return Objects.equals(this.id, poP.id)
        && Objects.equals(this.name, poP.name)
        && Objects.equals(this.interfaceInfo, poP.interfaceInfo)
        && Objects.equals(this.accessInfo, poP.accessInfo)
        && Objects.equals(this.interfaceEndpoint, poP.interfaceEndpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, interfaceInfo, accessInfo, interfaceEndpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoP {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    interfaceInfo: ").append(toIndentedString(interfaceInfo)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    accessInfo: ").append(toIndentedString(accessInfo)).append("\n");
    sb.append("    interfaceEndpoint: ").append(toIndentedString(interfaceEndpoint)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
