package com.javarush.island.rantsev.alive.abstraction;

import com.javarush.island.rantsev.action.Eat;
import com.javarush.island.rantsev.action.Move;
import com.javarush.island.rantsev.action.Movable;
import com.javarush.island.rantsev.alive.*;
import com.javarush.island.rantsev.gamemap.Coordinates;
import com.javarush.island.rantsev.gamemap.Location;
import com.javarush.island.rantsev.util.IdGenerator;
import com.javarush.island.rantsev.util.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Animal implements Movable, Eat {
    public final int id;
    public AnimalParameters animalParameters;
    public float satiety;
    public boolean death = false;
    public boolean birth = false;
    public float weight;
    public boolean move = false;

    public Animal(AnimalParameters animalParameters) {
        this.id = IdGenerator.get();
        this.animalParameters = animalParameters;
        this.weight = animalParameters.weight;
        this.satiety = animalParameters.satietyLimit;
    }
    public boolean hunger() {
        return !(satiety < animalParameters.satietyLimit);
    }
    private void safeMove(Location fromLocation, Location[][] area) {
        if(move) return;
        fromLocation.getLock().lock();
        try {
            Location result = fromLocation;
            for (int i = 0; i < animalParameters.speed; i++) {
                Move[] moves = result.getAvailableDirections().moveList;
                int randomIndex = Randomizer.RANDOM.nextInt(moves.length);
                Coordinates delta = moves[randomIndex].getDelta();
                Coordinates newCoordinates = result.coordinates.evaluate(delta);
                Location newLocation = area[newCoordinates.x][newCoordinates.y];
                if (newLocation.hasFreeSpace(this)) {
                    result = newLocation;
                }
            }
            if (result != fromLocation) {
                fromLocation.animalsMap.get(this.animalParameters.kind).remove(this);
                result.animalsMap.get(this.animalParameters.kind).add(this);
            }
            float satietyRes = satiety - animalParameters.getWastedSatietyPerStep();
            satiety = satietyRes <= 0 ? 0 : satietyRes;
            move = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fromLocation.getLock().unlock();
        }
    }
    @Override
    public void move(Location fromLocation, Location[][] area) {
        safeMove(fromLocation, area);
    }
    public void reproduce(Location location) {
        safeReproduce(location);
    }
    private void safeReproduce(Location location) {
        location.getLock().lock();
        try {
            ArrayList<Animal> list = location.animalsMap.get(animalParameters.kind);
            if (list.size() < 2 ||
                          birth ||
                          !location.hasFreeSpace(this) ||
                          !Randomizer.isSuccess(animalParameters.reproductionChance)
            ) return;
            Animal young = createAnimal();
            young.birth = true;
            list.add(young);
        } finally {
            location.getLock().unlock();
        }
    }
    private Animal createAnimal() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        String result = animalParameters.icon + id + "S:\u001B[32m" + satiety;
        if (death) {
            result += "\u001B[35mD";
        }
        if (birth) {
            result += "\u001B[35mCh";
        }
        return result + "\u001B[0m";
    }
}