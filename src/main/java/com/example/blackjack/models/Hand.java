package com.example.blackjack.models;

import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards;
    private boolean hasStood;

    public Hand() {
        cards = new ArrayList<>();
        hasStood = false;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void flipLastCard() {
        cards.get(cards.size() - 1).flip();
    }

    public boolean hasStood() {
        return hasStood;
    }

    public void stand() {
        hasStood = true;
    }

    public int getHandValue() {
        int handValue = 0;
        int aceCount = 0;

        for (Card card : cards) {
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }

            handValue += card.getValue();
        }

        while (handValue > 21 && aceCount > 0) {
            handValue -= 10;
            aceCount--;
        }

        return handValue;
    }

    public int getDealerHandValue() {
        int handValue = 0;
        int aceCount = 0;

        for (Card card : cards) {
            if (!card.isFaceUp()) {
                continue;
            }

            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }

            handValue += card.getValue();
        }

        while (handValue > 21 && aceCount > 0) {
            handValue -= 10;
            aceCount--;
        }

        return handValue;
    }

    public boolean isBusted() {
        return getHandValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getHandValue() == 21;
    }

    public boolean isDealerBlackjack() {
        return (cards.get(0).getRank() == Rank.ACE || cards.get(0).getRank() == Rank.TEN) && cards.size() == 2 && getHandValue() == 21;
    }

    public boolean is21() {
        return getHandValue() == 21;
    }

    public boolean isSoft17() {
        return getHandValue() == 17 && cards.size() == 2 && cards.get(0).getRank() == Rank.ACE;
    }

    public boolean isPair() {
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }

    public boolean isDoubleable() {
        return cards.size() == 2 && !isPair();
    }

    public boolean isSplittable() {
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }

    public boolean isSurrenderable() {
        return cards.size() == 2;
    }

    public boolean isHittable() {
        return !isBusted() && !isBlackjack();
    }

    public boolean isStandable() {
        return !isHittable();
    }

    public boolean isDealerHand() {
        return cards.size() == 2 && !cards.get(0).isFaceUp();
    }

    public boolean isPlayerHand() {
        return !isDealerHand();
    }

    public String[] toArray() {
        String[] hand = new String[cards.size()];

        for (int i = 0; i < cards.size(); i++) {
            hand[i] = cards.get(i).toString();
        }

        return hand;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Card card : cards) {
            if (card.isFaceUp()) {
                sb.append(card);

            } else {
                sb.append("XX");
            }

            sb.append(" ");
        }

        return sb.toString();
    }

    public String toStringFancy() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (Card card : cards) {
                if (card.isFaceUp() || (i == 0 || i == 8)) {
                    sb.append(card.getFace(i));

                } else {
                    sb.append("│ ░░░░░░░░░ │");
                }

                sb.append(" ");
            }

            sb.append("<br>");
        }

        return sb.toString();
    }
}
