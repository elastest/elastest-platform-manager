/*
 *
 *  * (C) Copyright 2016 NUBOMEDIA (http://www.nubomedia.eu)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package io.elastest.unit;

import io.elastest.epm.model.Network;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.unit.core.CoreTest;
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
}
