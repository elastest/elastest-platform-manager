package io.elastest.epm.pop.model.storage;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.pop.model.common.NfvEntity;
import io.elastest.epm.pop.model.common.OperationalState;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

/** Created by mpa on 29.12.16. */
/*The VirtualStorage information element encapsulates information of an instantiated virtualised storage resource.*/
public class VirtualStorage extends NfvEntity {

  /*Identifier of the virtualised storage resource.*/
  @NotEmpty private String storageId;

  /*Name of the virtualised storage resource.*/
  private String storageName;

  /*Identifier of the storage flavour used to instantiate this virtual storage.*/
  @NotEmpty private String flavourId;

  /*Type of virtualised storage resource (e.g. volume, object).*/
  @NotEmpty private String typeOfStorage;

  /*Size of virtualised storage resource (e.g. size of volume, in GB).*/
  @NotEmpty private long sizeOfStorage;

  /*Indicates if the storage supports RDMA.*/
  @NotEmpty private boolean rdmaEnabled;

  /*Identifier of the virtualised resource that owns and uses such a virtualised storage resource. The value can be NULL if the virtualised storage is not attached yet to any other resource (e.g. a virtual machine).*/
  private String ownerId;

  /*If present, it identifies the Resource Zone where the virtual storage resources have been allocated.*/
  private String zoneId;

  /*Identifier of the host where the virtualised storage resource is allocated. A cardinality of 0 refers to distributed storage solutions.*/
  private String hostId;

  /*Operational state of the resource.*/
  @NotEmpty private OperationalState operationalState;

  /*List of metadata key-value pairs used by the consumer to associate meaningful metadata to the related virtualised resource.*/
  private Set<KeyValuePair> metadata;

  public String getStorageId() {
    return storageId;
  }

  public void setStorageId(String storageId) {
    this.storageId = storageId;
  }

  public String getStorageName() {
    return storageName;
  }

  public void setStorageName(String storageName) {
    this.storageName = storageName;
  }

  public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
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

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }

  public String getHostId() {
    return hostId;
  }

  public void setHostId(String hostId) {
    this.hostId = hostId;
  }

  public OperationalState getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(OperationalState operationalState) {
    this.operationalState = operationalState;
  }

  public Set<KeyValuePair> getMetadata() {
    return metadata;
  }

  public void setMetadata(Set<KeyValuePair> metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "VirtualStorage{"
        + "metadata="
        + metadata
        + ", operationalState="
        + operationalState
        + ", hostId='"
        + hostId
        + '\''
        + ", zoneId='"
        + zoneId
        + '\''
        + ", ownerId='"
        + ownerId
        + '\''
        + ", rdmaEnabled="
        + rdmaEnabled
        + ", sizeOfStorage="
        + sizeOfStorage
        + ", typeOfStorage='"
        + typeOfStorage
        + '\''
        + ", flavourId='"
        + flavourId
        + '\''
        + ", storageName='"
        + storageName
        + '\''
        + ", storageId='"
        + storageId
        + '\''
        + '}';
  }
}
