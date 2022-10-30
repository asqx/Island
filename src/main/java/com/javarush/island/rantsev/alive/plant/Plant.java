package com.javarush.island.rantsev.alive.plant;

import com.javarush.island.rantsev.setting.Setting;

import java.util.concurrent.ThreadLocalRandom;

public class Plant {
    public float amount;
    private static final float MAX = Setting.get().getMaxVegetationOnLocation();
    private static final float GROW_STEP = Setting.get().getVegetationGrowPerStep();
    public Plant() {
        this.amount = (float) ThreadLocalRandom.current().nextInt((int) MAX+1);
    }
    public void grow() {
        amount = Math.min(MAX, amount + GROW_STEP);
    }
    @Override
    public String toString() {
        return Setting.get().getVegetationIcon() + amount;
    }
}
