import java.util.*;
import java.io.*;

public class dumbo_octopus {
    
    private static final int GRID_SIZE = 10;
    private static final int STEPS = 100;
    
    public static void main(String[] args) {
        String filename = "data.txt";
        
        if (args.length > 0) {
            filename = args[0];
        }
        
        System.out.println("Reading dumbo octopus energy levels from: " + filename);
        System.out.println();
        
        simulateOctopuses(filename);
    }
    
    /**
     * Simulates the octopus energy levels and flashes.
     */
    public static void simulateOctopuses(String filename) {
        try {
            // Read the grid from file
            int[][] grid = readGrid(filename);
            
            if (grid == null) {
                return;
            }
            
            System.out.println("Before any steps:");
            printGrid(grid);
            System.out.println();
            
            // Simulate 100 steps
            int totalFlashes = 0;
            
            for (int step = 1; step <= STEPS; step++) {
                int flashes = simulateStep(grid);
                totalFlashes += flashes;
                
                // Print grid every 10 steps for verification
                if (step <= 10 || step % 10 == 0) {
                    System.out.println("After step " + step + ":");
                    printGrid(grid);
                    System.out.println("Flashes this step: " + flashes);
                    System.out.println("Total flashes so far: " + totalFlashes);
                    System.out.println();
                }
            }
            
            System.out.println("======================================================================");
            System.out.println("SIMULATION COMPLETE");
            System.out.println("======================================================================");
            System.out.printf("Total steps simulated: %d%n", STEPS);
            System.out.printf("Total flashes after %d steps: %d%n", STEPS, totalFlashes);
            System.out.println("======================================================================");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Reads the octopus grid from a file.
     */
    public static int[][] readGrid(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                return null;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<String>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            reader.close();
            
            if (lines.isEmpty()) {
                System.err.println("Error: File is empty!");
                return null;
            }
            
            // Convert to 2D array
            int rows = lines.size();
            int cols = lines.get(0).length();
            int[][] grid = new int[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                String rowData = lines.get(i);
                for (int j = 0; j < rowData.length(); j++) {
                    grid[i][j] = rowData.charAt(j) - '0';
                }
            }
            
            return grid;
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Simulates a single step and returns the number of flashes.
     */
    public static int simulateStep(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Track which octopuses have flashed this step
        boolean[][] hasFlashed = new boolean[rows][cols];
        
        // Step 1: Increase all energy levels by 1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j]++;
            }
        }
        
        // Step 2: Process flashes (octopuses with energy > 9)
        boolean flashOccurred = true;
        while (flashOccurred) {
            flashOccurred = false;
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // If energy > 9 and hasn't flashed yet
                    if (grid[i][j] > 9 && !hasFlashed[i][j]) {
                        flash(grid, hasFlashed, i, j);
                        flashOccurred = true;
                    }
                }
            }
        }
        
        // Step 3: Reset all flashed octopuses to 0 and count flashes
        int flashCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasFlashed[i][j]) {
                    grid[i][j] = 0;
                    flashCount++;
                }
            }
        }
        
        return flashCount;
    }
    
    /**
     * Flashes an octopus and increases energy of all adjacent octopuses.
     */
    public static void flash(int[][] grid, boolean[][] hasFlashed, int row, int col) {
        hasFlashed[row][col] = true;
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // All 8 directions: up, down, left, right, and 4 diagonals
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},  // top-left, top, top-right
            {0, -1},           {0, 1},    // left, right
            {1, -1},  {1, 0},  {1, 1}     // bottom-left, bottom, bottom-right
        };
        
        // Increase energy of all adjacent octopuses
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            // Check bounds
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                // Only increase if it hasn't flashed yet
                if (!hasFlashed[newRow][newCol]) {
                    grid[newRow][newCol]++;
                }
            }
        }
    }
    
    /**
     * Prints the grid.
     */
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * Test with example data.
     */
    public static void testWithExample() {
        System.out.println("Testing with example data:");
        System.out.println();
        
        int[][] grid = {
            {5, 4, 8, 3, 1, 4, 3, 2, 2, 3},
            {2, 7, 4, 5, 8, 5, 4, 7, 1, 1},
            {5, 2, 6, 4, 5, 5, 6, 1, 7, 3},
            {6, 1, 4, 1, 3, 3, 6, 1, 4, 6},
            {6, 3, 5, 7, 3, 8, 5, 4, 7, 8},
            {4, 1, 6, 7, 5, 2, 4, 6, 4, 5},
            {2, 1, 7, 6, 8, 4, 1, 7, 2, 1},
            {6, 8, 8, 2, 8, 8, 1, 1, 3, 4},
            {4, 8, 4, 6, 8, 4, 8, 5, 5, 4},
            {5, 2, 8, 3, 7, 5, 1, 5, 2, 6}
        };
        
        System.out.println("Before any steps:");
        printGrid(grid);
        System.out.println();
        
        int totalFlashes = 0;
        
        for (int step = 1; step <= STEPS; step++) {
            int flashes = simulateStep(grid);
            totalFlashes += flashes;
            
            if (step <= 10 || step % 10 == 0) {
                System.out.println("After step " + step + ":");
                printGrid(grid);
                System.out.println("Total flashes: " + totalFlashes);
                System.out.println();
            }
        }
        
        System.out.println("======================================================================");
        System.out.println("Expected total flashes after 100 steps: 1656");
        System.out.println("Actual total flashes after 100 steps: " + totalFlashes);
        System.out.println("======================================================================");
    }
}