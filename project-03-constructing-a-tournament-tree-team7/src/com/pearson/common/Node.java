package com.pearson.common;

public class Node<T extends Comparable<T>, Y> {
    protected T key;
    protected Y data;

    public Node(T key, Y data) {
        this.key = key;
        this.data = data;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Y getData() {
        return data;
    }

    public void setData(Y data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", data=" + data +
                '}';
    }
}
