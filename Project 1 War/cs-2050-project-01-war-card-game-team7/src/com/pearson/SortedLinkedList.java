package com.pearson;

public class SortedLinkedList<T extends Comparable<T>> {
    private Link<T> first;

    public SortedLinkedList() {
        first = null;
    }

    public SortedLinkedList(T[] data) {
        first = null;
        for (T current : data) {
            insert(current);
        }
    }

    public void insert(T data) {
        Link<T> previous = null;
        Link<T> current = first;
        Link<T> toInsert = new Link<>(data);

        while (current != null &&
                toInsert.getData().compareTo(current.getData()) > 0) {
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

    public T remove() {
        Link<T> temp = first;
        first = first.getNext();
        return temp.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }
}
