package io.elastest.epm.pop.adapter.broker;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
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
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

@Service
@Scope
public class AdapterBroker implements AdapterBrokerInterface {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired DockerComposeAdapter dockerComposeAdapter;
  @Autowired AnsibleAdapter ansibleAdapter;

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
      switch (type) {
        case "docker-compose":
          return dockerComposeAdapter;

        case "ansible":
          return ansibleAdapter;

        default:
          return dockerComposeAdapter;
      }
    } else return dockerComposeAdapter;
  }

  @Override
  public PackageManagementInterface getAdapter(InputStream p) throws IOException, ArchiveException {

    ArchiveInputStream t = new ArchiveStreamFactory().createArchiveInputStream("tar", p);

    TarArchiveEntry entry = (TarArchiveEntry) t.getNextEntry();
    boolean found = false;
    String type = "docker-compose";

    log.info(entry.getName());
    log.info(String.valueOf(entry.getSize()));
    while (entry != null) {

      if (entry.getName().toLowerCase().contains("metadata.yaml")) {
        found = true;

        byte[] content = new byte[(int) entry.getSize()];
        t.read(content, 0, content.length);
        Yaml yaml = new Yaml();

        Map<String, Object> values = yaml.load(new String(content));
        if (values.containsKey("type")) {
          type = String.valueOf(values.get("type"));
        }
        break;
      }
      entry = (TarArchiveEntry) t.getNextEntry();
    }

    t.close();

    if (found) {
      switch (type) {
        case "docker-compose":
          return dockerComposeAdapter;
        case "ansible":
          return ansibleAdapter;
        default:
          return dockerComposeAdapter;
      }
    } else return null;
  }
}
