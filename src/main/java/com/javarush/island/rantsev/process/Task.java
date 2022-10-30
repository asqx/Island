package com.javarush.island.rantsev.process;

import com.javarush.island.rantsev.alive.abstraction.Animal;
import com.javarush.island.rantsev.gamemap.Location;

public class Task {
    private final Animal animal;
    private final Location location;
    private final Location[][] area;

    public Task(Animal animal, Location location, Location[][] area) {
        this.animal = animal;
        this.location = location;
        this.area = area;
    }

    public void execute() {
        try {
            animal.reproduce(location);
            animal.eat(location);
            animal.move(location, area);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
