package io.elastest.unit;

import io.elastest.epm.model.*;
import io.elastest.unit.core.CoreTest;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ContextConfiguration(classes = MockedConfig.class)
public class MockedConfig {

  private final Logger log = LoggerFactory.getLogger(CoreTest.class);

  @Bean
  VDU vdu() {
    VDU vdu = new VDU();
    vdu.setId("mocked_id");
    vdu.setName("mocked_name");
    vdu.setComputeId("mocked_compute_id");
    vdu.setNetName("mocked_network_name");
    vdu.setImageName("mocked_image_name");
    vdu.setPoPName("mocked_pop_name");
    vdu.setEvents(new ArrayList<>());
    KeyValuePair keyValuePair = new KeyValuePair("mocked_key", "mocked_value");
    vdu.addMetadataItem(keyValuePair);
    return vdu;
  }

  @Bean
  PoP pop() {
    PoP pop = new PoP();
    pop.setId("mocked_id");
    pop.setName("mocked_pop_name");
    pop.setInterfaceEndpoint("mocked_url");
    return pop;
  }

  @Bean
  Network network() {
    Network network = new Network();
    network.setId("mocked_id");
    network.setName("mocked_network_name");
    network.setCidr("mocked_cidr");
    network.setNetworkId("mocked_net_id");
    network.setPoPName("mocked_pop_name");
    return network;
  }

  @Bean
  ResourceGroup resourceGroup() {
    ResourceGroup resourceGroup = new ResourceGroup();
    resourceGroup.setId("mocked_id");
    resourceGroup.setName("mocked_resource_group_name");
    resourceGroup.setVdus(new ArrayList<>());
    resourceGroup.setNetworks(new ArrayList<>());
    resourceGroup.getNetworks().add(network());
    resourceGroup.getVdus().add(vdu());
    return resourceGroup;
  }
}
