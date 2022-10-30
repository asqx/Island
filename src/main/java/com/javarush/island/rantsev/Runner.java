package com.javarush.island.rantsev;

import com.javarush.island.rantsev.process.Statistics;
import com.javarush.island.rantsev.process.GameWorker;
import com.javarush.island.rantsev.setting.LoadParameters;
import com.javarush.island.rantsev.gamemap.Model;

public class Runner {
    public static void main(String[] args) {
        LoadParameters loadParameters = new LoadParameters();
        Model model = loadParameters.createIsland();
        Statistics statistics = new Statistics(model);
        GameWorker gameWorker = new GameWorker(statistics);
        gameWorker.start();
    }
}
