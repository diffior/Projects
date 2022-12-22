package com.pearson.stack;

import com.pearson.common.Link;
import com.pearson.list.DoubleEndedLinkedList;

public class Stack<T extends Comparable<T>, Y> extends DoubleEndedLinkedList<T, Y> {
    public void push(T key, Y data) {
        super.insertFirst(key, data);
    }

    public Link<T, Y> pop() {
        return super.deleteFirst();
    }

    public Link<T, Y> peek() {
        return super.peekFirst();
    }
}
