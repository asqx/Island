package com.javarush.island.rantsev.gamemap;

public class Coordinates {
    public final int x;
    public final int y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates evaluate(Coordinates delta) {
        return new Coordinates(x + delta.x, y + delta.y);
    }
}
