package com.example.blackjack;

public class Card {
    public final Rank rank;
    public boolean faceUp;
    public final String face;
    public final String back;

    public Card(Rank rank, String suit) {
        this.rank = rank;
        this.faceUp = false;

        this.face = "┌───────────┐<br>" +
                    "│ " + rank.rightPad() + "      " + suit + " │<br>" +
                    "│           │<br>" +
                    "│           │<br>" +
                    "│     " + suit + "     │<br>" +
                    "│           │<br>" +
                    "│           │<br>" +
                    "│ " + suit + "      " + rank.leftPad() + " │<br>" +
                    "└───────────┘";

        this.back = "┌───────────┐<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "│ ░░░░░░░░░ │<br>" +
                    "└───────────┘";
    }

    public Card flip() {
        faceUp = !faceUp;
        return this;
    }

    @Override
    public String toString() {
        return faceUp ? face : back;
    }
}
