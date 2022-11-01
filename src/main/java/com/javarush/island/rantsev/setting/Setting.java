package com.javarush.island.rantsev.setting;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.rantsev.alive.AnimalParameters;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class Setting {
    private static final Setting SETTING = new Setting();
    private static final String FILE_SETTING = "setting.yaml";
    private int islandWidth;
    private int islandHeight;
    private int corePoolSize;
    private int lifeCycleDuration;
    private float maxVegetationOnLocation;
    private float vegetationGrowPerStep;
    private boolean printStatsForDev;
    private String vegetationIcon;
    private Map<String, AnimalParameters> animalProps;
    private Setting() {
        URL source = Setting.class.getClassLoader().getResource(FILE_SETTING);
        ObjectReader objectReader = new YAMLMapper().readerForUpdating(this);
        if (Objects.nonNull(source)) {
            try {
                objectReader.readValue(source.openStream());
            } catch (IOException e) {
                System.out.printf("Error reading settings file: %s", e);
            }
        }
    }
    public int getIslandWidth() {
        return islandWidth;
    }

    public int getIslandHeight() {
        return islandHeight;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public int getLifeCycleDuration() {
        return lifeCycleDuration;
    }

    public Map<String, AnimalParameters> getAnimalProps() {
        return animalProps;
    }

    public float getMaxVegetationOnLocation() {
        return maxVegetationOnLocation;
    }

    public float getVegetationGrowPerStep() {
        return vegetationGrowPerStep;
    }

    public String getVegetationIcon() {
        return vegetationIcon;
    }

    public static Setting get() {
        return SETTING;
    }
}
