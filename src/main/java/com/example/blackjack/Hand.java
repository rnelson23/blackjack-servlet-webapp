package com.example.blackjack;

import java.util.ArrayList;

public class Hand {
    public final ArrayList<Card> cards;
    public boolean stood;

    public Hand() {
        cards = new ArrayList<>();
        stood = false;
    }

    public boolean isBlackjack() {
        if (cards.size() > 2) { return false; }

        for (Card card : cards) {
            if (card.rank != Rank.ACE && card.rank != Rank.TEN) {
                return false;
            }
        }

        return true;
    }

    public void flip() {
        cards.get(cards.size() - 1).flip();
    }

    public int value() {
        int value = 0;
        int numAces = 0;

        for (Card card : cards) {
            if (!card.faceUp) { continue; }
            if (card.rank == Rank.ACE) {
                numAces++;
            }

            value += card.rank.value;
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }

        return value;
    }
}
