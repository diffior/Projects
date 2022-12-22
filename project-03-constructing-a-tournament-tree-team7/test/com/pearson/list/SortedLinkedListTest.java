package com.pearson.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedLinkedListTest {
    private SortedLinkedList<Double, Double> sortedLinkedList;

    @BeforeEach
    void setupTest() {
        sortedLinkedList = new SortedLinkedList<>();   // create the array
    }

    @Test
    void testInsertOutOfOrder() {
        sortedLinkedList.insert(10.0, 10.0);
        sortedLinkedList.insert(5.0, 5.0);
        sortedLinkedList.insert(20.0, 20.0);
        sortedLinkedList.insert(15.0, 15.0);
        Assertions.assertEquals(5.0, sortedLinkedList.remove().getKey());
        Assertions.assertEquals(10.0, sortedLinkedList.remove().getKey());
        Assertions.assertEquals(15.0, sortedLinkedList.remove().getKey());
        Assertions.assertEquals(20.0, sortedLinkedList.remove().getKey());
    }
}
