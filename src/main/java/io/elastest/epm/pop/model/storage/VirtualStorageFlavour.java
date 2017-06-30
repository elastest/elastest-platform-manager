package io.elastest.epm.pop.model.storage;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/** Created by mpa on 28.12.16. */
/*This clause describes the attributes for the VirtualStorageFlavour information element. The VirtualStorageFlavour information element encapsulates information for storage flavours. A storage flavour includes information about the size of the storage, and the type of storage.*/
@Entity
public class VirtualStorageFlavour extends NfvEntity {

  /*Identifier of the storage flavour.*/
  @NotEmpty private String flavourId;

  /*Element containing information about the size of virtualised storage resource (e.g. size of volume, in GB), the type of storage (e.g. volume, object), and support for RDMA. See clause 8.4.6.3.*/
  @NotEmpty private VirtualStorageData storageAttributes;

  public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
  }

  public VirtualStorageData getStorageAttributes() {
    return storageAttributes;
  }

  public void setStorageAttributes(VirtualStorageData storageAttributes) {
    this.storageAttributes = storageAttributes;
  }

  @Override
  public String toString() {
    return "VirtualStorageFlavour{"
        + "flavourId='"
        + flavourId
        + '\''
        + ", storageAttributes="
        + storageAttributes
        + '}';
  }
}
