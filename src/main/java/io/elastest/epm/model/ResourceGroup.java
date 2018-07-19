package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A Resource Group defines a bundle of VDUs and virtual networks which belongs together. It
 * includes also the Point-of-Presences where the virtual resources have to be allocated.
 */
@ApiModel(
        description =
                "A Resource Group defines a bundle of VDUs and virtual networks which belongs together. It includes also the Point-of-Presences where the virtual resources have to be allocated."
)
@javax.annotation.Generated(
        value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2017-08-03T17:51:47.319+02:00"
)
@Entity
public class ResourceGroup {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("vdus")
    @OneToMany(cascade = CascadeType.ALL)
    private List<VDU> vdus = new ArrayList<VDU>();

    @JsonProperty("networks")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Network> networks = new ArrayList<Network>();

    public ResourceGroup id(String id) {
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

    public ResourceGroup name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @ApiModelProperty(example = "testResourceGroupName1", required = true, value = "")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceGroup vdus(List<VDU> vdus) {
        this.vdus = vdus;
        return this;
    }

    public ResourceGroup addVdusItem(VDU vdusItem) {
        this.vdus.add(vdusItem);
        return this;
    }

    /**
     * Get vdus
     *
     * @return vdus
     */
    @ApiModelProperty(value = "")
    public List<VDU> getVdus() {
        return vdus;
    }

    public void setVdus(List<VDU> vdus) {
        this.vdus = vdus;
    }

    public ResourceGroup networks(List<Network> networks) {
        this.networks = networks;
        return this;
    }

    public ResourceGroup addNetworksItem(Network networksItem) {
        this.networks.add(networksItem);
        return this;
    }

    /**
     * Get networks
     *
     * @return networks
     */
    @ApiModelProperty(value = "")
    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResourceGroup resourceGroup = (ResourceGroup) o;
        return Objects.equals(this.id, resourceGroup.id)
                && Objects.equals(this.name, resourceGroup.name)
                && Objects.equals(this.vdus, resourceGroup.vdus)
                && Objects.equals(this.networks, resourceGroup.networks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vdus, networks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResourceGroup {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    vdus: ").append(toIndentedString(vdus)).append("\n");
        sb.append("    networks: ").append(toIndentedString(networks)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
