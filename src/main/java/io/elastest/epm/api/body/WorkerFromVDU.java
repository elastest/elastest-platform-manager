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
 * WorkerFromVDU
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-12T11:29:25.083+02:00")

public class WorkerFromVDU   {
  @JsonProperty("type")
  @Valid
  private List<String> type = new ArrayList<String>();

  @JsonProperty("vduId")
  private String vduId = null;

  public WorkerFromVDU type(List<String> type) {
    this.type = type;
    return this;
  }

  public WorkerFromVDU addTypeItem(String typeItem) {
    this.type.add(typeItem);
    return this;
  }

  /**
   * The types of the worker.
   * @return type
  **/
  @ApiModelProperty(required = true, value = "The types of the worker.")
  @NotNull


  public List<String> getType() {
    return type;
  }

  public void setType(List<String> type) {
    this.type = type;
  }

  public WorkerFromVDU vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * The id of vdu from which to create the worker.
   * @return vduId
  **/
  @ApiModelProperty(required = true, value = "The id of vdu from which to create the worker.")
  @NotNull


  public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkerFromVDU workerFromVDU = (WorkerFromVDU) o;
    return Objects.equals(this.type, workerFromVDU.type) &&
        Objects.equals(this.vduId, workerFromVDU.vduId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, vduId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkerFromVDU {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
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

