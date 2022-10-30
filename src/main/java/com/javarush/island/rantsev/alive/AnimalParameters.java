package com.javarush.island.rantsev.alive;

import java.util.Map;

public class AnimalParameters {
    private AnimalParameters() {
    }
    public AnimalTypes kind;
    public String icon;
    public int speed;
    public float weight;
    public int maxOnLocation;
    public float satietyLimit;
    public int reproductionChance;
    public Map<AnimalTypes, Integer> menu;
    public int stepsCountWithoutFood;
    public float getWastedSatietyPerStep() {
        return satietyLimit / stepsCountWithoutFood;
    }
}
