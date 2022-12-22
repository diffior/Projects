package edu.msudenver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerExtraCreditTest {
    private Player player;

    @BeforeEach
    void setupTest() {
        player = new Player("Test Player");   // create the array
    }

    @Test
    void testReplenishDrawStackExtraCredit() {
        Assertions.assertFalse(player.hasCards());

        Card testCard1 = new Card(13, Suit.HEARTS);
        testCard1.random = 0.1;
        player.discard(testCard1);

        Card testCard2 = new Card(4, Suit.HEARTS);
        testCard2.random = 0.5;
        player.discard(testCard2);

        Card testCard3 = new Card(3, Suit.HEARTS);
        testCard3.random = 0.3;
        player.discard(testCard3);

        Card testCard4 = new Card(2, Suit.HEARTS);
        testCard4.random = 0.2;
        player.discard(testCard4);

        player.replenishDrawStack();
        Assertions.assertTrue(player.hasCards());

        Assertions.assertEquals(testCard1, player.draw());
        Assertions.assertEquals(testCard4, player.draw());
        Assertions.assertEquals(testCard3, player.draw());
        Assertions.assertEquals(testCard2, player.draw());
    }
}
