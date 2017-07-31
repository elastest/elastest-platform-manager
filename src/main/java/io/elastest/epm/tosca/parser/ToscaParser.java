package io.elastest.epm.tosca.parser;

import io.elastest.epm.core.NetworkManagement;
import io.elastest.epm.core.PoPManagement;
import io.elastest.epm.core.VduManagement;
import io.elastest.epm.model.KeyValuePair;
import io.elastest.epm.model.Network;
import io.elastest.epm.model.PoP;
import io.elastest.epm.model.VDU;
import io.elastest.epm.tosca.templates.service.NodeTemplate;
import io.elastest.epm.tosca.templates.service.ServiceTemplate;
import io.elastest.epm.tosca.templates.types.NodeTypeTemplate;
import io.elastest.epm.tosca.templates.types.NodeTypesRoot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/** Created by rvl on 27.07.17. */
@Service
public class ToscaParser {

  @Autowired private PoPManagement poPManagement;

  @Autowired private VduManagement vduManagement;

  @Autowired private NetworkManagement networkManagement;

  public static ServiceTemplate parseServiceTemplate(String t) throws FileNotFoundException {

    Constructor constructor = new Constructor(ServiceTemplate.class);
    TypeDescription typeDescription = new TypeDescription(ServiceTemplate.class);
    constructor.addTypeDescription(typeDescription);
    Yaml yaml = new Yaml(constructor);

    return yaml.loadAs(t, ServiceTemplate.class);
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
      ToscaParser.verifyNode(
          serviceTemplate.getTopology_template().getNodes_template().get(key), nodes);
    }
  }

  public List<Object> templateToModel(String t) throws Exception {

    ServiceTemplate serviceTemplate = parseServiceTemplate(t);
    Map<String, NodeTypeTemplate> nodeTypes = loadNodeTypes("src/main/resources/types.yaml");

    verifyNodes(serviceTemplate, nodeTypes);

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

    List<PoP> updatePoPList = new ArrayList<>();
    List<Network> updateNetworkList = new ArrayList<>();
    List<VDU> updateVDUList = new ArrayList<>();

    if (poPManagement == null) throw new NotFoundException("Pop management is null");

    for (PoP poP : poPList) updatePoPList.add(poPManagement.registerPoP(poP));

    for (Network network : networksList)
      updateNetworkList.add(networkManagement.createNetwork(network));

    for (VDU vdu : vduList) updateVDUList.add(vduManagement.deployVdu(vdu));

    List<Object> models = new ArrayList<>();
    models.add(updateVDUList);
    models.add(updatePoPList);
    models.add(updateNetworkList);

    return models;
  }
}
