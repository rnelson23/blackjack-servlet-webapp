package com.example.blackjack;

public enum Rank {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    public final String display;
    public final int value;

    Rank(String display, int value) {
        this.display = display;
        this.value = value;
    }

    public String leftPad() {
        return display.length() > 1 ? display : " " + display;
    }

    public String rightPad() {
        return display.length() > 1 ? display : display + " ";
    }

    @Override
    public String toString() {
        return display;
    }
}
