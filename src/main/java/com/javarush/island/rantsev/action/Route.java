package com.javarush.island.rantsev.action;

public enum Route {
    TOP_LEFT_EDGE(Move.RIGHT, Move.BOTTOM),
    TOP_RIGHT_EDGE(Move.LEFT, Move.BOTTOM),
    TOP_EDGE(Move.RIGHT, Move.BOTTOM, Move.LEFT),
    BOTTOM_LEFT_EDGE(Move.TOP, Move.RIGHT),
    BOTTOM_RIGHT_EDGE(Move.TOP, Move.LEFT),
    BOTTOM_EDGE(Move.LEFT, Move.RIGHT, Move.TOP),
    LEFT_EDGE(Move.RIGHT, Move.BOTTOM, Move.TOP),
    RIGHT_EDGE(Move.LEFT, Move.BOTTOM, Move.TOP),
    ALL(Move.LEFT, Move.RIGHT, Move.BOTTOM, Move.TOP);

    public final Move[] moveList;

    Route(Move... moveList) {
        this.moveList = moveList;
    }
}
