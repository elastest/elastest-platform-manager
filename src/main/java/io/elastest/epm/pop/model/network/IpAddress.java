package io.elastest.epm.pop.model.network;

public class IpAddress {

  String address;

  IpVersion version;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public IpVersion getVersion() {
    return version;
  }

  public void setVersion(IpVersion version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "IpAddress{" + "address='" + address + '\'' + ", version=" + version + '}';
  }
}
