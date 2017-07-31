package io.elastest.epm.tosca.templates.service;

import java.util.List;

/** Created by rvl on 27.07.17. */
public class ServiceTemplate {

  private String tosca_definitions_version;
  private TopologyTemplate topology_template;
  private List<String> imports;
  private Metadata metadata;

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public List<String> getImports() {
    return imports;
  }

  public void setImports(List<String> imports) {
    this.imports = imports;
  }

  public TopologyTemplate getTopology_template() {
    return topology_template;
  }

  public void setTopology_template(TopologyTemplate topology_template) {
    this.topology_template = topology_template;
  }

  public String getTosca_definitions_version() {
    return tosca_definitions_version;
  }

  public void setTosca_definitions_version(String tosca_definitions_version) {
    this.tosca_definitions_version = tosca_definitions_version;
  }

  public String toString() {
    return "tosca_version: "
        + tosca_definitions_version
        + "\n"
        + "metadata: "
        + metadata
        + "\n"
        + "topology_template: "
        + topology_template
        + "\n";
  }
}
