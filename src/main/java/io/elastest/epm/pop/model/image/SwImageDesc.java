package io.elastest.epm.pop.model.image;

import io.elastest.epm.pop.model.common.NfvEntity;

import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

/*This information element describes requested additional capability for a particular VDU. Such a capability may be for acceleration or specific tasks.*/
public class SwImageDesc extends NfvEntity {

    /*THe identifier of this software image.*/
    @NotEmpty
    private String id;

    /*The name of this software image.*/
    @NotEmpty
    private String name;

    /*The version of this software image.*/
    @NotEmpty
    private String version;

    /*The checksum of the software image file.*/
    @NotEmpty
    private String checksum;

    /*The container format describes the container file format in which software image is provided.*/
    @NotEmpty
    private String containerFormat;

    /*The disk format of a software image is the format of the underlying disk image.*/
    @NotEmpty
    private String diskFormat;

    /*The minimal disk size requirement for this software image. The value of the "size of storage" attribute of the VirtualStorageDesc referencing this SwImageDesc shall not be smaller than the value of minDisk.*/
    @NotEmpty
    private long minDisk;

    /*The minimal RAM requirement for this software image. The value of the "size" attribute of VirtualMemoryData of the Vdu referencing this SwImageDesc shall not be smaller than the value of minRam.*/
    private long minRam;

    /*The size of this software image.*/
    @NotEmpty
    private long size;

    /*This is a reference to the actual software image. The reference can be relative to the root of the VNF Package or can be a URL.*/
    @NotEmpty
    private String swImage;

    /*Identifies the operating system used in the software image. This attribute may also identify if a 32 bit or 64 bit software image is used.*/
    private String operatingSystem;

    /*Identifies the virtualisation environments (e.g. hypervisor) compatible with this software image.*/
    private Set<String> supportedVirtualisationEnvironment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getContainerFormat() {
        return containerFormat;
    }

    public void setContainerFormat(String containerFormat) {
        this.containerFormat = containerFormat;
    }

    public String getDiskFormat() {
        return diskFormat;
    }

    public void setDiskFormat(String diskFormat) {
        this.diskFormat = diskFormat;
    }

    public long getMinDisk() {
        return minDisk;
    }

    public void setMinDisk(long minDisk) {
        this.minDisk = minDisk;
    }

    public long getMinRam() {
        return minRam;
    }

    public void setMinRam(long minRam) {
        this.minRam = minRam;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSwImage() {
        return swImage;
    }

    public void setSwImage(String swImage) {
        this.swImage = swImage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Set<String> getSupportedVirtualisationEnvironment() {
        return supportedVirtualisationEnvironment;
    }

    public void setSupportedVirtualisationEnvironment(
            Set<String> supportedVirtualisationEnvironment) {
        this.supportedVirtualisationEnvironment = supportedVirtualisationEnvironment;
    }

    @Override
    public String toString() {
        return "SwImageDesc{"
                + "id='"
                + id
                + '\''
                + ", name='"
                + name
                + '\''
                + ", version='"
                + version
                + '\''
                + ", checksum='"
                + checksum
                + '\''
                + ", containerFormat='"
                + containerFormat
                + '\''
                + ", diskFormat='"
                + diskFormat
                + '\''
                + ", minDisk="
                + minDisk
                + ", minRam="
                + minRam
                + ", size="
                + size
                + ", swImage='"
                + swImage
                + '\''
                + ", operatingSystem='"
                + operatingSystem
                + '\''
                + ", supportedVirtualisationEnvironment="
                + supportedVirtualisationEnvironment
                + '}';
    }
}
