package io.elastest.epm.api.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FileUploadBody
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-18T18:59:43.258+02:00")

public class FileUploadBody   {
  @JsonProperty("remotePath")
  private String remotePath = null;

  @JsonProperty("hostPath")
  private String hostPath = null;

  public FileUploadBody remotePath(String remotePath) {
    this.remotePath = remotePath;
    return this;
  }

   /**
   * Get remotePath
   * @return remotePath
  **/
  @ApiModelProperty(example = "/", required = true, value = "")
  @NotNull
  public String getRemotePath() {
    return remotePath;
  }

  public void setRemotePath(String remotePath) {
    this.remotePath = remotePath;
  }

  public FileUploadBody hostPath(String hostPath) {
    this.hostPath = hostPath;
    return this;
  }

   /**
   * Get hostPath
   * @return hostPath
  **/
  @ApiModelProperty(example = "/", required = true, value = "")
  @NotNull
  public String getHostPath() {
    return hostPath;
  }

  public void setHostPath(String hostPath) {
    this.hostPath = hostPath;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileUploadBody fileUploadBody = (FileUploadBody) o;
    return Objects.equals(this.remotePath, fileUploadBody.remotePath) &&
        Objects.equals(this.hostPath, fileUploadBody.hostPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(remotePath, hostPath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileUploadBody {\n");

    sb.append("    remotePath: ").append(toIndentedString(remotePath)).append("\n");
    sb.append("    hostPath: ").append(toIndentedString(hostPath)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

