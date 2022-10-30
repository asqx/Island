package com.javarush.island.rantsev.alive.herbivores;

import com.javarush.island.rantsev.alive.abstraction.Herbivore;
import com.javarush.island.rantsev.setting.Setting;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(Setting.get().getAnimalProps().get("Buffalo"));
    }
}
