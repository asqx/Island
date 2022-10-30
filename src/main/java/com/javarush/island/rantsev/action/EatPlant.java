package com.javarush.island.rantsev.action;

import com.javarush.island.rantsev.alive.abstraction.Animal;
import com.javarush.island.rantsev.gamemap.Location;

public interface EatPlant {
    default void eatingPlant(Location location, Animal herbivore) {
        location.getLock().lock();
        try {
            float vegetationValue = location.plant.amount;
            if(vegetationValue <= 0) return;

            float needVegetation = herbivore.animalParameters.satietyLimit - herbivore.satiety;
            float possibleValue = Math.min(needVegetation, vegetationValue);
            herbivore.satiety += possibleValue;
            location.plant.amount -= possibleValue;
        } finally {
            location.getLock().unlock();
        }
    }
}
