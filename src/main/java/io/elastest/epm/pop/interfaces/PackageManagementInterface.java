package io.elastest.epm.pop.interfaces;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import org.apache.commons.compress.archivers.ArchiveException;

import java.io.IOException;
import java.io.InputStream;

public interface PackageManagementInterface {

  ResourceGroup deploy(InputStream data) throws NotFoundException, IOException, ArchiveException, AdapterException, AllocationException;

  ResourceGroup deploy(InputStream data, PoP poP) throws NotFoundException, IOException;

  void terminate(String packageId) throws NotFoundException;
}
