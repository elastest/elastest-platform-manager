package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;
/**
 * A Virtual Deployment Unit (VDU) describes the capabilities of virtualized computing (Containers,
 * VMs) and networking resources.
 */
@ApiModel(
  description =
      "A Virtual Deployment Unit (VDU) describes the capabilities of virtualized computing (Containers, VMs) and networking resources."
)
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
public class VDU {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("imageName")
  private String imageName = null;

  @JsonProperty("ip")
  private String ip = null;

  @JsonProperty("netId")
  private String netId = null;

  @JsonProperty("vimInfo")
  private VimInfo vimInfo = null;

  /** Gets or Sets status */
  public enum StatusEnum {
    INITIALIZING("initializing"),

    INITIALIZED("initialized"),

    DEPLOYING("deploying"),

    DEPLOYED("deployed"),

    UNDEPLOYING("undeploying"),

    UNDEPLOYED("undeployed"),

    ERROR("error");

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

  @JsonProperty("metadata")
  private List<KeyValuePair> metadata = new ArrayList<KeyValuePair>();

  @JsonProperty("events")
  private List<Event> events = new ArrayList<Event>();

  public VDU id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @ApiModelProperty(example = "1234-abcd", value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VDU name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   */
  @ApiModelProperty(example = "testVdu1", required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VDU imageName(String imageName) {
    this.imageName = imageName;
    return this;
  }

  /**
   * Get imageName
   *
   * @return imageName
   */
  @ApiModelProperty(example = "testImage1", required = true, value = "")
  @NotNull
  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public VDU ip(String ip) {
    this.ip = ip;
    return this;
  }

  /**
   * Get ip
   *
   * @return ip
   */
  @ApiModelProperty(example = "127.0.0.1", value = "")
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public VDU netId(String netId) {
    this.netId = netId;
    return this;
  }

  /**
   * Get netId
   *
   * @return netId
   */
  @ApiModelProperty(example = "1234-abcd", required = true, value = "")
  @NotNull
  public String getNetId() {
    return netId;
  }

  public void setNetId(String netId) {
    this.netId = netId;
  }

  public VDU vimInfo(VimInfo vimInfo) {
    this.vimInfo = vimInfo;
    return this;
  }

  /**
   * Get vimInfo
   *
   * @return vimInfo
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public VimInfo getVimInfo() {
    return vimInfo;
  }

  public void setVimInfo(VimInfo vimInfo) {
    this.vimInfo = vimInfo;
  }

  public VDU status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @ApiModelProperty(value = "")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public VDU metadata(List<KeyValuePair> metadata) {
    this.metadata = metadata;
    return this;
  }

  public VDU addMetadataItem(KeyValuePair metadataItem) {
    this.metadata.add(metadataItem);
    return this;
  }

  /**
   * Get metadata
   *
   * @return metadata
   */
  @ApiModelProperty(value = "")
  public List<KeyValuePair> getMetadata() {
    return metadata;
  }

  public void setMetadata(List<KeyValuePair> metadata) {
    this.metadata = metadata;
  }

  public VDU events(List<Event> events) {
    this.events = events;
    return this;
  }

  public VDU addEventsItem(Event eventsItem) {
    this.events.add(eventsItem);
    return this;
  }

  /**
   * Get events
   *
   * @return events
   */
  @ApiModelProperty(value = "")
  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VDU VDU = (VDU) o;
    return Objects.equals(this.id, VDU.id)
        && Objects.equals(this.name, VDU.name)
        && Objects.equals(this.imageName, VDU.imageName)
        && Objects.equals(this.ip, VDU.ip)
        && Objects.equals(this.netId, VDU.netId)
        && Objects.equals(this.vimInfo, VDU.vimInfo)
        && Objects.equals(this.status, VDU.status)
        && Objects.equals(this.metadata, VDU.metadata)
        && Objects.equals(this.events, VDU.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, imageName, ip, netId, vimInfo, status, metadata, events);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VDU {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    imageName: ").append(toIndentedString(imageName)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
    sb.append("    netId: ").append(toIndentedString(netId)).append("\n");
    sb.append("    vimInfo: ").append(toIndentedString(vimInfo)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
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
