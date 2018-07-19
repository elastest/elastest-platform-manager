package io.elastest.epm.pop.interfaces;

import io.elastest.epm.model.PoP;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveException;

public interface AdapterBrokerInterface {

    RuntimeManagmentInterface getAdapter(PoP pop);

    PackageManagementInterface getPackageManagementPerPop(PoP pop);

    PackageManagementInterface getAdapter(InputStream p) throws IOException, ArchiveException;
}
