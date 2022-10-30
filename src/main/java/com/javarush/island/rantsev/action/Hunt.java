package com.javarush.island.rantsev.action;

import com.javarush.island.rantsev.alive.AnimalTypes;
import com.javarush.island.rantsev.util.Randomizer;
import com.javarush.island.rantsev.alive.abstraction.Animal;
import com.javarush.island.rantsev.gamemap.Location;

import java.util.ArrayList;
import java.util.Map;

public interface Hunt {
    default void hunt(Location location, Animal hunter)  {
        location.getLock().lock();
        try {
            for (Map.Entry<AnimalTypes, Integer> entry : hunter.animalParameters.menu.entrySet()) {
                AnimalTypes victimKind = entry.getKey();
                int successHuntChance = entry.getValue();
                ArrayList<Animal> list = location.animalsMap.get(victimKind);
                if(list == null || list.isEmpty()) continue;
                int attemptsCount = Math.max(1, list.get(0).animalParameters.maxOnLocation/3);

                for (Animal animal : list) {
                    if (hunter.hunger()) {
                        break;
                    }
                    if (attemptsCount <= 0) {
                        break;
                    }
                    if (animal.death) {
                        eatAnimal(animal, hunter);
                        continue;
                    }
                    attemptsCount--;
                    if (!Randomizer.isSuccess(successHuntChance)) continue;
                    eatAnimal(animal, hunter);
                }
            }
        } finally {
            location.getLock().unlock();
        }
    }
    private void eatAnimal(Animal victim, Animal hunter) {
        if(victim.weight <= 0) return;
        float need = hunter.animalParameters.satietyLimit - hunter.satiety;
        float willBeEaten = victim.weight - need > 0 ? need : victim.weight;
        hunter.satiety += willBeEaten;
        victim.weight -= willBeEaten;
        victim.death = true;
    }
}
