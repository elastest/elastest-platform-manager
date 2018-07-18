package io.elastest.epm.pop.model.network;

import io.elastest.epm.pop.model.common.NfvEntity;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

/*The L3AddressData information element supports providing information about Layer 3 level addressing scheme and parameters applicable to a CP.
 * NOTE: The address type should be aligned with the address type supported by the layerProtocol attribute of the parent VnfExtCpd. */
@Entity
public class L3AddressData extends NfvEntity {

    /*Specify if the address assignment is the responsibility of management and orchestration function or not. If it is set to True, it is the management and orchestration function responsibility.*/
    @NotEmpty
    private boolean iPAddressAssignment;

    /*Specify if the floating IP scheme is activated on the CP or not. */
    @NotEmpty
    private boolean floatingIpActivated;

    /*Define address type. Value: IPv4 address, IPv6 address. See note.*/
    private AddressType iPAddressType;

    /*Minimum number of IP addresses to be assigned based on this L3AddressData information element.*/
    private int numberOfIpAddress;

    public boolean isiPAddressAssignment() {
        return iPAddressAssignment;
    }

    public void setiPAddressAssignment(boolean iPAddressAssignment) {
        this.iPAddressAssignment = iPAddressAssignment;
    }

    public boolean isFloatingIpActivated() {
        return floatingIpActivated;
    }

    public void setFloatingIpActivated(boolean floatingIpActivated) {
        this.floatingIpActivated = floatingIpActivated;
    }

    public AddressType getiPAddressType() {
        return iPAddressType;
    }

    public void setiPAddressType(AddressType iPAddressType) {
        this.iPAddressType = iPAddressType;
    }

    public int getNumberOfIpAddress() {
        return numberOfIpAddress;
    }

    public void setNumberOfIpAddress(int numberOfIpAddress) {
        this.numberOfIpAddress = numberOfIpAddress;
    }

    @Override
    public String toString() {
        return "L3AddressData{"
                + "iPAddressAssignment="
                + iPAddressAssignment
                + ", floatingIpActivated="
                + floatingIpActivated
                + ", iPAddressType="
                + iPAddressType
                + ", numberOfIpAddress="
                + numberOfIpAddress
                + '}';
    }
}
