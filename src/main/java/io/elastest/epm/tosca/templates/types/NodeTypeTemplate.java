package io.elastest.epm.tosca.templates.types;

import java.util.Map;

/** Created by rvl on 27.07.17. */
public class NodeTypeTemplate {

  private String derived_from;
  private Map<String, Map<String, String>> properties;

  public Map<String, Map<String, String>> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Map<String, String>> properties) {
    this.properties = properties;
  }

  public String getDerived_from() {
    return derived_from;
  }

  public void setDerived_from(String derived_from) {
    this.derived_from = derived_from;
  }

  @Override
  public String toString() {
    return "derived_from: " + derived_from + "\n" + "properties: " + properties.toString() + "\n";
  }
}
