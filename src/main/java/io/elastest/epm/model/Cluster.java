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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Cluster
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-12T12:05:16.662+02:00")
@Entity
public class Cluster   {

  @JsonProperty("id")
  @Id
  private String id = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("master")
  @OneToOne(cascade = CascadeType.ALL)
  private Worker master = null;

  @JsonProperty("nodes")
  @Valid
  @ManyToMany(cascade = CascadeType.ALL)
  private List<Worker> nodes = null;

  @JsonProperty("resourceGroupId")
  private String resourceGroupId = null;

  public Cluster id(String id) {
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

  public Cluster type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the Cluster.
   * @return type
  **/
  @ApiModelProperty(example = "kubernetes", required = true, value = "Type of the Cluster.")
  @NotNull


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Cluster master(Worker master) {
    this.master = master;
    return this;
  }

  /**
   * Get master
   * @return master
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Worker getMaster() {
    return master;
  }

  public void setMaster(Worker master) {
    this.master = master;
  }

  public Cluster nodes(List<Worker> nodes) {
    this.nodes = nodes;
    return this;
  }

  public Cluster addNodesItem(Worker nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<Worker>();
    }
    this.nodes.add(nodesItem);
    return this;
  }

  /**
   * Get nodes
   * @return nodes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Worker> getNodes() {
    return nodes;
  }

  public void setNodes(List<Worker> nodes) {
    this.nodes = nodes;
  }

  public Cluster resourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
    return this;
  }

  /**
   * Get resourceGroupId
   * @return resourceGroupId
  **/
  @ApiModelProperty(value = "")


  public String getResourceGroupId() {
    return resourceGroupId;
  }

  public void setResourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
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
    Cluster cluster = (Cluster) o;
    return Objects.equals(this.id, cluster.id) &&
        Objects.equals(this.type, cluster.type) &&
        Objects.equals(this.master, cluster.master) &&
        Objects.equals(this.nodes, cluster.nodes) &&
        Objects.equals(this.resourceGroupId, cluster.resourceGroupId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, master, nodes, resourceGroupId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cluster {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    master: ").append(toIndentedString(master)).append("\n");
    sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
    sb.append("    resourceGroupId: ").append(toIndentedString(resourceGroupId)).append("\n");
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

