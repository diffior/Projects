package com.pearson.list;

import com.pearson.common.Link;

public class SortedLinkedList<T extends Comparable<T>, Y> {
    private Link<T, Y> first;

    public SortedLinkedList() {
        first = null;
    }

    public void insert(T key, Y data) {
        Link<T, Y> previous = null;
        Link<T, Y> current = first;
        Link<T, Y> toInsert = new Link<>(key, data);

        while (current != null &&
                toInsert.getKey().compareTo(current.getKey()) > 0) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            first = toInsert;
        } else {
            previous.setNext(toInsert);
        }
        toInsert.setNext(current);
    }

    public Link<T, Y> remove() {
        Link<T, Y> temp = first;
        first = first.getNext();
        return temp;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
