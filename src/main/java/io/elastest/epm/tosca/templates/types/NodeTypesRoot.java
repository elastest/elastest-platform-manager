package io.elastest.epm.tosca.templates.types;

import java.util.Map;

/**
 * Created by rvl on 27.07.17.
 */
public class NodeTypesRoot {

    private Map<String, NodeTypeTemplate> node_types;

    public Map<String, NodeTypeTemplate> getNode_types() {
        return node_types;
    }

    public void setNode_types(Map<String, NodeTypeTemplate> node_types) {
        this.node_types = node_types;
    }
}
