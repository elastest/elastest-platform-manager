package io.elastest.epm.tosca.parser;

import io.elastest.epm.core.ResourceGroupManagement;
import io.elastest.epm.model.*;
import io.elastest.epm.tosca.templates.service.NodeTemplate;
import io.elastest.epm.tosca.templates.service.ServiceTemplate;
import io.elastest.epm.tosca.templates.types.NodeTypeTemplate;
import io.elastest.epm.tosca.templates.types.NodeTypesRoot;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/** Created by rvl on 27.07.17. */
@Service
public class ToscaParser {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  //  @Autowired private PoPManagement poPManagement;
  //
  //  @Autowired private VduManagement vduManagement;
  //
  //  @Autowired private NetworkManagement networkManagement;

  @Autowired private ResourceGroupManagement resourceGroupManagement;

  public String getToscaTemplate() {
    return "tosca_definitions_version: tosca_simple_yaml_1_0"
        + "\n"
        + "metadata:\n"
        + "  vendor: ElasTest\n"
        + "  name: TestTemplate\n"
        + "  version: 0.1\n"
        + "\n"
        + "topology_template:\n"
        + "\n"
        + "  nodes_template:\n"
        + "\n"
        + "    VDU1:\n"
        + "      type: elastest.nodes.VDU\n"
        + "      properties:\n"
        + "        imageName: \"ubuntu\"\n"
        + "        netName: \"testNetwork123\"\n"
        + "        poPName: \"docker-local\"\n"
        + "        metadata:\n"
        + "          - key: \"LOGSTASH_ADDRESS\"\n"
        + "            value: \"tcp://localhost:5000\"\n"
        + "\n"
        + "    docker-local:\n"
        + "      type: elastest.nodes.PoP\n"
        + "      properties:\n"
        + "        name: \"docker-local\"\n"
        + "        interfaceEndpoint: \"unix:///var/run/docker.sock\"\n"
        + "\n"
        + "    testNetwork123:\n"
        + "      type: elastest.nodes.Network\n"
        + "      properties:\n"
        + "        name: \"testNetwork123\"\n"
        + "        cidr: \"192.168.4.1/24\"\n"
        + "        poPName: \"docker-local\"\n";
  }

  public static ServiceTemplate parseServiceTemplate(String t) throws FileNotFoundException {
    Constructor constructor = new Constructor(ServiceTemplate.class);
    TypeDescription typeDescription = new TypeDescription(ServiceTemplate.class);
    constructor.addTypeDescription(typeDescription);
    Yaml yaml = new Yaml(constructor);
    return yaml.loadAs(t, ServiceTemplate.class);
    //    YAMLFactory yamlFactory = new YAMLFactory();
    //    ObjectMapper mapper = new YAMLMapper(yamlFactory);
    //    ServiceTemplate serviceTemplate = null;
    //    try {
    //      serviceTemplate = mapper.readValue(t, ServiceTemplate.class);
    //    } catch (IOException e) {
    //      e.printStackTrace();
    //    }
    //    return serviceTemplate;
  }

  public static Map<String, NodeTypeTemplate> loadNodeTypes(String fileLocation)
      throws FileNotFoundException {
    InputStream nodeTypesFile = new FileInputStream(new File(fileLocation));

    Constructor constructor = new Constructor(NodeTypesRoot.class);
    TypeDescription typeDescription = new TypeDescription(NodeTypesRoot.class);
    constructor.addTypeDescription(typeDescription);

    Yaml yaml = new Yaml(constructor);

    NodeTypesRoot nodeTypes = yaml.loadAs(nodeTypesFile, NodeTypesRoot.class);

    return nodeTypes.getNode_types();
  }

  private static void verifyNode(NodeTemplate nodeTemplate, Map<String, NodeTypeTemplate> nodeTypes)
      throws Exception {

    String nodeTypeKey = nodeTemplate.getType();

    if (nodeTypes.containsKey(nodeTypeKey)) {

      NodeTypeTemplate nodeType = nodeTypes.get(nodeTypeKey);
      Map<String, Map<String, String>> nodeTypeProperties = nodeType.getProperties();

      for (String property : nodeTemplate.getProperties().keySet()) {

        if (nodeTypeProperties.keySet().contains(property)) {

          // Check if property is in the right format
          Map<String, String> propertyDefinition = nodeTypeProperties.get(property);
          System.out.print(propertyDefinition);

          String propertyType = propertyDefinition.get("type");
          Object value = nodeTemplate.getProperties().get(property);

          switch (propertyType) {
            case "string":
              if (!(value instanceof String))
                throw new Exception("Property: " + property + " is of the wrong type");
              break;
            case "boolean":
              if (!(value instanceof Boolean))
                throw new Exception("Property: " + property + " is of the wrong type");
              break;
            case "float":
              if (!(value instanceof Float))
                throw new Exception("Property: " + property + " is of the wrong type");
              break;
            case "int":
              if (!(value instanceof Integer))
                throw new Exception("Property: " + property + " is of the wrong type");
              break;
            case "list":
              if (!(value instanceof List))
                throw new Exception("Property: " + property + " is of the wrong type");
              break;
            case "{}":
              if (!(value instanceof Map))
                throw new Exception("Property: " + property + " is of the wrong type");
              break;
            default:
              break;
          }

        } else
          throw new NotFoundException(
              "Property: " + property + " is not part of the Node type: " + nodeTemplate.getType());
      }
    } else throw new NotFoundException("Specified node type was not found!");
  }

  public static void verifyNodes(
      ServiceTemplate serviceTemplate, Map<String, NodeTypeTemplate> nodes) throws Exception {

    for (String key : serviceTemplate.getTopology_template().getNodes_template().keySet()) {
      verifyNode(serviceTemplate.getTopology_template().getNodes_template().get(key), nodes);
    }
  }

  public ResourceGroup templateToModel(String t) throws Exception {

    ServiceTemplate serviceTemplate = parseServiceTemplate(t);

    String resourceGroupName = serviceTemplate.getMetadata().getName();

    Map<String, NodeTypeTemplate> nodeTypes = loadNodeTypes("src/main/resources/types.yaml");

    ToscaParser.verifyNodes(serviceTemplate, nodeTypes);

    Map<String, NodeTemplate> nodes = serviceTemplate.getTopology_template().getNodes_template();

    List<VDU> vduList = new ArrayList<>();
    List<PoP> poPList = new ArrayList<>();
    List<Network> networksList = new ArrayList<>();

    for (String key : nodes.keySet()) {
      NodeTemplate node = nodes.get(key);
      // parse VDUs
      if (node.getType().equals("elastest.nodes.VDU")) {

        VDU vdu = new VDU();
        vdu.setName(key);
        if (node.getProperties().containsKey("imageName"))
          vdu.setImageName((String) node.getProperties().get("imageName"));
        if (node.getProperties().containsKey("netName"))
          vdu.setNetName((String) node.getProperties().get("netName"));
        if (node.getProperties().containsKey("poPName"))
          vdu.setPoPName((String) node.getProperties().get("poPName"));

        if (node.getProperties().containsKey("metadata")) {
          List<Map<String, String>> m =
              (List<Map<String, String>>) node.getProperties().get("metadata");

          List<KeyValuePair> metadata = new ArrayList<>();
          for (Map<String, String> keyValuePair : m) {
            metadata.add(new KeyValuePair(keyValuePair.get("key"), keyValuePair.get("value")));
          }
          vdu.setMetadata(metadata);
        }

        vduList.add(vdu);
      }

      // parse PoPs
      if (node.getType().equals("elastest.nodes.PoP")) {
        PoP poP = new PoP();

        if (node.getProperties().containsKey("name"))
          poP.setName((String) node.getProperties().get("name"));
        else poP.setName(key);

        if (node.getProperties().containsKey("interfaceEndpoint"))
          poP.setInterfaceEndpoint((String) node.getProperties().get("interfaceEndpoint"));
        poPList.add(poP);
      }

      // parse Networks
      if (node.getType().equals("elastest.nodes.Network")) {

        Network network = new Network();

        if (node.getProperties().containsKey("name"))
          network.setName((String) node.getProperties().get("name"));
        else network.setName(key);

        if (node.getProperties().containsKey("cidr"))
          network.setCidr((String) node.getProperties().get("cidr"));

        if (node.getProperties().containsKey("poPName"))
          network.setPoPName((String) node.getProperties().get("poPName"));
        networksList.add(network);
      }
    }

    //    List<PoP> updatePoPList = new ArrayList<>();
    //    List<Network> updateNetworkList = new ArrayList<>();
    //    List<VDU> updateVDUList = new ArrayList<>();

    //    if (poPManagement == null) throw new NotFoundException("Pop management is null");
    //
    //    for (PoP poP : poPList) updatePoPList.add(poPManagement.registerPoP(poP));
    //
    //    for (Network network : networksList)
    //      updateNetworkList.add(networkManagement.createNetwork(network));
    //
    //    for (VDU vdu : vduList) updateVDUList.add(vduManagement.deployVdu(vdu));
    //
    //    List<Object> models = new ArrayList<>();
    //    models.add(updateVDUList);
    //    models.add(updatePoPList);
    //     models.add(updateNetworkList);

    ResourceGroup resourceGroup = new ResourceGroup();
    resourceGroup.setName(resourceGroupName);
    resourceGroup.setPops(poPList);
    resourceGroup.setNetworks(networksList);
    resourceGroup.setVdus(vduList);
    resourceGroup = resourceGroupManagement.deployResourceGroup(resourceGroup);
    return resourceGroup;
  }
}
