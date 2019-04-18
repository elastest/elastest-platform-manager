package io.elastest.unit.core;

import io.elastest.epm.api.utils.AdapterLauncher;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.Adapter;
import io.elastest.epm.pop.adapter.Utils;
import io.elastest.epm.repository.AdapterRepositoryImpl;
import io.elastest.unit.MockedConfig;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
public class AdapterRepositoryTest {

  private final Logger log = LoggerFactory.getLogger(AdapterRepositoryTest.class);

  @Autowired AdapterRepositoryImpl adapterRepositoryImpl;

  @Autowired
  Utils utils;

  @Autowired AdapterLauncher adapterLauncher;

  @Autowired Adapter adapter;

  @Rule public final ExpectedException exception = ExpectedException.none();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    log.info("Starting test");
  }

  @Test
  public void findAdapterForTypeAndIpTest() throws Exception {
    log.info("Test: findAdapterForTypeAndIp() ...");

    Adapter adapterExpected =
        adapterRepositoryImpl.findAdapterForTypeAndIp(adapter.getType(), adapter.getEndpoint());
    Assert.assertEquals(adapterExpected.getType(), adapter.getType());
    Assert.assertEquals(adapterExpected.getEndpoint(), adapter.getEndpoint());
  }

  @Test
  public void findAdapterForTypeAndIpNotFoundTest() throws Exception {
    log.info("Test: findAdapterForTypeAndIp() NotFoundException...");
    exception.expect(NotFoundException.class);
    adapterRepositoryImpl.findAdapterForTypeAndIp("not_existing_type", adapter.getEndpoint());
  }
}
