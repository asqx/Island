package com.javarush.island.rantsev.alive;

import java.util.Map;

public class AnimalParameters {
    private AnimalParameters() {
    }
    private AnimalTypes kind;
    private String icon;
    private int speed;
    private float weight;
    private int maxOnLocation;
    private float satietyLimit;
    private int reproductionChance;
    private Map<AnimalTypes, Integer> menu;
    public int stepsCountWithoutFood;
    public float getWastedSatietyPerStep() {
        return satietyLimit / stepsCountWithoutFood;
    }
    public AnimalTypes getKind() {
        return kind;
    }
    public String getIcon() {
        return icon;
    }
    public int getSpeed() {
        return speed;
    }
    public float getWeight() {
        return weight;
    }
    public int getMaxOnLocation() {
        return maxOnLocation;
    }
    public float getSatietyLimit() {
        return satietyLimit;
    }
    public int getReproductionChance() {
        return reproductionChance;
    }
    public Map<AnimalTypes, Integer> getMenu() {
        return menu;
    }
}
