package com.javarush.island.rantsev.action;

import com.javarush.island.rantsev.gamemap.Location;

public interface Movable {
    void move(Location fromLocation, Location[][] area);
}
