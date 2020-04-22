package com.viewspot.domain;

import java.util.List;

/**
 * Mesh data
 */
public class MeshData {

    /**
     * Mesh elements
     */
    private List<Element> elements;
    /**
     * Mesh values
     */
    private List<Value> values;
    /**
     * Mesh nodes
     */
    private List<Node> nodes;

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Value> getValues() {
        return values;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "MeshData{" +
                "elements=" + elements +
                ", values=" + values +
                ", nodes=" + nodes +
                '}';
    }
}
