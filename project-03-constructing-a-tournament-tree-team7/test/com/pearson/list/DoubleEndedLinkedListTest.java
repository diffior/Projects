package com.pearson.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoubleEndedLinkedListTest {
    private DoubleEndedLinkedList<Integer, Double> linkedList;

    @BeforeEach
    void setupTest() {
        linkedList = new DoubleEndedLinkedList<>();   // create the array
    }

    @Test
    void testAsStack() {
        linkedList.insertFirst(10, 10.0);
        linkedList.insertFirst(11, 11.0);
        linkedList.insertFirst(12, 12.0);
        linkedList.insertFirst(13, 13.0);
        Assertions.assertEquals(13, linkedList.deleteFirst().getKey());
        Assertions.assertEquals(12, linkedList.deleteFirst().getKey());
        Assertions.assertEquals(11, linkedList.deleteFirst().getKey());
        Assertions.assertEquals(10, linkedList.deleteFirst().getKey());
    }

    @Test
    void testAsQueue() {
        linkedList.insertLast(10, 10.0);
        linkedList.insertLast(11, 11.0);
        linkedList.insertLast(12, 12.0);
        linkedList.insertLast(13, 13.0);
        Assertions.assertEquals(10, linkedList.deleteFirst().getKey());
        Assertions.assertEquals(11, linkedList.deleteFirst().getKey());
        Assertions.assertEquals(12, linkedList.deleteFirst().getKey());
        Assertions.assertEquals(13, linkedList.deleteFirst().getKey());
    }
}
