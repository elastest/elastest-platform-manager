package io.elastest.epm.tosca.templates.service;

import java.util.Map;

/** Created by rvl on 27.07.17. */
public class NodeTemplate {

  private String type;
  private Map<String, Object> properties;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  @Override
  public String toString() {

    return "type: " + type + "\n" + "properties: " + properties;
  }
}
