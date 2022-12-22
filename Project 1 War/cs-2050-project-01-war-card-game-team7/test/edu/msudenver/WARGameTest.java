package edu.msudenver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WARGameTest {
    private WARGame game;

    @BeforeEach
    void setupTest() {
        game = new WARGame();   // create the array
    }

    @Test
    void testSetup() {
        game.setup();

        Assertions.assertNotEquals(game.player1, game.player2);

        Assertions.assertNotNull(game.player1);
        Assertions.assertFalse(game.player1.drawStack.isEmpty());
        Assertions.assertTrue(game.player1.discardStack.isEmpty());

        Assertions.assertNotNull(game.player2);
        Assertions.assertFalse(game.player2.drawStack.isEmpty());
        Assertions.assertTrue(game.player2.discardStack.isEmpty());

        Assertions.assertNotNull(game.deck);
    }

    @Test
    void testPlay() {
        game.setup();

        // This is a very simple test like a main method that just runs the game
        // It does not verify the game works correctly, just that it does not error!
        game.play();
    }
}
