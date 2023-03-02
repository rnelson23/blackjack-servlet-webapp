package com.example.blackjack.models;

import java.util.ArrayList;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int j = (int) (Math.random() * cards.size());
            Card temp = cards.get(i);

            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public void dealFaceUp(Hand hand) {
        Card card = cards.remove(0);
        card.flip();
        hand.addCard(card);
    }

    public void dealFaceDown(Hand hand) {
        hand.addCard(cards.remove(0));
    }
}
