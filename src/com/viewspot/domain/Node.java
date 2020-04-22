package com.viewspot.domain;

/**
 * Node
 */
public class Node {

    /**
     * Node id
     */
    private Integer id;

    /**
     * Node x coordinate
     */
    private Double x;
    /**
     * Node y coordinate
     */
    private Double y;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
