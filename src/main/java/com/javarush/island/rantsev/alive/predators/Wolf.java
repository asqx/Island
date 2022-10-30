package com.javarush.island.rantsev.alive.predators;

import com.javarush.island.rantsev.alive.abstraction.Predator;
import com.javarush.island.rantsev.setting.Setting;

public class Wolf extends Predator {
    public Wolf() {
        super(Setting.get().getAnimalProps().get("Wolf"));
    }
}
