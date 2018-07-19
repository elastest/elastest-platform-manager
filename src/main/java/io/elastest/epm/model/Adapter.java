package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.*;

/**
 * Adapter
 */
@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2018-03-22T14:32:12.348+01:00"
)
@Entity
public class Adapter {

    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("endpoint")
    private String endpoint = null;

    @JsonProperty("type")
    private String type = null;

    public Adapter id(String id) {
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
    @ApiModelProperty(value = "")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Adapter endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * Get endpoint
     *
     * @return endpoint
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Adapter type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Adapter adapter = (Adapter) o;
        return Objects.equals(this.id, adapter.id)
                && Objects.equals(this.endpoint, adapter.endpoint)
                && Objects.equals(this.type, adapter.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endpoint, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Adapter {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
