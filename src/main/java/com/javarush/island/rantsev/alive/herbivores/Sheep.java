package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Sheep extends Herbivore {
    public Sheep() {
        super(Setting.get().getAnimalProps().get("Sheep"));
    }
}
