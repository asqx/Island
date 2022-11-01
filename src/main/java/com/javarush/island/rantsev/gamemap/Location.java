package com.javarush.island.rantsev.gamemap;

import com.javarush.island.rantsev.action.Route;
import com.javarush.island.rantsev.alive.*;
import com.javarush.island.rantsev.alive.abstraction.Animal;
import com.javarush.island.rantsev.alive.herbivores.*;
import com.javarush.island.rantsev.alive.omnivores.Boar;
import com.javarush.island.rantsev.alive.omnivores.Duck;
import com.javarush.island.rantsev.alive.omnivores.Mouse;
import com.javarush.island.rantsev.alive.plant.Plant;
import com.javarush.island.rantsev.alive.predators.*;
import com.javarush.island.rantsev.setting.Setting;
import com.javarush.island.rantsev.util.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class Location {
    public final Coordinates coordinates;
    public final HashMap<AnimalTypes, ArrayList<Animal>> animalsMap = new HashMap<>();
    private Route availableDirections;
    public Plant plant;
    private final Lock lock = new ReentrantLock(true);
    private static final Animal[] ANIMAL_GENERATION_LIST = new Animal[]{
            new Buffalo(), new Caterpillar(), new Deer(), new Goat(), new Horse(), new Rabbit(), new Sheep(),
            new Boar(), new Duck(), new Mouse(),
            new Bear(), new Boa(), new Eagle(), new Fox(), new Wolf()
    };

    public Location(Coordinates coordinates) {
        this.coordinates = coordinates;

        int x = coordinates.x;
        int y = coordinates.y;

        int limitX = Setting.get().getIslandWidth() - 1;
        int limitY = Setting.get().getIslandHeight() - 1;

        createRoute(x, y, limitX, limitY);
        spawn();
    }
    private void createRoute(int x, int y, int limitX, int limitY) {
        if (x == 0 && y == 0) {
            availableDirections = Route.BOTTOM_LEFT_EDGE;
        } else if (x == limitX && y == 0) {
            availableDirections = Route.BOTTOM_RIGHT_EDGE;
        } else if (y == 0) {
            availableDirections = Route.BOTTOM_EDGE;
        } else if (x == 0 && y == limitY) {
            availableDirections = Route.TOP_LEFT_EDGE;
        } else if (x == limitX && y == limitY) {
            availableDirections = Route.TOP_RIGHT_EDGE;
        } else if (y == limitY) {
            availableDirections = Route.TOP_EDGE;
        } else if (x == 0) {
            availableDirections = Route.LEFT_EDGE;
        } else if (x == limitX) {
            availableDirections = Route.RIGHT_EDGE;
        } else {
            availableDirections = Route.ALL;
        }
    }
    private void spawn() {
        plant = new Plant();
        for (Animal animal : ANIMAL_GENERATION_LIST) {
            int animalsCount = Randomizer.RANDOM.nextInt(animal.animalParameters.getMaxOnLocation());
            ArrayList<Animal> animalList = new ArrayList<>();

            for (int i = 0; i < animalsCount; i++) {
                animalList.add(tryToCreateNewAnimal(animal));
            }

            animalsMap.put(animal.animalParameters.getKind(), animalList);
        }
    }
    public void getAnimals(Consumer<Animal> consumer) {
        animalsMap.values().stream().flatMap(ArrayList::stream).forEach(consumer);
    }

    public Lock getLock() {
        return lock;
    }

    public void vegetationGrow() {
        lock.lock();
        try {
            plant.grow();
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        lock.lock();

        try {
            animalsMap.values().forEach(list -> {
                list.forEach(animal -> {
                    animal.birth = false;
                    animal.move = false;
                });

                list.removeIf(animal -> animal.death || animal.satiety < animal.animalParameters.getWastedSatietyPerStep());
            });
        } finally {
            lock.unlock();
        }
    }

    public Animal tryToCreateNewAnimal(Animal animalExample) {
        try {
            Class<? extends Animal> clazz = animalExample.getClass();
            return clazz.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasFreeSpace(Animal animal) {
        AnimalParameters animalParameters = animal.animalParameters;
        ArrayList<Animal> list = animalsMap.get(animalParameters.getKind());

        return list.size() < animalParameters.getMaxOnLocation();
    }

    public Route getAvailableDirections() {
        return availableDirections;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("\u001B[31m")
                .append(coordinates.x)
                .append(":")
                .append(coordinates.y)
                .append(" \033[0m")
                .append(plant)
                .append(" ");
        animalsMap.values().stream()
                .flatMap(ArrayList::stream)
                .forEach(animal -> stringBuilder.append(animal).append(" "));

        return stringBuilder.toString();
    }
}
