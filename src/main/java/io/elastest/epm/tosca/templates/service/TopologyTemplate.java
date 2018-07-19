package io.elastest.epm.tosca.templates.service;

import java.util.Map;

/**
 * Created by rvl on 27.07.17.
 */
public class TopologyTemplate {

    private Object relationship_template;
    private Map<String, NodeTemplate> nodes_template;
    private Object inputs;

    public Map<String, NodeTemplate> getNodes_template() {
        return nodes_template;
    }

    public void setNodes_template(Map<String, NodeTemplate> nodes_template) {
        this.nodes_template = nodes_template;
    }

    public Object getRelationship_template() {
        return relationship_template;
    }

    public void setRelationship_template(Object relationship_template) {
        this.relationship_template = relationship_template;
    }

    public Object getInputs() {
        return inputs;
    }

    public void setInputs(Object inputs) {
        this.inputs = inputs;
    }

    public String toString() {
        return "nodes_template: " + nodes_template;
    }
}
