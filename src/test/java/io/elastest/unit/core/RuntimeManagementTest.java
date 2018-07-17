package io.elastest.unit.core;

import io.elastest.epm.core.*;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.pop.adapter.GenericAdapter;
import io.elastest.epm.pop.adapter.docker.DockerAdapter;
import io.elastest.epm.pop.adapter.exception.AdapterException;
import io.elastest.epm.repository.VduRepository;
import io.elastest.unit.MockedConfig;
import java.io.IOException;
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
import org.springframework.mock.web.MockMultipartFile;
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
public class RuntimeManagementTest {

  @Rule public final ExpectedException exception = ExpectedException.none();
  private final Logger log = LoggerFactory.getLogger(RuntimeManagementTest.class);

  //  @Autowired
  //  @Qualifier("mocked_dockerAdapter")
  //  DockerAdapter dockerAdapter;

  @Autowired NetworkManagement networkManagement;

  @Autowired VduManagement vduManagement;

  @Autowired PoPManagement poPManagement;

  @Autowired VduRepository vduRepository;

  @Autowired
  @Qualifier("mocked_genericAdapter")
  GenericAdapter genericAdapter;

  @InjectMocks RuntimeManagement runtimeManagement;

  @Autowired VDU vdu;
  @Autowired private PoP pop;

  @Before
  public void init() throws Exception {
    MockitoAnnotations.initMocks(this);
    ReflectionTestUtils.setField(runtimeManagement, "poPManagement", poPManagement);
    ReflectionTestUtils.setField(runtimeManagement, "vduManagement", vduManagement);
    log.info("Starting RuntimeManagementTest");
  }

  @Test
  public void testDownloadFileFromInstance() throws AdapterException, NotFoundException {
    log.info("Test: DownloadFileFromInstance");
    runtimeManagement.downloadFileFromInstance(vdu.getId(), "mocked_file_path");
  }

  @Test
  public void testExecuteOnInstance() throws AdapterException, NotFoundException {
    log.info("Test: executreOnInstance");
    runtimeManagement.executeOnInstance(vdu.getId(), "mocked_command", true);
  }

  @Test
  public void testStartInstance() throws AdapterException, NotFoundException {
    log.info("Test: startInstance");
    runtimeManagement.startInstance(vdu.getId());
  }

  @Test
  public void testStopInstance() throws AdapterException, NotFoundException {
    log.info("Test: stopInstance");
    runtimeManagement.stopInstance(vdu.getId());
  }

  @Test
  public void testUploadFileToInstanceByFile()
      throws AdapterException, BadRequestException, NotFoundException, IOException {
    log.info("Test: uploadFileToInstanceByFile");
    runtimeManagement.uploadFileToInstanceByFile(
        vdu.getId(), "mocked_path", new MockMultipartFile("mocked_name", new byte[] {}));
  }

  @Test
  public void testUploadFileToInstanceWithNullFile()
      throws AdapterException, BadRequestException, NotFoundException, IOException {
    log.info("Test: uploadFileToInstanceByFile BadRequest");
    exception.expect(BadRequestException.class);
    runtimeManagement.uploadFileToInstanceByFile(vdu.getId(), "mocked_path", null);
  }

  @Test
  public void testUploadFileToInstanceByPath()
      throws AdapterException, BadRequestException, NotFoundException, IOException {
    log.info("Test: uploadFileToInstance");
    runtimeManagement.uploadFileToInstanceByPath(vdu.getId(), "mocked_path", "mocked_path");
  }

  @Test
  public void testUploadFileToInstanceWithNullPath()
      throws AdapterException, BadRequestException, NotFoundException, IOException {
    log.info("Test: uploadFileToInstaceByPath BadRequest");
    exception.expect(BadRequestException.class);
    runtimeManagement.uploadFileToInstanceByPath(vdu.getId(), "mocked_path", null);
  }
}
