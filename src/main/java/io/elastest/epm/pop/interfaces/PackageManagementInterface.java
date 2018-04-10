package io.elastest.epm.pop.interfaces;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveException;

public interface PackageManagementInterface {

  ResourceGroup deploy(InputStream data)
      throws NotFoundException, IOException, ArchiveException, AdapterException,
          AllocationException, BadRequestException;

  ResourceGroup deploy(InputStream data, PoP poP)
      throws NotFoundException, IOException, AdapterException, BadRequestException,
          AllocationException, ArchiveException;

  void terminate(String packageId) throws NotFoundException;
}
