package com.javarush.island.rantsev.process;

import com.javarush.island.rantsev.gamemap.LocationWorker;
import com.javarush.island.rantsev.setting.Setting;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWorker extends Thread {
    private static final String GAME_OVER = "The game is over";
    private final Statistics statistics;
    private final int CORE_POOL_SIZE = Setting.get().getCorePoolSize();
    private final int LIFE_CYCLE_DURATION = Setting.get().getLifeCycleDuration();
    public GameWorker(Statistics statistics) {
        this.statistics = statistics;
    }
    @Override
    public void run() {
        statistics.printStatistics();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        scheduledExecutorService.scheduleWithFixedDelay(this::runAndWaitLocationWorkers, LIFE_CYCLE_DURATION, LIFE_CYCLE_DURATION, TimeUnit.MILLISECONDS);
    }
    public void runAndWaitLocationWorkers() {
        ArrayList<LocationWorker> locationWorkers = new ArrayList<>();
        statistics.model().getLocation().forEach(location -> {
            location.clear();
            location.vegetationGrow();
            locationWorkers.add(new LocationWorker(location, statistics.model().getArea()));
        });
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        for (LocationWorker locationWorker : locationWorkers) {
            fixedThreadPool.submit(locationWorker);
        }
        fixedThreadPool.shutdown();
        try {
            if (fixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS)) {
                statistics.printStatistics();
            }
        } catch (InterruptedException e) {
            System.out.println(GAME_OVER);
        }
    }
}
