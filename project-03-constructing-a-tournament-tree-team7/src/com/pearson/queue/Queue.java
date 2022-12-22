package com.pearson.queue;

import com.pearson.common.Link;
import com.pearson.list.DoubleEndedLinkedList;

public class Queue<T extends Comparable<T>, Y> extends DoubleEndedLinkedList<T, Y> {
    public void insert(T key, Y data) {
        super.insertLast(key, data);
    }

    public Link<T, Y> remove() {
        return super.deleteFirst();
    }

    public Link<T, Y> peek() {
        return super.peekFirst();
    }
}
