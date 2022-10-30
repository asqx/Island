package com.javarush.island.rantsev.action;

import com.javarush.island.rantsev.gamemap.Coordinates;

public enum Move {
    LEFT(-1, 0),
    TOP(0, 1),
    RIGHT(1, 0),
    BOTTOM(0, -1);
    private final Coordinates delta;
    Move(int x, int y) {
        delta = new Coordinates(x, y);
    }

    public Coordinates getDelta() {
        return delta;
    }
}
