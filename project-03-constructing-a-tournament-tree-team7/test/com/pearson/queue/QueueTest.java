package com.pearson.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueueTest {
    private Queue<Integer, Double> linkedList;

    @BeforeEach
    void setupTest() {
        linkedList = new Queue<>();   // create the array
    }

    @Test
    void testQueue() {
        linkedList.insert(10, 10.0);
        linkedList.insert(11, 11.0);
        linkedList.insert(12, 12.0);
        linkedList.insert(13, 13.0);
        Assertions.assertEquals(10, linkedList.remove().getKey());
        Assertions.assertEquals(11, linkedList.remove().getKey());
        Assertions.assertEquals(12, linkedList.remove().getKey());
        Assertions.assertEquals(13, linkedList.remove().getKey());
    }
}
