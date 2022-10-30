package com.javarush.island.rantsev.alive.predators;

import com.javarush.island.rantsev.alive.abstraction.Predator;
import com.javarush.island.rantsev.setting.Setting;

public class Eagle extends Predator {
    public Eagle() {
        super(Setting.get().getAnimalProps().get("Eagle"));
    }
}
