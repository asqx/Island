package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Deer extends Herbivore {
    public Deer() {
        super(Setting.get().getAnimalProps().get("Deer"));
    }
}
