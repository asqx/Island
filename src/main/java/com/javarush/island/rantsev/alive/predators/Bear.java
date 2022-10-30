package com.javarush.island.rantsev.alive.predators;

import com.javarush.island.rantsev.alive.abstraction.Predator;
import com.javarush.island.rantsev.setting.Setting;

public class Bear extends Predator {
    public Bear() {
        super(Setting.get().getAnimalProps().get("Bear"));
    }
}
