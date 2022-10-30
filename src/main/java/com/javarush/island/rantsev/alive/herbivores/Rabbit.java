package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(Setting.get().getAnimalProps().get("Rabbit"));
    }
}
