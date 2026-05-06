package com.example.gamelife3d.websocket;

import com.example.gamelife3d.model.Cell;
import com.example.gamelife3d.model.World3D;
import com.example.gamelife3d.service.GameOfLifeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ServerEndpoint("/game-of-life/ws/{id}")
@ApplicationScoped
public class GameOfLifeWebSocket {

    @Inject
    GameOfLifeService gameOfLifeService;

    private final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        sessions.put(id, session);
        System.out.println("WebSocket connection opened for world: " + id);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("id") String id) {
        // Handle messages from client if needed
        System.out.println("Message received: " + message);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        sessions.remove(id);
        stopSimulation(id);
        System.out.println("WebSocket connection closed for world: " + id);
    }

    @OnError
    public void onError(Session session, @PathParam("id") String id, Throwable throwable) {
        System.out.println("WebSocket error for world: " + id);
        throwable.printStackTrace();
        sessions.remove(id);
        stopSimulation(id);
    }

    public void startSimulation(String id, int delayMillis) {
        if (tasks.containsKey(id)) {
            return; // Already running
        }

        ScheduledFuture<?> task = java.util.concurrent.Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(() -> {
                    try {
                        World3D world = gameOfLifeService.getWorld(id);
                        if (world != null) {
                            world.update();
                            sendWorldUpdate(id, world);
                        }
                    } catch (Exception e) {
                        System.err.println("Error in simulation: " + e.getMessage());
                    }
                }, 0, delayMillis, TimeUnit.MILLISECONDS);

        tasks.put(id, task);
    }

    public void stopSimulation(String id) {
        ScheduledFuture<?> task = tasks.remove(id);
        if (task != null) {
            task.cancel(false);
        }
    }

    private void sendWorldUpdate(String id, World3D world) {
        Session session = sessions.get(id);
        if (session != null && session.isOpen()) {
            try {
                // Send the world state as JSON
                String worldJson = "{"
                        + "\"worldId\":\"" + id + "\","
                        + "\"generation\":" + world.getGeneration() + ","
                        + "\"aliveCells\":" + world.getAliveCells().size() + ","
                        + "\"width\":" + world.getWidth() + ","
                        + "\"height\":" + world.getHeight() + ","
                        + "\"depth\":" + world.getDepth() + ","
                        + "\"timestamp\":" + System.currentTimeMillis() + ""
                        + "}";
                session.getBasicRemote().sendText(worldJson);
            } catch (IOException e) {
                System.err.println("Error sending world update: " + e.getMessage());
            }
        }
    }

    public void sendWorldData(String id, World3D world) {
        Session session = sessions.get(id);
        if (session != null && session.isOpen()) {
            try {
                // Send complete world data
                String worldJson = "{"
                        + "\"worldId\":\"" + id + "\","
                        + "\"generation\":" + world.getGeneration() + ","
                        + "\"aliveCells\":" + world.getAliveCells().size() + ","
                        + "\"width\":" + world.getWidth() + ","
                        + "\"height\":" + world.getHeight() + ","
                        + "\"depth\":" + world.getDepth() + ","
                        + "\"timestamp\":" + System.currentTimeMillis() + ","
                        + "\"cells\":" + serializeCells(world.getAliveCells()) + ""
                        + "}";
                session.getBasicRemote().sendText(worldJson);
            } catch (IOException e) {
                System.err.println("Error sending world data: " + e.getMessage());
            }
        }
    }

    private String serializeCells(java.util.List<Cell> cells) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            sb.append("{")
              .append("\"x\":").append(cell.getX()).append(",")
              .append("\"y\":").append(cell.getY()).append(",")
              .append("\"z\":").append(cell.getZ()).append(",")
              .append("\"alive\":").append(cell.isAlive()).append(",")
              .append("\"generation\":").append(cell.getGeneration())
              .append("}");
            if (i < cells.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
