package edu.msudenver;

public class Card implements Comparable<Card> {
    protected int face;
    protected Suit suit;

    protected double random;

    public Card(int face, Suit suit) {
        this.face = face;
        this.suit = suit;

        this.random = Math.random();
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "face=" + face +
                ", suit=" + suit +
                '}';
    }


    @Override
    public int compareTo(Card that) {
        if (this.random != that.random) {
            return (this.random < that.random ? -1 : 1);
        }

        return 0;
    }
}
