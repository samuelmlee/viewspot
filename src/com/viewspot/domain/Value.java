package com.viewspot.domain;

/**
 * Value of an element
 */
public class Value {

    /**
     * Element id
     */
    private Integer element_id;
    /**
     * Value of element
     */
    private Double value;

    public Integer getElement_id() {
        return element_id;
    }

    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "element_id=" + element_id +
                ", value=" + value +
                '}';
    }
}
