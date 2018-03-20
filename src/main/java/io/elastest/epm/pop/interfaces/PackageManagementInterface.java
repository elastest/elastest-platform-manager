package io.elastest.epm.pop.interfaces;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import java.io.IOException;
import java.io.InputStream;

public interface PackageManagementInterface {

  ResourceGroup deploy(InputStream data) throws NotFoundException, IOException;

  ResourceGroup deploy(InputStream data, PoP poP) throws NotFoundException, IOException;

  void terminate(String packageId) throws NotFoundException;
}
