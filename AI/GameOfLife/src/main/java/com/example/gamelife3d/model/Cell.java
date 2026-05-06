package com.example.gamelife3d.model;

import java.util.Arrays;

public class Cell {
    private boolean alive;
    private int x, y, z;
    private int generation;
    private int[] neighbors;

    public Cell(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.alive = false;
        this.generation = 0;
        this.neighbors = new int[26]; // 3x3x3 - 1 (center) = 26 neighbors
        Arrays.fill(neighbors, 0);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int g)
    {
	generation = g;
    }

    public void incrementGeneration() {
        this.generation++;
    }

    public int[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(int[] neighbors) {
        this.neighbors = neighbors;
    }

    public int getNeighborCount() {
        int count = 0;
        for (int neighbor : neighbors) {
            if (neighbor > 0) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return String.format("Cell[x=%d, y=%d, z=%d, alive=%s, generation=%d]", x, y, z, alive, generation);
    }
}
