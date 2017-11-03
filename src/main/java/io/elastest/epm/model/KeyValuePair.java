package io.elastest.epm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.elastest.epm.repository.IdGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.validation.constraints.*;
/** This entity is a Key-Value pair for storing metadata contained in other entities */
@ApiModel(
  description = "This entity is a Key-Value pair for storing metadata contained in other entities"
)
@javax.annotation.Generated(
  value = "io.swagger.codegen.languages.SpringCodegen",
  date = "2017-06-12T17:49:47.810+02:00"
)
@Entity
public class KeyValuePair {
  @Id
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("value")
  @Lob
  @Column(length = 20971520)
  private String value = null;

  public KeyValuePair() {}

  public KeyValuePair(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public KeyValuePair id(String id) {
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

  public KeyValuePair key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Get key
   *
   * @return key
   */
  @ApiModelProperty(example = "testKey1", required = true, value = "")
  @NotNull
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public KeyValuePair value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   *
   * @return value
   */
  @ApiModelProperty(example = "testValue1", required = true, value = "")
  @NotNull
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyValuePair keyValuePair = (KeyValuePair) o;
    return Objects.equals(this.id, keyValuePair.id)
        && Objects.equals(this.key, keyValuePair.key)
        && Objects.equals(this.value, keyValuePair.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyValuePair {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
