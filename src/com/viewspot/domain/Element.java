package com.viewspot.domain;

import java.util.List;
import java.util.Optional;

/**
 * Mesh element
 */
public class Element implements Comparable<Element> {

    /**
     * element id
     */
    private Integer id;
    /**
     * element nodes
     */
    private List<Integer> nodes;
    /**
     * element value updated from mesh values
     */
    private Double value;

    public Element() {
    }

    public Element(int id, List<Integer> nodes, Double value) {
        this.id = id;
        this.nodes = nodes;
        this.value = value;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getNodes() {
        return nodes;
    }

    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Updates element value from mesh values
     *
     * @param  values  used to update element value
     */
    public void updateValueFromData(List<Value> values) {
        Optional<Value> elementValue = values.stream()
                .filter(v -> v.getElement_id().equals(id))
                .findFirst();
        elementValue.ifPresent(value -> this.setValue(value.getValue()));
    }

    @Override
    public String toString() {
        return "{" +
                "element_id: " + id +
                ", value: " + value +
                '}';
    }

    @Override
    public int compareTo(Element other) {
        return this.value.compareTo(other.getValue());

    }
}
