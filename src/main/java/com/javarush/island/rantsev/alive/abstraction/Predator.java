package com.javarush.island.rantsev.alive.abstraction;

import com.javarush.island.rantsev.alive.abstraction.Animal;
import com.javarush.island.rantsev.alive.AnimalParameters;
import com.javarush.island.rantsev.action.Hunt;
import com.javarush.island.rantsev.gamemap.Location;

public abstract class Predator extends Animal implements Hunt {
    @Override
    public void eat(Location location) {
        hunt(location, this);
    }

    @Override
    public void hunt(Location location, Animal hunter) {
        Hunt.super.hunt(location, hunter);
    }

    public Predator(AnimalParameters base) {
        super(base);
    }
}
