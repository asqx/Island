package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Horse extends Herbivore {
    public Horse() {
        super(Setting.get().getAnimalProps().get("Horse"));
    }
}
