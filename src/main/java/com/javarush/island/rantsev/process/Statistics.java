package com.javarush.island.rantsev.process;

import com.javarush.island.rantsev.alive.AnimalParameters;
import com.javarush.island.rantsev.gamemap.Model;
import com.javarush.island.rantsev.setting.Setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public record Statistics(Model model) {
    public void printStatistics() {
        Supplier<HashMap<AnimalParameters, Integer>> supplier =
                HashMap::new;

        BiConsumer<HashMap<AnimalParameters, Integer>, AnimalParameters> accumulator =
                (animalKindToInteger, item) -> animalKindToInteger.merge(item, 1, Integer::sum);
        BiConsumer<HashMap<AnimalParameters, Integer>, HashMap<AnimalParameters, Integer>> combiner =
                HashMap::putAll;

        AtomicReference<Float> vegetationCount = new AtomicReference<>((float) 0);

        HashMap<AnimalParameters, Integer> statsMap = model.getLocation()
                .flatMap(location -> {
                    vegetationCount.updateAndGet(v -> v + location.plant.amount);
                    return location.animalsMap.values().stream();
                })
                .flatMap(ArrayList::stream)
                .map(animal -> animal.animalParameters)
                .collect(supplier, accumulator, combiner);

        System.out.println("\uD83C\uDF3F: " + vegetationCount);
        statsMap.forEach((key, value) -> System.out.print(key.icon + ":\s" + value + "/" + key.maxOnLocation * Setting.get().getIslandWidth() * Setting.get().getIslandHeight() + "\s"));
        System.out.println();
    }
}
