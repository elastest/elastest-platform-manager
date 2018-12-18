package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This entity defines the network connectivity and details where the VDUs are connected to.
 */
@ApiModel(
        description =
                "This entity defines the network connectivity and details where the VDUs are connected to."
)
@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2017-06-12T17:49:47.810+02:00"
)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "poPName"}))
public class Network {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("networkId")
    private String networkId = null;

    @JsonProperty("cidr")
    private String cidr = null;

    @JsonProperty("poPName")
    private String poPName = null;

    public Network id(String id) {
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

    public Network name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @ApiModelProperty(example = "testNetwork1", required = true, value = "")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Network networkId(String networkId) {
        this.networkId = networkId;
        return this;
    }

    /**
     * Get networkId
     *
     * @return networkId
     */
    @ApiModelProperty(example = "1234-abcd", required = true, value = "")
    @NotNull
    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public Network cidr(String cidr) {
        this.cidr = cidr;
        return this;
    }

    /**
     * Get cidr
     *
     * @return cidr
     */
    @ApiModelProperty(example = "192.168.1.1/24", required = true, value = "")
    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public Network poPInfoName(String poPInfoName) {
        this.poPName = poPInfoName;
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

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Network network = (Network) o;
        return Objects.equals(this.id, network.id)
                && Objects.equals(this.name, network.name)
                && Objects.equals(this.cidr, network.cidr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cidr);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Network {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    networkId: ").append(toIndentedString(networkId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    cidr: ").append(toIndentedString(cidr)).append("\n");
        sb.append("    poPName: ").append(toIndentedString(poPName)).append("\n");
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
