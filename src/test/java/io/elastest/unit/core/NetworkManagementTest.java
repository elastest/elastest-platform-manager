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

package io.elastest.unit.core;

import io.elastest.epm.core.NetworkManagement;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Network;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.unit.MockedConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = {CoreTest.class, MockedConfig.class},
  loader = AnnotationConfigContextLoader.class
)
public class NetworkManagementTest {

  @Rule public final ExpectedException exception = ExpectedException.none();
  private final Logger log = LoggerFactory.getLogger(PoPManagementTest.class);
  @Autowired PoPRepository poPRepository;

  @Autowired NetworkRepository networkRepository;

  @Autowired
  @Qualifier("mocked_dockerAdapter")
  DockerAdapter dockerAdapter;

  @Autowired Network network;

  @InjectMocks private NetworkManagement networkManagement;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    //    ReflectionTestUtils.setField(networkManagement, "networkRepository", networkRepository);
    log.info("Starting NetworkManagementTest");
  }

  @Test
  public void createNetwork() throws Exception {
    log.info("Test: createNetwork without existing network");
    Network expectedNetwork = getNetwork();
    expectedNetwork.setName("not_existing_network");
    Network actualNetwork = networkManagement.createNetwork(expectedNetwork);
    Assert.assertEquals(expectedNetwork, actualNetwork);
  }

  @Test
  public void createNetworkWhereNetworkAlreadyExists() throws Exception {
    log.info("Test: createNetwork with existing network");
    exception.expect(BadRequestException.class);
    networkManagement.createNetwork(network);
  }

  @Test
  public void createNetworkWithNotExistingPoP() throws Exception {
    log.info("Test: createNetwork without existing PoP");
    Network newNetwork = getNetwork();
    newNetwork.setPoPName("not_existing_pop");
    exception.expect(NotFoundException.class);
    networkManagement.createNetwork(newNetwork);
  }

  @Test
  public void deleteNetwork() throws Exception {
    log.info("Test: deleteNetwork...");
    networkManagement.deleteNetwork(network.getId());
  }

  @Test
  public void updateNetwork() throws Exception {
    log.info("Test: updateNetwork...");
    exception.expect(UnsupportedOperationException.class);
    Network updatedNetwork = networkManagement.updateNetwork(network.getId(), network);
    Assert.assertEquals(network, updatedNetwork);
  }

  @Test
  public void getAllNetworks() throws Exception {
    log.info("Test: getAllNetworks...");
    List<Network> expectedNetworks = new ArrayList<>();
    expectedNetworks.add(network);
    List<Network> allNetworks = networkManagement.getAllNetworks();
    Assert.assertEquals(expectedNetworks, allNetworks);
  }

  @Test
  public void getNetworkById() throws Exception {
    log.info("Test: getNetworkById...");
    Network actualNetwork = networkManagement.getNetworkById(network.getId());
    Assert.assertEquals(network, actualNetwork);
  }

  private Network getNetwork() {
    Network network = new Network();
    network.setId("mocked_id");
    network.setName("mocked_network");
    network.setCidr("mocked_cidr");
    network.setNetworkId("mocked_net_id");
    network.setPoPName("mocked_pop_name");
    return network;
  }
}
