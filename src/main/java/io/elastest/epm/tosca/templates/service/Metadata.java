package io.elastest.epm.tosca.templates.service;

/** Created by rvl on 27.07.17. */
public class Metadata {

  private String vendor;
  private String name;
  private String version;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  @Override
  public String toString() {
    return "vendor: " + vendor + "\n" + "name: " + name + "\n" + "version: " + version + "\n";
  }
}
