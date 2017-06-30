package io.elastest.epm.pop.model.storage;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/** Created by mpa on 27.12.16. */
/*The VirtualStorageDesc information element supports the specifications of requirements related to virtual storage resources.*/
@Entity
public class VirtualStorageDesc extends NfvEntity {

  /*Unique identifier of this VirtualStorageDesc in the VNFD.*/
  @NotEmpty private String id;

  /*Type of virtualised storage resource (e.g. volume, object).*/
  @NotEmpty private String typeOfStorage;

  /*Size of virtualised storage resource (e.g. size of volume, in GB).*/
  @NotEmpty private long sizeOfStorage;

  /*Indicate if the storage support RDMA.*/
  private boolean rdmaEnabled;

  /*Software image to be loaded on the VirtualStorage Resource created based on this VirtualStorageDesc.*/
  private String swImageDesc;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTypeOfStorage() {
    return typeOfStorage;
  }

  public void setTypeOfStorage(String typeOfStorage) {
    this.typeOfStorage = typeOfStorage;
  }

  public long getSizeOfStorage() {
    return sizeOfStorage;
  }

  public void setSizeOfStorage(long sizeOfStorage) {
    this.sizeOfStorage = sizeOfStorage;
  }

  public boolean isRdmaEnabled() {
    return rdmaEnabled;
  }

  public void setRdmaEnabled(boolean rdmaEnabled) {
    this.rdmaEnabled = rdmaEnabled;
  }

  public String getSwImageDesc() {
    return swImageDesc;
  }

  public void setSwImageDesc(String swImageDesc) {
    this.swImageDesc = swImageDesc;
  }

  @Override
  public String toString() {
    return "VirtualStorageDesc{"
        + "id='"
        + id
        + '\''
        + ", typeOfStorage='"
        + typeOfStorage
        + '\''
        + ", sizeOfStorage="
        + sizeOfStorage
        + ", rdmaEnabled="
        + rdmaEnabled
        + ", swImageDesc='"
        + swImageDesc
        + '\''
        + '}';
  }
}
