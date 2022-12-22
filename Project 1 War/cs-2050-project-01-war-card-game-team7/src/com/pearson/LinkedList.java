package com.pearson;

public class LinkedList<T extends Comparable<T>> {
    private Link<T> first;

    public LinkedList()
    {
        first = null;
    }

    public boolean isEmpty()
    {
        return (first == null);
    }

    public void insertFirst(T data)
    {
        Link newLink = new Link(data);
        newLink.setNext(first);
        first = newLink;
    }

    public T deleteFirst()
    {
        Link<T> temp = first;
        first = first.getNext();
        return temp.getData();
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
