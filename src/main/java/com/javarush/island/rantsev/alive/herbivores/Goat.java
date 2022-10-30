package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Goat extends Herbivore {
    public Goat() {
        super(Setting.get().getAnimalProps().get("Goat"));
    }
}
