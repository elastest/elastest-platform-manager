package io.elastest.unit.core;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import io.elastest.epm.core.NetworkManagement;
import io.elastest.epm.core.PoPManagement;
import io.elastest.epm.core.ResourceGroupManagement;
import io.elastest.epm.core.VduManagement;
import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.repository.NetworkRepository;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.epm.repository.ResourceGroupRepository;
import io.elastest.epm.repository.VduRepository;
import io.elastest.unit.MockedConfig;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = {CoreTest.class, MockedConfig.class},
  loader = AnnotationConfigContextLoader.class
)
public class ResourceGroupManagementTest {

  @Rule public final ExpectedException exception = ExpectedException.none();
  private final Logger log = LoggerFactory.getLogger(ResourceGroupManagementTest.class);

  //  @Autowired
  //  @Qualifier("mocked_dockerAdapter")
  //  DockerAdapter dockerAdapter;

  @Autowired NetworkManagement networkManagement;

  @Autowired VduManagement vduManagement;

  @Autowired PoPManagement poPManagement;

  @Autowired PoPRepository poPRepository;

  @Autowired NetworkRepository networkRepository;

  @Autowired VduRepository vduRepository;

  @Autowired ResourceGroupRepository resourceGroupRepository;

  @Autowired ResourceGroup resourceGroup;

  @Autowired PoP pop;

  @Autowired VDU vdu;

  @InjectMocks ResourceGroupManagement resourceGroupManagement;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    ReflectionTestUtils.setField(resourceGroupManagement, "poPManagement", poPManagement);
    ReflectionTestUtils.setField(resourceGroupManagement, "networkManagement", networkManagement);
    ReflectionTestUtils.setField(resourceGroupManagement, "vduManagement", vduManagement);
    when(poPRepository.findOneByName(any())).thenReturn(pop);
    log.info("Starting ResourceGroupManagementTest");
  }

  @Test
  public void testDeployResourceGroup()
      throws AdapterException, BadRequestException, AllocationException, NotFoundException {
    log.info("Test: deployResourceGroup");
    when(vduRepository.findOne(any())).thenReturn(vdu);
    resourceGroupManagement.deployResourceGroup(resourceGroup);
  }

  @Test
  public void testTerminateResourceGroup()
      throws AdapterException, BadRequestException, AllocationException, NotFoundException,
          TerminationException {
    log.info("Test: terminateResourceGroup");
    resourceGroupManagement.terminateResourceGroup(resourceGroup.getId());
  }

  @Test
  public void testgetResourceGroupById()
      throws AdapterException, BadRequestException, AllocationException, NotFoundException {
    log.info("Test: getResourceGroupById");
    ResourceGroup resourceGroupActual =
        resourceGroupManagement.getResourceGroupById(resourceGroup.getId());
    Assert.assertEquals(resourceGroup, resourceGroupActual);
  }

  @Test
  public void testgetAllResourceGroups()
      throws AdapterException, BadRequestException, AllocationException, NotFoundException {
    log.info("Test: getAllResourceGroups");
    List<ResourceGroup> resourceGroupsActual = resourceGroupManagement.getAllResourceGroups();
    Assert.assertEquals(resourceGroup, resourceGroupsActual.get(0));
  }
}
