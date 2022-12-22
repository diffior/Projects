package com.pearson.list;

import com.pearson.common.Link;

public class DoubleEndedLinkedList<T extends Comparable<T>, Y> extends LinkedList<T, Y> {
    private Link<T, Y> last;

    @Override
    public void insertFirst(T key, Y data) {
        super.insertFirst(key, data);
        if (last == null) {
            last = first;
        }
    }

    @Override
    public Link<T, Y> deleteFirst() {
        Link<T, Y> retVal = super.deleteFirst();
        if (first == null) {
            last = null;
        }
        return retVal;
    }

    public void insertLast(T key, Y data) {
        if (last == null) {
            insertFirst(key, data);
        } else {
            Link<T, Y> newLink = new Link(key, data);
            last.setNext(newLink);
            last = newLink;
        }
    }

    public Link<T, Y> peekLast() {
        return last == null ? null : last;
    }
}
