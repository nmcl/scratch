package com.example.gamelife3d.model;

import java.util.*;

public class World3D {
    private final int width;
    private final int height;
    private final int depth;
    private final Cell[][][] cells;
    private final Map<String, Cell> cellMap;
    private int generation;
    private final int[] neighborOffsets;

    public World3D(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.generation = 0;
        this.cells = new Cell[width][height][depth];
        this.cellMap = new HashMap<>();
        this.neighborOffsets = new int[]{
            -1, -1, -1, -1, -1, 0, -1, -1, 1,
            -1, 0, -1, -1, 0, 0, -1, 0, 1,
            -1, 1, -1, -1, 1, 0, -1, 1, 1,
            0, -1, -1, 0, -1, 0, 0, -1, 1,
            0, 0, -1, 0, 0, 0, 0, 0, 1,
            0, 1, -1, 0, 1, 0, 0, 1, 1,
            1, -1, -1, 1, -1, 0, 1, -1, 1,
            1, 0, -1, 1, 0, 0, 1, 0, 1,
            1, 1, -1, 1, 1, 0, 1, 1, 1
        };

        initializeWorld();
    }

    private void initializeWorld() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    Cell cell = new Cell(x, y, z);
                    cells[x][y][z] = cell;
                    cellMap.put(getCellKey(x, y, z), cell);
                }
            }
        }
    }

    public void setCellAlive(int x, int y, int z) {
        if (isValidPosition(x, y, z)) {
            cells[x][y][z].setAlive(true);
        }
    }

    public void setCellDead(int x, int y, int z) {
        if (isValidPosition(x, y, z)) {
            cells[x][y][z].setAlive(false);
        }
    }

    public boolean isCellAlive(int x, int y, int z) {
        if (isValidPosition(x, y, z)) {
            return cells[x][y][z].isAlive();
        }
        return false;
    }

    public void randomize(int density) {
        Random random = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    if (random.nextInt(100) < density) {
                        cells[x][y][z].setAlive(true);
                    } else {
                        cells[x][y][z].setAlive(false);
                    }
                }
            }
        }
    }

    public void update() {
        // Create a temporary array to store next state
        Cell[][][] nextCells = new Cell[width][height][depth];
        
        // Initialize next cells
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    nextCells[x][y][z] = new Cell(x, y, z);
                    nextCells[x][y][z].setAlive(cells[x][y][z].isAlive());
                    nextCells[x][y][z].setNeighbors(cells[x][y][z].getNeighbors().clone());
                }
            }
        }

        // Update each cell based on Game of Life rules
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    int aliveNeighbors = countAliveNeighbors(x, y, z);
                    boolean isAlive = cells[x][y][z].isAlive();
                    
                    // Apply Conway's Game of Life rules
                    if (isAlive && (aliveNeighbors < 2 || aliveNeighbors > 3)) {
                        nextCells[x][y][z].setAlive(false);
                    } else if (!isAlive && aliveNeighbors == 3) {
                        nextCells[x][y][z].setAlive(true);
                    }
                    
                    nextCells[x][y][z].incrementGeneration();
                }
            }
        }

        // Copy next state back to current cells
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    cells[x][y][z].setAlive(nextCells[x][y][z].isAlive());
                    cells[x][y][z].setNeighbors(nextCells[x][y][z].getNeighbors());
                    cells[x][y][z].incrementGeneration();
                }
            }
        }

        generation++;
    }

    private int countAliveNeighbors(int x, int y, int z) {
        int count = 0;
        int[] neighbors = new int[26];
        
        for (int i = 0; i < neighborOffsets.length; i += 3) {
            int nx = x + neighborOffsets[i];
            int ny = y + neighborOffsets[i + 1];
            int nz = z + neighborOffsets[i + 2];
            
            if (isValidPosition(nx, ny, nz) && cells[nx][ny][nz].isAlive()) {
                count++;
                neighbors[i/3] = 1;
            } else {
                neighbors[i/3] = 0;
            }
        }
        
        cells[x][y][z].setNeighbors(neighbors);
        return count;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public int getGeneration() {
        return generation;
    }

    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    cells[x][y][z].setAlive(false);
                    cells[x][y][z].setGeneration(0);
                }
            }
        }
        generation = 0;
    }

    public List<Cell> getAliveCells() {
        List<Cell> aliveCells = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    if (cells[x][y][z].isAlive()) {
                        aliveCells.add(cells[x][y][z]);
                    }
                }
            }
        }
        return aliveCells;
    }

    private boolean isValidPosition(int x, int y, int z) {
        return x >= 0 && x < width && y >= 0 && y < height && z >= 0 && z < depth;
    }

    private String getCellKey(int x, int y, int z) {
        return String.format("%d,%d,%d", x, y, z);
    }
}
