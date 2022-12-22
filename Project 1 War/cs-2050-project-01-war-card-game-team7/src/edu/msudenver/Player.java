package edu.msudenver;

import com.pearson.LinkedStack;

public class Player {
    protected String name;
    protected LinkedStack<Card> discardStack;
    protected LinkedStack<Card> drawStack;

    public Player(String name) {
        this.name = name;
        this.discardStack = new LinkedStack<>();
        this.drawStack = new LinkedStack<>();
    }

    public Card draw() {
        // TODO implement
        // Draw a card from the drawStack
        return null;
    }

    public void discard(Card card) {
        // TODO implement
        // Add a card to the discardStack
    }

    public void dealt(Card card) {
        // TODO implement
        // Player was dealt a card that needs to be added to the drawStack
    }

    public boolean hasCards() {
        // TODO implement
        // returns true if there are cards in the drawStack only
        // cards in discardStack do not count for this method
        return false;
    }

    public void replenishDrawStack() {
        // TODO implement
        // this method will take the cards currently in discardStack and add them to drawStack
        // EXTRA CREDIT: If you shuffle the cards in discardStack before adding them to drawStack,
        // that's worth an extra 5 points!
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", discardStack=" + discardStack +
                ", drawStack=" + drawStack +
                '}';
    }
}
