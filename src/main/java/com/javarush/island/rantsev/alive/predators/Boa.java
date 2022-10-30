package com.javarush.island.rantsev.alive.predators;

import com.javarush.island.rantsev.alive.abstraction.Predator;
import com.javarush.island.rantsev.setting.Setting;

public class Boa extends Predator {
    public Boa() {
        super(Setting.get().getAnimalProps().get("Boa"));
    }
}
