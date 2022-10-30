package com.javarush.island.rantsev.alive.predators;

import com.javarush.island.rantsev.alive.abstraction.Predator;
import com.javarush.island.rantsev.setting.Setting;

public class Fox extends Predator {
    public Fox() {
        super(Setting.get().getAnimalProps().get("Fox"));
    }
}
