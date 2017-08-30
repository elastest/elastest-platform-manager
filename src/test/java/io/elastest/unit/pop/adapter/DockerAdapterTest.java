package io.elastest.unit.pop.adapter;

import com.github.dockerjava.api.DockerClient;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.messages.compute.AllocateComputeRequest;
import io.elastest.epm.pop.messages.compute.QueryComputeRequest;
import io.elastest.epm.pop.model.common.Filter;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
  @Ignore
  public void queryVirtualisedComputeResource() throws Exception {
    QueryComputeRequest queryComputeRequest = new QueryComputeRequest();
    Filter filter = new Filter();
    Predicate predicate = new Predicate() {
      @Override
      public boolean test(Object o) {
        return false;
      }
    };
    List<Predicate> predicateList = new ArrayList<>();
    predicateList.add(predicate);
    filter.setPredicates(predicateList);
    queryComputeRequest.setQueryComputeFilter(filter);
    dockerAdapter.queryVirtualisedComputeResource(queryComputeRequest, pop);
  }

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
