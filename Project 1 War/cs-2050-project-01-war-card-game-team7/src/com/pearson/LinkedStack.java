package com.pearson;

public class LinkedStack<T extends Comparable<T>> {
    private LinkedList<T> linkedList;

    public LinkedStack()
    {
        linkedList = new LinkedList<T>();
    }

    public void push(T data)
    {
        linkedList.insertFirst(data);
    }

    public T pop()
    {
        return linkedList.deleteFirst();
    }

    public boolean isEmpty()
    {
        return (linkedList.isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack (top-->bottom): ");
        builder.append(linkedList.toString());
        return builder.toString();
    }
}
