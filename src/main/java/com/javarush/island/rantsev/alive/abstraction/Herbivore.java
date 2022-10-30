package com.javarush.island.rantsev.alive.abstraction;

import com.javarush.island.rantsev.alive.AnimalParameters;
import com.javarush.island.rantsev.action.EatPlant;
import com.javarush.island.rantsev.gamemap.Location;

public abstract class Herbivore extends Animal implements EatPlant {
    @Override
    public void eat(Location location) {
        eatingPlant(location, this);
    }

    @Override
    public void eatingPlant(Location location, Animal herbivore) {
        EatPlant.super.eatingPlant(location, herbivore);
    }

    public Herbivore(AnimalParameters base) {
        super(base);
    }
}
