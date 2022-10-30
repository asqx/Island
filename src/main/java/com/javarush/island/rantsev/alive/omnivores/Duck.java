package com.javarush.island.rantsev.alive.omnivores;

import com.javarush.island.rantsev.alive.abstraction.Omnivore;
import com.javarush.island.rantsev.setting.Setting;

public class Duck extends Omnivore {
    public Duck() {
        super(Setting.get().getAnimalProps().get("Duck"));
    }
}
