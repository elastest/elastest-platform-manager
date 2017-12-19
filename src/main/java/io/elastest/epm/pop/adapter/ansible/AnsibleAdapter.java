package io.elastest.epm.pop.adapter.ansible;

import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import java.io.IOException;
import java.io.InputStream;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AnsibleAdapter implements PackageManagementInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public ResourceGroup deploy(InputStream data) throws NotFoundException, IOException {

    throw new org.apache.commons.lang.NotImplementedException(
        "Ansible adapter is under development!");
  }

  @Override
  public void terminate(String packageId) throws NotFoundException {}
}
