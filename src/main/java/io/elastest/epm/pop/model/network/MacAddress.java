package io.elastest.epm.pop.model.network;

public class MacAddress {

  String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "MacAddress{" + "address='" + address + '\'' + '}';
  }
}
