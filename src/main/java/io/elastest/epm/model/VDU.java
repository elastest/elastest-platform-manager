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
@Entity
public class VDU {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("computeId")
    private String computeId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("imageName")
    private String imageName = null;

    @JsonProperty("ip")
    private String ip = null;

    @JsonProperty("netName")
    private String netName = null;

    @JsonProperty("poPName")
    private String poPName = null;

    /**
     * Gets or Sets status
     */
    public enum StatusEnum {
        INITIALIZING("initializing"),

        INITIALIZED("initialized"),

        DEPLOYING("deploying"),

        DEPLOYED("deployed"),

        RUNNING("running"),

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

    @JsonProperty("key")
    private String key = null;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("metadata")
    private List<KeyValuePair> metadata = new ArrayList<KeyValuePair>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("events")
    private List<Event> events = new ArrayList<Event>();

    public VDU id(String id) {
        this.id = id;
        return this;
    }

    @PrePersist
    public void ensureId() {
        id = IdGenerator.createUUID();
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
     * Get computeId
     *
     * @return computeId
     */
    @ApiModelProperty(example = "1234-abcd", required = true, value = "")
    @NotNull
    public String getComputeId() {
        return computeId;
    }

    public void setComputeId(String computeId) {
        this.computeId = computeId;
    }

    public VDU computeId(String computeId) {
        this.computeId = computeId;
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

    public VDU imageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    /**
     * Get ip
     *
     * @return ip
     */
    @ApiModelProperty(example = "172.0.0.1", required = true, value = "")
    @NotNull
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public VDU ip(String ip) {
        this.ip = ip;
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

    /**
     * Get netName
     *
     * @return netName
     */
    @ApiModelProperty(example = "testNetworkName", required = true, value = "")
    @NotNull
    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public VDU poPName(String poPName) {
        this.poPName = poPName;
        return this;
    }

    /**
     * Get poPName
     *
     * @return poPName
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getPoPName() {
        return poPName;
    }

    public void setPoPName(String poPName) {
        this.poPName = poPName;
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

    public VDU key(String key) {
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
        return Objects.equals(this.computeId, VDU.computeId) &&
                Objects.equals(this.events, VDU.events) &&
                Objects.equals(this.id, VDU.id) &&
                Objects.equals(this.imageName, VDU.imageName) &&
                Objects.equals(this.ip, VDU.ip) &&
                Objects.equals(this.metadata, VDU.metadata) &&
                Objects.equals(this.name, VDU.name) &&
                Objects.equals(this.netName, VDU.netName) &&
                Objects.equals(this.poPName, VDU.poPName) &&
                Objects.equals(this.status, VDU.status) &&
                Objects.equals(this.key, VDU.key);

    }

    @Override
    public int hashCode() {
        return Objects.hash(computeId, events, id, imageName, ip, metadata, name, netName, poPName, status, key);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VDU {\n");

        sb.append("    computeId: ").append(toIndentedString(computeId)).append("\n");
        sb.append("    events: ").append(toIndentedString(events)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    imageName: ").append(toIndentedString(imageName)).append("\n");
        sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
        sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    netName: ").append(toIndentedString(netName)).append("\n");
        sb.append("    poPName: ").append(toIndentedString(poPName)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    key: ").append(toIndentedString(key)).append("\n");
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
