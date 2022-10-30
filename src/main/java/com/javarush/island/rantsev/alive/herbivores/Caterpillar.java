package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(Setting.get().getAnimalProps().get("Caterpillar"));
    }
}
