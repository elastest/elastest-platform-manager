package io.elastest.epm.pop.model.storage;

import io.elastest.epm.pop.model.common.NfvEntity;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/** Created by mpa on 29.12.16. */
/*This clause describes the attributes for the VirtualStorageData information element.*/
@Entity
public class VirtualStorageData extends NfvEntity {

  /*Type of virtualised storage resource (e.g. volume, object).*/
  @NotEmpty private String typeOfStorage;

  /*Size of virtualised storage resource (e.g. size of volume, in GB).*/
  @NotEmpty private long sizeOfStorage;

  /*Indicates if the storage supports RDMA.*/
  private boolean rdmaEnabled;

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

  @Override
  public String toString() {
    return "VirtualStorageData{"
        + "typeOfStorage='"
        + typeOfStorage
        + '\''
        + ", sizeOfStorage="
        + sizeOfStorage
        + ", rdmaEnabled="
        + rdmaEnabled
        + '}';
  }
}
