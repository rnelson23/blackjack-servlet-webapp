package com.example.blackjack;

import java.util.ArrayList;

public class Deck {
    public final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (String suit : new String[]{"♣", "♦", "♥", "♠"}) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }

        for (int i = 0; i < cards.size(); i++) {
            int j = (int) (Math.random() * cards.size());
            Card temp = cards.get(i);

            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public void dealFaceUp(Hand hand) {
        hand.cards.add(cards.remove(0).flip());
    }

    public void dealFaceDown(Hand hand) {
        hand.cards.add(cards.remove(0));
    }
}
