package com.example.gamelife3d.service;

import com.example.gamelife3d.model.World3D;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class GameOfLifeService {
    private final ConcurrentHashMap<String, World3D> worlds;
    private final ScheduledExecutorService scheduler;
    private final ConcurrentHashMap<String, ScheduledFuture<?>> runningTasks;

    public GameOfLifeService() {
        this.worlds = new ConcurrentHashMap<>();
        this.scheduler = java.util.concurrent.Executors.newScheduledThreadPool(2);
        this.runningTasks = new ConcurrentHashMap<>();
    }

    public World3D createWorld(String id, int width, int height, int depth) {
        World3D world = new World3D(width, height, depth);
        worlds.put(id, world);
        return world;
    }

    public World3D getWorld(String id) {
        return worlds.get(id);
    }

    public void startSimulation(String id, int delayMillis) {
        if (runningTasks.containsKey(id)) {
            return; // Already running
        }

        ScheduledFuture<?> task = scheduler.scheduleAtFixedRate(() -> {
            World3D world = worlds.get(id);
            if (world != null) {
                world.update();
            }
        }, 0, delayMillis, TimeUnit.MILLISECONDS);

        runningTasks.put(id, task);
    }

    public void stopSimulation(String id) {
        ScheduledFuture<?> task = runningTasks.remove(id);
        if (task != null) {
            task.cancel(false);
        }
    }

    public void destroyWorld(String id) {
        stopSimulation(id);
        worlds.remove(id);
    }

    public void randomizeWorld(String id, int density) {
        World3D world = worlds.get(id);
        if (world != null) {
            world.randomize(density);
        }
    }

    public void clearWorld(String id) {
        World3D world = worlds.get(id);
        if (world != null) {
            world.clear();
        }
    }

    public void setCellAlive(String id, int x, int y, int z) {
        World3D world = worlds.get(id);
        if (world != null) {
            world.setCellAlive(x, y, z);
        }
    }

    public void setCellDead(String id, int x, int y, int z) {
        World3D world = worlds.get(id);
        if (world != null) {
            world.setCellDead(x, y, z);
        }
    }

    public void close() {
        for (ScheduledFuture<?> task : runningTasks.values()) {
            task.cancel(false);
        }
        scheduler.shutdown();
    }
}
