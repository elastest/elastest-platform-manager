package io.elastest.unit.core;

import io.elastest.epm.core.PlacementManager;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.PoP;
import io.elastest.epm.repository.PoPRepository;
import io.elastest.unit.MockedConfig;
import org.junit.*;
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

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {CoreTest.class, MockedConfig.class},
    loader = AnnotationConfigContextLoader.class
)
public class PlacementManagerTest {

  private final Logger log = LoggerFactory.getLogger(AdapterRepositoryTest.class);

  @Autowired PoPRepository poPRepository;

  @Autowired PoP pop;

  @InjectMocks private PlacementManager placementManager;

  @Rule public final ExpectedException exception = ExpectedException.none();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    log.info("Starting test");
  }

  @Test
  public void choosePoPByTypeTest() throws NotFoundException {
    log.info("Test: ChoosePoPByType...");
    String actualType = null;
    for (KeyValuePair kvp : pop.getInterfaceInfo())
      if (kvp.getKey() == "type") actualType = kvp.getValue();
    PoP chosenPoP = placementManager.choosePoPByType("mocked_type");
    String expectedType = null;
    for (KeyValuePair kvp : chosenPoP.getInterfaceInfo())
      if (kvp.getKey() == "type") expectedType = kvp.getValue();
    Assert.assertEquals(expectedType, actualType);
  }

  @Test
  public void choosePoPByNonExistingTypeTest() throws NotFoundException {
    log.info("Test: ChoosePoPByNonExistingType...");
    exception.expect(NotFoundException.class);
    PoP chosenPoP = placementManager.choosePoPByType("nonExisting_type");
    Assert.assertEquals(chosenPoP, pop);
  }
}
