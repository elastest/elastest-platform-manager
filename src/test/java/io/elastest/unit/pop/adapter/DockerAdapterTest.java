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

package io.elastest.unit.pop.adapter;

import com.github.dockerjava.api.DockerClient;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.messages.compute.AllocateComputeRequest;
import io.elastest.unit.MockedConfig;
import io.elastest.unit.core.PoPManagementTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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
  classes = {AdapterTest.class, MockedConfig.class},
  loader = AnnotationConfigContextLoader.class
)
public class DockerAdapterTest {

  private final Logger log = LoggerFactory.getLogger(PoPManagementTest.class);

  @Autowired
  @Qualifier("mocked_dockerClient")
  DockerClient dockerClient;

  @InjectMocks
  @Qualifier("test_dockerAdapter")
  DockerAdapter dockerAdapter;

  //  @Autowired ListImagesCmd listImagesCmd;

  @Autowired VDU vdu;

  @Autowired PoP pop;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    pop.setInterfaceEndpoint("tcp://" + pop.getInterfaceEndpoint());
    log.info("Starting DockerAdapterTest");
  }

  @Test
  @Ignore
  public void allocateVirtualisedComputeResource() throws Exception {
    AllocateComputeRequest allocateComputeRequest = new AllocateComputeRequest();
    allocateComputeRequest.setComputeName(vdu.getName());
    allocateComputeRequest.setVcImageId(vdu.getImageName());
    allocateComputeRequest.setMetaData(vdu.getMetadata());
    dockerAdapter.allocateVirtualisedComputeResource(allocateComputeRequest, pop);
  }

  @Test
  public void queryVirtualisedComputeResource() throws Exception {}

  @Test
  public void updateVirtualisedComputeResource() throws Exception {}

  @Test
  public void terminateVirtualisedComputeResource() throws Exception {}

  @Test
  public void allocateVirtualisedNetworkResource() throws Exception {}

  @Test
  public void queryVirtualisedNetworkResource() throws Exception {}

  @Test
  public void updateVirtualisedNetworkResource() throws Exception {}

  @Test
  public void terminateVirtualisedNetworkResource() throws Exception {}
}
