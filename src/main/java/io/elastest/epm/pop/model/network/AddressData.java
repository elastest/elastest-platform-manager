package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The AddressData information element supports providing information about the addressing scheme and parameters applicable to a CP.*/
@Entity
public class AddressData extends NfvEntity {

  /*Describes the type of the address to be assigned to the CP instantiated from the parent CPD. Value: MAC address, IP address, …. The content type shall be aligned with the address type supported by the layerProtocol attribute of the parent CPD.*/
  @NotEmpty private AddressType addressType;

  /*Provides the information on the MAC addresses to be assigned to the CP(s) instantiated from the parent CPD. Shall be present when the addressType is MAC address.*/
  //TODO type not specified
  private String l2AddressData;

  /*Provides the information on the IP addresses to be assigned to the CP instantiated from the parent CPD. Shall be present when the addressType is IP address. See clause 7.1.3.3.1.*/
  private L3AddressData l3AddressData;

  public AddressType getAddressType() {
    return addressType;
  }

  public void setAddressType(AddressType addressType) {
    this.addressType = addressType;
  }

  public String getL2AddressData() {
    return l2AddressData;
  }

  public void setL2AddressData(String l2AddressData) {
    this.l2AddressData = l2AddressData;
  }

  public L3AddressData getL3AddressData() {
    return l3AddressData;
  }

  public void setL3AddressData(L3AddressData l3AddressData) {
    this.l3AddressData = l3AddressData;
  }

  @Override
  public String toString() {
    return "AddressData{"
        + "addressType="
        + addressType
        + ", l2AddressData='"
        + l2AddressData
        + '\''
        + ", l3AddressData="
        + l3AddressData
        + '}';
  }
}
