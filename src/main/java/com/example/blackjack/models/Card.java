package com.example.blackjack.models;

public class Card {
    private final Rank rank;
    private final Suit suit;
    private boolean faceUp;
    private final String[] face;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.faceUp = false;
        this.face = new String[]{
                "┌───────────┐",
                "│ " + rank.getRank() + (rank.getRank().equals("10") ? "" : " ") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + suit.getSuit() + " │",
                "│ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │",
                "│ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │",
                "│ &nbsp;&nbsp;&nbsp; " + suit.getSuit() + " &nbsp;&nbsp;&nbsp; │",
                "│ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │",
                "│ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │",
                "│ " + suit.getSuit() + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + (rank.getRank().equals("10") ? "" : " ") + rank.getRank() + " │",
                "└───────────┘"
        };
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return rank.getValue();
    }

    public String getFace(int line) {
        return face[line];
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public String toString() {
        return rank.getRank() + suit.getSuit();
    }
}
