package edu.msudenver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setupTest() {
        player = new Player("Test Player");   // create the array
    }

    @Test
    void testDealAndDraw() {
        Card testCard = new Card(13, Suit.HEARTS);

        player.dealt(testCard);

        Card drawnCard = player.draw();

        Assertions.assertEquals(testCard, drawnCard);
    }

    @Test
    void testDiscard() {
        Card testCard = new Card(13, Suit.HEARTS);

        player.discard(testCard);

        Assertions.assertEquals(testCard, player.discardStack.pop());
    }

    @Test
    void testHasCards() {
        Assertions.assertFalse(player.hasCards());

        Card testCard = new Card(13, Suit.HEARTS);

        player.dealt(testCard);
        Assertions.assertTrue(player.hasCards());

        player.draw();
        Assertions.assertFalse(player.hasCards());

        player.discard(testCard);
        Assertions.assertFalse(player.hasCards());
    }

    @Test
    void testReplenishDrawStack() {
        Assertions.assertFalse(player.hasCards());

        Card testCard = new Card(13, Suit.HEARTS);

        player.discard(testCard);

        player.replenishDrawStack();

        Assertions.assertTrue(player.hasCards());

        Assertions.assertEquals(testCard, player.draw());
    }
}
