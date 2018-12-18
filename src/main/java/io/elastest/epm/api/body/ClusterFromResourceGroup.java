package io.elastest.epm.api.body;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ClusterFromResourceGroup
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-16T14:10:31.267+02:00")

public class ClusterFromResourceGroup   {
  @JsonProperty("type")
  @Valid
  private List<String> type = new ArrayList<String>();

  @JsonProperty("resourceGroupId")
  private String resourceGroupId = null;

  @JsonProperty("masterId")
  private String masterId = null;

  public ClusterFromResourceGroup type(List<String> type) {
    this.type = type;
    return this;
  }

  public ClusterFromResourceGroup addTypeItem(String typeItem) {
    this.type.add(typeItem);
    return this;
  }

  /**
   * The types of the worker.
   * @return type
  **/
  @ApiModelProperty(example = "\"kubernetes\"", required = true, value = "The types of the worker.")
  @NotNull


  public List<String> getType() {
    return type;
  }

  public void setType(List<String> type) {
    this.type = type;
  }

  public ClusterFromResourceGroup resourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
    return this;
  }

  /**
   * The id of vdu from which to create the worker.
   * @return resourceGroupId
  **/
  @ApiModelProperty(required = true, value = "The id of vdu from which to create the worker.")
  @NotNull


  public String getResourceGroupId() {
    return resourceGroupId;
  }

  public void setResourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
  }

  public ClusterFromResourceGroup masterId(String masterId) {
    this.masterId = masterId;
    return this;
  }

  /**
   * The ID of the VDU which will serve as the master node. This should be an ID of a VDU which belongs to the specified Ressource Group.
   * @return masterId
  **/
  @ApiModelProperty(required = true, value = "The ID of the VDU which will serve as the master node. This should be an ID of a VDU which belongs to the specified Ressource Group.")
  @NotNull


  public String getMasterId() {
    return masterId;
  }

  public void setMasterId(String masterId) {
    this.masterId = masterId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClusterFromResourceGroup clusterFromResourceGroup = (ClusterFromResourceGroup) o;
    return Objects.equals(this.type, clusterFromResourceGroup.type) &&
        Objects.equals(this.resourceGroupId, clusterFromResourceGroup.resourceGroupId) &&
        Objects.equals(this.masterId, clusterFromResourceGroup.masterId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, resourceGroupId, masterId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClusterFromResourceGroup {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    resourceGroupId: ").append(toIndentedString(resourceGroupId)).append("\n");
    sb.append("    masterId: ").append(toIndentedString(masterId)).append("\n");
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

