package com.javarush.island.rantsev.alive.omnivores;

import com.javarush.island.rantsev.alive.abstraction.Omnivore;
import com.javarush.island.rantsev.setting.Setting;

public class Boar extends Omnivore {
    public Boar() {
        super(Setting.get().getAnimalProps().get("Boar"));
    }
}
