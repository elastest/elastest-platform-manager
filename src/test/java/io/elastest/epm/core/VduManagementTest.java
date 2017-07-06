package io.elastest.epm.core;

import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.DockerAdapter;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.VduRepository;
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
@ContextConfiguration(classes = CoreTest.class, loader = AnnotationConfigContextLoader.class)
public class VduManagementTest {

  @Rule public final ExpectedException exception = ExpectedException.none();
  private final Logger log = LoggerFactory.getLogger(PoPManagementTest.class);

  @Autowired
  @Qualifier("mocked_dockerAdapter")
  DockerAdapter dockerAdapter;

  @InjectMocks private VduManagement vduManagement;
  @Autowired private VDU vdu;

  @Autowired PoPRepository poPRepository;

  @Autowired NetworkRepository networkRepository;

  @Autowired VduRepository vduRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    log.info("Starting VduManagementTest");
  }

  @Test
  public void deployVdu() throws Exception {
    log.info("Test: deployVdu");
    VDU newVdu = vduManagement.deployVdu(vdu);
    Assert.assertEquals(vdu, newVdu);
  }

  @Test
  public void terminateVdu() throws Exception {
    log.info("Test: terminateVdu");
    vduManagement.terminateVdu(vdu.getId());
  }

  @Test
  public void getAllVdus() throws Exception {
    log.info("Test: getAllVdus");
    vduManagement.getAllVdus();
  }
}
