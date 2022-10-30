package com.javarush.island.rantsev.setting;

import com.javarush.island.rantsev.gamemap.Coordinates;
import com.javarush.island.rantsev.gamemap.Model;
import com.javarush.island.rantsev.gamemap.Location;

public class LoadParameters {
    public Model createIsland() {
        int width = Setting.get().getIslandWidth();
        int height = Setting.get().getIslandHeight();

        Model model = new Model(width, height);
        generation(model);
        return model;
    }

    private void generation(Model model) {
        Location[][] area = model.getArea();
        for (int y = 0; y < Setting.get().getIslandHeight(); y++) {
            for (int x = 0; x < Setting.get().getIslandWidth(); x++) {
                area[x][y] = new Location(new Coordinates(x, y));
            }
        }
    }
}


