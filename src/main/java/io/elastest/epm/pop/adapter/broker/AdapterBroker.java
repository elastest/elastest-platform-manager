package io.elastest.epm.pop.adapter.broker;

import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.compose.DockerComposeAdapter;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.interfaces.AdapterBrokerInterface;
import io.elastest.epm.pop.interfaces.RuntimeManagmentInterface;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope
public class AdapterBroker implements AdapterBrokerInterface {

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
          return new DockerComposeAdapter();

        case "docker":
          return new DockerAdapter();

        default:
          return new DockerAdapter();
      }
    } else return new DockerAdapter();
  }
}
