package com.pearson.list;

import com.pearson.common.Link;

public class LinkedList<T extends Comparable<T>, Y> {
    protected Link<T, Y> first;

    public LinkedList()
    {
        first = null;
    }

    public boolean isEmpty()
    {
        return (first == null);
    }

    public void insertFirst(T key, Y data) {
        Link<T, Y> newLink = new Link(key, data);
        newLink.setNext(first);
        first = newLink;
    }

    public Link<T, Y> peekFirst() {
        return first == null ? null : first;
    }

    public Link<T, Y> deleteFirst() {
        Link<T, Y>  temp = first;
        first = first.getNext();
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Link current = first;
        while (current != null)
        {
            if(current.getNext() != null) {
                builder.append(current + ", ");
            } else {
                builder.append(current);
            }
            current = current.getNext();
        }
        return builder.toString();
    }
}
