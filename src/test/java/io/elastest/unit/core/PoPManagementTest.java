package io.elastest.unit.core;

import io.elastest.epm.core.PoPManagement;
import io.elastest.epm.model.PoP;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.unit.MockedConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
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
  classes = {CoreTest.class, MockedConfig.class},
  loader = AnnotationConfigContextLoader.class
)
public class PoPManagementTest {

  private final Logger log = LoggerFactory.getLogger(PoPManagementTest.class);
  @Autowired PoPRepository poPRepository;
  @Autowired NetworkRepository networkRepository;

  @Autowired
  @Qualifier("mocked_dockerAdapter")
  DockerAdapter dockerAdapter;

  @InjectMocks private PoPManagement poPManagement;
  @Autowired private PoP pop;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    log.info("Starting test");
  }

  @Test
  public void registerPoP() throws Exception {
    log.info("Test: registerPoP...");
    PoP newPop = poPManagement.registerPoP(pop);
    Assert.assertEquals(pop.getName(), newPop.getName());
    Assert.assertEquals(pop.getInterfaceEndpoint(), newPop.getInterfaceEndpoint());
  }

  @Test
  public void unregisterPoP() throws Exception {
    log.info("Test: unregisterPoP...");
    poPManagement.unregisterPoP(pop.getId());
  }

  @Test
  public void updatePoP() throws Exception {
    log.info("Test: updatePoP...");
    PoP updatedPoP = poPManagement.updatePoP(pop.getId(), pop);
    Assert.assertEquals(pop.getName(), updatedPoP.getName());
    Assert.assertEquals(pop.getInterfaceEndpoint(), updatedPoP.getInterfaceEndpoint());
  }

  @Test
  public void getAllPoPs() throws Exception {
    log.info("Test: getAllPoPs...");
    List<PoP> expectedPoPs = new ArrayList<>();
    expectedPoPs.add(pop);
    List<PoP> allPoPs = poPManagement.getAllPoPs();
    Assert.assertEquals(allPoPs, expectedPoPs);
  }

  @Test
  public void getPoPById() throws Exception {
    log.info("Test: getPoPById...");
    PoP expectedPoP = poPManagement.getPoPById(pop.getId());
    Assert.assertEquals(pop.getId(), expectedPoP.getId());
    Assert.assertEquals(pop.getName(), expectedPoP.getName());
    Assert.assertEquals(pop.getInterfaceEndpoint(), expectedPoP.getInterfaceEndpoint());
  }
}
