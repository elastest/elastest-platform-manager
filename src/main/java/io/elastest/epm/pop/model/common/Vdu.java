package io.elastest.epm.pop.model.common;

import io.elastest.epm.pop.model.image.SwImageDesc;
import io.elastest.epm.pop.model.network.VduCpd;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/*The Virtualisation Deployment Unit (VDU) is a construct supporting the description of the deployment and operational behaviour of a VNFC. A VNFC instance created based on the VDU maps to a single virtualisation container (e.g. a VM).
* NOTE 1: If no boot order is defined the default boot order defined in the VIM or NFVI shall be used.
* NOTE 2: More software images can be attached to the virtualisation container using VirtualStorage resources. See
clause 7.1.9.4.
* NOTE 3: These are constraints other than stipulating that a VNFC instance has access to a certain resource, as a
prerequisite to instantiation. The attributes virtualComputeDesc and virtualStorageDesc define the resources
required for instantiation of the VNFC instance. */
@Entity
public class Vdu extends NfvEntity {

  /*Unique identifier of this Vdu in VNFD.*/
  @NotEmpty private String vduId;

  /*Human readable name of the Vdu.*/
  @NotEmpty private String name;

  /*Human readable description of the Vdu. */
  @NotEmpty private String description;

  /*Describes network connectivity between a VNFC record (based on this Vdu) and an internal Virtual Link (VL). See clause 7.1.6.4. */
  @NotEmpty private Set<VduCpd> intCpd;

  /*Describes CPU, Memory and acceleration requirements of the Virtualisation Container realizing this Vdu. See clause 7.1.9.2.2.*/
  @NotEmpty private String virtualComputeDesc;

  /*Describes storage requirements for a VirtualStorage record attached to the virtualisation container created from virtualComputeDesc defined for this Vdu. See clause 7.1.9.4. */
  private String virtualStorageDesc;

  /*The key indicates the boot index (lowest index defines highest boot priority). The Value references a descriptor from which a valid boot device is created e.g. VirtualStorageDesc from which a VirtualStorage record is created. See note 1.*/
  private Map bootOrder;

  /*Describes the software image which is directly loaded on the virtualisation container realizing this Vdu. See clause 7.1.6.5. See note 2.*/
  private SwImageDesc swImageDesc;

  /*Describes constraints on the NFVI for the VNFC record(s) created from this Vdu. For example, aspects of a secure hosting environment for the VNFC record that involve additional entities or processes. See note 3. */
  private String nfviConstraint;

  /*Defines the virtualised resources monitoring parameters on VDU level. MonitoringParameter is defined in clause 7.1.11.3.*/
  private Set<MonitoringParameter> monitoringParameter;

  /*Describes the configurable properties of all VNFC instances based on this VDU. See clause 7.1.6.7. */
  @NotEmpty private VnfcConfigurableProperties configurableProperties;

  public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<VduCpd> getIntCpd() {
    return intCpd;
  }

  public void setIntCpd(Set<VduCpd> intCpd) {
    this.intCpd = intCpd;
  }

  public String getVirtualComputeDesc() {
    return virtualComputeDesc;
  }

  public void setVirtualComputeDesc(String virtualComputeDesc) {
    this.virtualComputeDesc = virtualComputeDesc;
  }

  public String getVirtualStorageDesc() {
    return virtualStorageDesc;
  }

  public void setVirtualStorageDesc(String virtualStorageDesc) {
    this.virtualStorageDesc = virtualStorageDesc;
  }

  public Map getBootOrder() {
    return bootOrder;
  }

  public void setBootOrder(Map bootOrder) {
    this.bootOrder = bootOrder;
  }

  public SwImageDesc getSwImageDesc() {
    return swImageDesc;
  }

  public void setSwImageDesc(SwImageDesc swImageDesc) {
    this.swImageDesc = swImageDesc;
  }

  public String getNfviConstraint() {
    return nfviConstraint;
  }

  public void setNfviConstraint(String nfviConstraint) {
    this.nfviConstraint = nfviConstraint;
  }

  public Set<MonitoringParameter> getMonitoringParameter() {
    return monitoringParameter;
  }

  public void setMonitoringParameter(Set<MonitoringParameter> monitoringParameter) {
    this.monitoringParameter = monitoringParameter;
  }

  public VnfcConfigurableProperties getConfigurableProperties() {
    return configurableProperties;
  }

  public void setConfigurableProperties(VnfcConfigurableProperties configurableProperties) {
    this.configurableProperties = configurableProperties;
  }

  @Override
  public String toString() {
    return "Vdu{"
        + "vduId='"
        + vduId
        + '\''
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", intCpd="
        + intCpd
        + ", virtualComputeDesc='"
        + virtualComputeDesc
        + '\''
        + ", virtualStorageDesc='"
        + virtualStorageDesc
        + '\''
        + ", bootOrder="
        + bootOrder
        + ", swImageDesc="
        + swImageDesc
        + ", nfviConstraint='"
        + nfviConstraint
        + '\''
        + ", monitoringParameter="
        + monitoringParameter
        + ", configurableProperties="
        + configurableProperties
        + '}';
  }
}
