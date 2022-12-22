package com.pearson.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeapTest {
    private Heap<Integer, Double> theHeap;

    @BeforeEach
    void setupTest() {
        theHeap = new Heap<>();
    }

    @Test
    void testHeap() {
        theHeap.insert(70, 70.0);
        theHeap.insert(40, 40.0);
        theHeap.insert(50, 50.0);
        theHeap.insert(20, 20.0);
        theHeap.insert(60, 60.0);
        theHeap.insert(100, 100.0);
        theHeap.insert(80, 80.0);
        theHeap.insert(30, 30.0);
        theHeap.insert(10, 10.0);
        theHeap.insert(90, 90.0);

        Assertions.assertEquals(100, theHeap.remove().getKey(), "First element should be 100");
        Assertions.assertEquals(90, theHeap.remove().getKey(), "Second element should be 90");
        Assertions.assertEquals(80, theHeap.remove().getKey(), "Third element should be 80");
        Assertions.assertEquals(70, theHeap.remove().getKey(), "Fourth element should be 70");
        Assertions.assertEquals(60, theHeap.remove().getKey(), "Fifth element should be 60");
        Assertions.assertEquals(50, theHeap.remove().getKey(), "Sixth element should be 50");
        Assertions.assertEquals(40, theHeap.remove().getKey(), "Seventh element should be 40");
        Assertions.assertEquals(30, theHeap.remove().getKey(), "Eighth element should be 30");
        Assertions.assertEquals(20, theHeap.remove().getKey(), "Ninth element should be 20");
        Assertions.assertEquals(10, theHeap.remove().getKey(), "Tenth element should be 10");
    }

    @Test
    void testChange() {
        int seventyIndex = theHeap.insert(70, 70.0);
        int fortyIndex = theHeap.insert(40, 40.0);
        int fiftyIndex = theHeap.insert(50, 50.0);
        int twentyIndex = theHeap.insert(20, 20.0);
        int sixtyIndex = theHeap.insert(60, 60.0);
        int oneHundredIndex = theHeap.insert(100, 100.0);
        int eightyIndex = theHeap.insert(80, 80.0);
        int thirtyIndex = theHeap.insert(30, 30.0);
        int tenIndex = theHeap.insert(10, 10.0);
        int ninetyIndex = theHeap.insert(90, 90.0);

        theHeap.change(tenIndex, 170);

        Assertions.assertEquals(170, theHeap.remove().getKey(), "First element should be 170");
        Assertions.assertEquals(100, theHeap.remove().getKey(), "Second element should be 100");
        Assertions.assertEquals(90, theHeap.remove().getKey(), "Third element should be 90");
        Assertions.assertEquals(80, theHeap.remove().getKey(), "Fourth element should be 80");
        Assertions.assertEquals(70, theHeap.remove().getKey(), "Fifth element should be 70");
        Assertions.assertEquals(60, theHeap.remove().getKey(), "Sixth element should be 60");
        Assertions.assertEquals(50, theHeap.remove().getKey(), "Seventh element should be 50");
        Assertions.assertEquals(40, theHeap.remove().getKey(), "Eighth element should be 40");
        Assertions.assertEquals(30, theHeap.remove().getKey(), "Ninth element should be 30");
        Assertions.assertEquals(20, theHeap.remove().getKey(), "Tenth element should be 20");
    }
}
