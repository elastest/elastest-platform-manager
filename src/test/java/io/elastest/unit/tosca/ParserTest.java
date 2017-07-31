package io.elastest.unit.tosca;

import com.google.common.io.Files;
import io.elastest.epm.tosca.parser.ToscaParser;
import io.elastest.epm.tosca.templates.service.ServiceTemplate;
import io.elastest.epm.tosca.templates.types.NodeTypeTemplate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.Charsets;
import org.junit.Test;

/** Created by rvl on 27.07.17. */
public class ParserTest {

  @Test
  public void testNodeTypes() throws FileNotFoundException {
    Map<String, NodeTypeTemplate> map = ToscaParser.loadNodeTypes("src/main/resources/types.yaml");

    System.out.print(map.toString());
  }

  @Test
  public void testServiceTemplate() throws IOException {
    String text =
        Files.toString(new File("src/main/resources/service_template.yaml"), Charsets.UTF_8);
    ServiceTemplate serviceTemplate = ToscaParser.parseServiceTemplate(text);
    System.out.print(serviceTemplate.toString());
  }

  @Test
  public void testNodeVerification() throws Exception {

    String text =
        Files.toString(new File("src/main/resources/service_template.yaml"), Charsets.UTF_8);

    ServiceTemplate serviceTemplate = ToscaParser.parseServiceTemplate(text);
    Map<String, NodeTypeTemplate> map = ToscaParser.loadNodeTypes("src/main/resources/types.yaml");

    ToscaParser.verifyNodes(serviceTemplate, map);
  }

  @Test
  public void testReadingTemplate() throws Exception {

    //String text = Files.toString(new File("src/main/resources/service_template.yaml"), Charsets.UTF_8);

    //List<Object> models = ToscaParser.templateToModel(text);

    //System.out.print(models);
  }
}
