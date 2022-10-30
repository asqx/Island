package com.javarush.island.rantsev.gamemap;

import java.util.Arrays;
import java.util.stream.Stream;

public class Model {
    private final Location[][] area;

    public Model(int width, int height) {
        this.area = new Location[width][height];
    }

    public Location[][] getArea() {
        return area;
    }

    public Stream<Location> getLocation() {
        return Arrays.stream(area).flatMap(Arrays::stream);
    }
}

