package edu.msudenver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void setupTest() {
        deck = new Deck();   // create the array
    }

    @Test
    void testShuffle() {
        deck.shuffle();

        HashMap<Suit, Set<Integer>> cardMap = new HashMap<>();
        while(!deck.cards.isEmpty()) {
            Card current = deck.cards.remove();
            if(!cardMap.containsKey(current.getSuit())) {
                cardMap.put(current.getSuit(), new HashSet<>());
            }
            cardMap.get(current.getSuit()).add(current.getFace());
        }

        Assertions.assertEquals(4, cardMap.keySet().size());
        Assertions.assertEquals(13, cardMap.get(Suit.HEARTS).size());
        Assertions.assertEquals(13, cardMap.get(Suit.CLUBS).size());
        Assertions.assertEquals(13, cardMap.get(Suit.SPADES).size());
        Assertions.assertEquals(13, cardMap.get(Suit.DIAMONDS).size());
    }

    @Test
    void testDraw() {
        deck.shuffle();
        double prevRandom = -1;
        for(int i = 0; i < 52; i++) {
            Card current = deck.draw();
            Assertions.assertTrue(prevRandom <= current.random);
            prevRandom = current.random;
        }
        Assertions.assertTrue(deck.cards.isEmpty());
    }
}
