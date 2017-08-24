package io.elastest.unit.core;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import io.elastest.epm.core.VduManagement;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.pop.messages.compute.AllocateComputeRequest;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.VduRepository;
import io.elastest.unit.MockedConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
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
  public void deployVduNotFoundPop() throws Exception {
    log.info("Test: deployVduWithNotFoundPoPException");
    when(poPRepository.findOneByName(any())).thenReturn(null);
    exception.expect(NotFoundException.class);
    VDU newVdu = vduManagement.deployVdu(vdu);
    Assert.assertEquals(vdu, newVdu);
  }

  @Test
  public void deployVduNotFoundNetwork() throws Exception {
    log.info("Test: deployVduWithNotFoundNetworkException");
    when(networkRepository.findOneByName(any())).thenReturn(null);
    exception.expect(NotFoundException.class);
    VDU newVdu = vduManagement.deployVdu(vdu);
    Assert.assertEquals(vdu, newVdu);
  }

  @Test
  @Ignore
  public void deployVduAllocationException() throws Exception {
    log.info("Test: deployVduWithAllocationException");
    when(dockerAdapter.allocateVirtualisedComputeResource(
            any(AllocateComputeRequest.class), any(PoP.class)))
        .thenThrow(new AdapterException("mocked_exception"));
    exception.expect(AllocationException.class);
    VDU newVdu = vduManagement.deployVdu(vdu);
    Assert.assertEquals(vdu, newVdu);
  }

  @Test
  public void terminateVdu() throws Exception {
    log.info("Test: terminateVdu");
    vduManagement.terminateVdu(vdu.getId());
  }

  @Test
  public void terminateVduNotFound() throws Exception {
    log.info("Test: terminateVduNotFound");
    when(vduRepository.findOne("not_existing_vdu")).thenReturn(null);
    exception.expect(NotFoundException.class);
    vduManagement.terminateVdu("not_existing_vdu");
  }

  @Test
  @Ignore
  public void terminateVduException() throws Exception {
    log.info("Test: terminateVduException");
    when(dockerAdapter.terminateVirtualisedComputeResource(any(), any()))
        .thenThrow(AdapterException.class);
    exception.expect(TerminationException.class);
    vduManagement.terminateVdu(vdu.getId());
  }

  @Test
  public void getAllVdus() throws Exception {
    log.info("Test: getAllVdus");
    List<VDU> allExpectedVdus = new ArrayList<>();
    allExpectedVdus.add(vdu);
    List<VDU> allActualVdus = vduManagement.getAllVdus();
    Assert.assertEquals(allExpectedVdus.get(0), allActualVdus.get(0));
  }
}
