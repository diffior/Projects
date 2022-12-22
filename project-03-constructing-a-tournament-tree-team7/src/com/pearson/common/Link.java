package com.pearson.common;

public class Link<T extends Comparable<T>, Y> extends Node<T, Y> {
    private Link next;

    public Link(T key, Y data) {
        super(key, data);
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Link{" +
                "data=" + key +
                ", next=" + next +
                '}';
    }
}
