package com.pearson.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackTest {
    private Stack<Double, Double> stack;

    @BeforeEach
    void setupTest() {
        stack = new Stack<>();   // create the array
    }

    @Test
    void testStack() {
        stack.push(10.0, 10.0);
        stack.push(11.0, 11.0);
        stack.push(12.0, 12.0);
        stack.push(13.0, 13.0);
        Assertions.assertEquals(13.0, stack.pop().getKey());
        Assertions.assertEquals(12.0, stack.pop().getKey());
        Assertions.assertEquals(11.0, stack.pop().getKey());
        Assertions.assertEquals(10.0, stack.pop().getKey());
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(stack.isEmpty());
        stack.push(10.0, 10.0);
        stack.push(11.0, 11.0);
        Assertions.assertFalse(stack.isEmpty());
    }
}
