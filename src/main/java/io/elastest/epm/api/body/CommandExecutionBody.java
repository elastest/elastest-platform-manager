package io.elastest.epm.api.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * CommandExecutionBody
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-18T18:59:43.258+02:00")

public class CommandExecutionBody   {
  @JsonProperty("command")
  private String command = null;

  @JsonProperty("awaitCompletion")
  private Boolean awaitCompletion = null;

  public CommandExecutionBody command(String command) {
    this.command = command;
    return this;
  }

   /**
   * Get command
   * @return command
  **/
  @ApiModelProperty(example = "ls /", required = true, value = "")
  @NotNull
  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public CommandExecutionBody awaitCompletion(Boolean awaitCompletion) {
    this.awaitCompletion = awaitCompletion;
    return this;
  }

   /**
   * Get awaitCompletion
   * @return awaitCompletion
  **/
  @ApiModelProperty(example = "true", required = true, value = "")
  @NotNull
  public Boolean getAwaitCompletion() {
    return awaitCompletion;
  }

  public void setAwaitCompletion(Boolean awaitCompletion) {
    this.awaitCompletion = awaitCompletion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandExecutionBody commandExecutionBody = (CommandExecutionBody) o;
    return Objects.equals(this.command, commandExecutionBody.command) &&
        Objects.equals(this.awaitCompletion, commandExecutionBody.awaitCompletion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(command, awaitCompletion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommandExecutionBody {\n");

    sb.append("    command: ").append(toIndentedString(command)).append("\n");
    sb.append("    awaitCompletion: ").append(toIndentedString(awaitCompletion)).append("\n");
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

