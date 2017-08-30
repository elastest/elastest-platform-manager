package io.elastest.unit.tosca;

import com.google.common.io.Files;
import io.elastest.epm.core.ResourceGroupManagement;
import io.elastest.epm.model.ResourceGroup;
import io.elastest.epm.tosca.parser.ToscaParser;
import io.elastest.epm.tosca.templates.service.ServiceTemplate;
import io.elastest.epm.tosca.templates.types.NodeTypeTemplate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.Charsets;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/** Created by rvl on 27.07.17. */
@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

  @InjectMocks
  private ToscaParser mockToscaParser = new ToscaParser();

  @Mock
  private ResourceGroupManagement resourceGroupManagement;

  @Test
  public void testNodeTypes() throws FileNotFoundException {
    Map<String, NodeTypeTemplate> map = ToscaParser.loadNodeTypes("src/main/resources/types.yaml");

    System.out.print(map.toString());
  }

  @Test
  public void testServiceTemplate() throws IOException {
    ToscaParser toscaParser = new ToscaParser();
    String text = toscaParser.getToscaTemplate();
    ServiceTemplate serviceTemplate = ToscaParser.parseServiceTemplate(text);
    System.out.print(serviceTemplate.toString());
  }

  @Test
  public void testNodeVerification() throws Exception {

    ToscaParser toscaParser = new ToscaParser();
    String text = toscaParser.getToscaTemplate();

    ServiceTemplate serviceTemplate = ToscaParser.parseServiceTemplate(text);
    Map<String, NodeTypeTemplate> map = ToscaParser.loadNodeTypes("src/main/resources/types.yaml");

    ToscaParser.verifyNodes(serviceTemplate, map);
  }

  @Test
  public void testReadingTemplate() throws Exception {

    ToscaParser toscaParser = new ToscaParser();

    String text = mockToscaParser.getToscaTemplate();
    ResourceGroup resourceGroup = mockToscaParser.templateToModel(text);

    System.out.print(resourceGroup);
  }
}
