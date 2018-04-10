package io.elastest.epm.pop.adapter.broker;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.pop.adapter.ansible.AnsibleAdapter;
import io.elastest.epm.pop.adapter.compose.DockerComposeAdapter;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.interfaces.AdapterBrokerInterface;
import io.elastest.epm.pop.interfaces.PackageManagementInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveException;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope
public class AdapterBroker implements AdapterBrokerInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired DockerComposeAdapter dockerComposeAdapter;
  @Autowired AnsibleAdapter ansibleAdapter;
  @Autowired DockerAdapter dockerAdapter;

  @Override
  public RuntimeManagmentInterface getAdapter(PoP pop) {
    boolean typeSpecified = false;
    String type = "";
    for (KeyValuePair kvp : pop.getInterfaceInfo()) {
      if (kvp.getKey().equals("type")) {
        type = kvp.getValue();
        typeSpecified = true;
        break;
      }
    }

    if (typeSpecified) {
      switch (type) {
        case "docker-compose":
          return dockerComposeAdapter;

        case "ansible":
          return ansibleAdapter;
        case "docker":
          return new DockerAdapter();

        default:
          return new DockerAdapter();
      }
    } else return new DockerAdapter();
  }

  @Override
  public PackageManagementInterface getPackageManagementPerPop(PoP pop) {
    boolean typeSpecified = false;
    String type = "";
    for (KeyValuePair kvp : pop.getInterfaceInfo()) {
      if (kvp.getKey().equals("type")) {
        type = kvp.getValue();
        typeSpecified = true;
        break;
      }
    }

    if (typeSpecified) {
      return findByType(type);
    } else return dockerComposeAdapter;
  }

  @Override
  public PackageManagementInterface getAdapter(InputStream p) throws IOException, ArchiveException {

    Map<String, Object> values = Utils.extractMetadata(p);
    String type = "";
    if (values.containsKey("type")) {
      type = String.valueOf(values.get("type"));
    }
    if (!type.equals("")) {
      return findByType(type);
    } else return null;
  }

  private PackageManagementInterface findByType(String type) {
    switch (type) {
      case "docker-compose":
        return dockerComposeAdapter;
      case "ansible":
        return ansibleAdapter;
      case "docker":
        return dockerAdapter;
      default:
        return dockerComposeAdapter;
    }
  }
}
