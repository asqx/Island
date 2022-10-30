package com.javarush.island.rantsev.alive.abstraction;

import com.javarush.island.rantsev.alive.AnimalParameters;
import com.javarush.island.rantsev.action.EatPlant;
import com.javarush.island.rantsev.action.Hunt;
import com.javarush.island.rantsev.gamemap.Location;

public abstract class Omnivore extends Animal implements Hunt, EatPlant {
    public Omnivore(AnimalParameters base) {
        super(base);
    }

    @Override
    public void eat(Location location) {
        eatingPlant(location, this);

        if (this.hunger()) return;

        hunt(location, this);
    }

    @Override
    public void hunt(Location location, Animal hunter) {
        Hunt.super.hunt(location, hunter);
    }

    @Override
    public void eatingPlant(Location location, Animal herbivore) {
        EatPlant.super.eatingPlant(location, herbivore);
    }
}