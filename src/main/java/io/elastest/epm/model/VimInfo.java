package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;
/** This entity contains information about the Virtualised Infrastructure Manager (VIM) */
@ApiModel(
  description =
      "This entity contains information about the Virtualised Infrastructure Manager (VIM)"
)
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
public class VimInfo {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("interfaceInfo")
  private List<KeyValuePair> interfaceInfo = new ArrayList<KeyValuePair>();

  @JsonProperty("accessInfo")
  private List<KeyValuePair> accessInfo = new ArrayList<KeyValuePair>();

  @JsonProperty("interfaceEndpoint")
  private String interfaceEndpoint = null;

  public VimInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the VIM
   *
   * @return id
   */
  @ApiModelProperty(example = "1234-abcd", value = "Identifier of the VIM")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VimInfo name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable identifier of this VimInfo information element
   *
   * @return name
   */
  @ApiModelProperty(
    example = "testVimName",
    required = true,
    value = "Human-readable identifier of this VimInfo information element"
  )
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VimInfo interfaceInfo(List<KeyValuePair> interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
    return this;
  }

  public VimInfo addInterfaceInfoItem(KeyValuePair interfaceInfoItem) {
    this.interfaceInfo.add(interfaceInfoItem);
    return this;
  }

  /**
   * Information about the interface(s) to the VIM, including VIM provider type, API version, and
   * protocol type.
   *
   * @return interfaceInfo
   */
  @ApiModelProperty(
    example = "[{&quot;key&quot;:&quot;type&quot;,&quot;value&quot;:&quot;docker&quot;}]",
    required = true,
    value =
        "Information about the interface(s) to the VIM, including VIM provider type, API version, and protocol type."
  )
  @NotNull
  public List<KeyValuePair> getInterfaceInfo() {
    return interfaceInfo;
  }

  public void setInterfaceInfo(List<KeyValuePair> interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
  }

  public VimInfo accessInfo(List<KeyValuePair> accessInfo) {
    this.accessInfo = accessInfo;
    return this;
  }

  public VimInfo addAccessInfoItem(KeyValuePair accessInfoItem) {
    this.accessInfo.add(accessInfoItem);
    return this;
  }

  /**
   * Authentication credentials for accessing the VIM. Examples may include those to support
   * different authentication schemes, e.g. OAuth, Token, etc.
   *
   * @return accessInfo
   */
  @ApiModelProperty(
    required = true,
    value =
        "Authentication credentials for accessing the VIM. Examples may include those to support different authentication schemes, e.g. OAuth, Token, etc."
  )
  @NotNull
  public List<KeyValuePair> getAccessInfo() {
    return accessInfo;
  }

  public void setAccessInfo(List<KeyValuePair> accessInfo) {
    this.accessInfo = accessInfo;
  }

  public VimInfo interfaceEndpoint(String interfaceEndpoint) {
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
    VimInfo vimInfo = (VimInfo) o;
    return Objects.equals(this.id, vimInfo.id)
        && Objects.equals(this.name, vimInfo.name)
        && Objects.equals(this.interfaceInfo, vimInfo.interfaceInfo)
        && Objects.equals(this.accessInfo, vimInfo.accessInfo)
        && Objects.equals(this.interfaceEndpoint, vimInfo.interfaceEndpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, interfaceInfo, accessInfo, interfaceEndpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimInfo {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    interfaceInfo: ").append(toIndentedString(interfaceInfo)).append("\n");
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
