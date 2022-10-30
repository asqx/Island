package com.javarush.island.rantsev.alive.omnivores;

import com.javarush.island.rantsev.alive.abstraction.Omnivore;
import com.javarush.island.rantsev.setting.Setting;

public class Mouse extends Omnivore {
    public Mouse() {
        super(Setting.get().getAnimalProps().get("Mouse"));
    }
}
