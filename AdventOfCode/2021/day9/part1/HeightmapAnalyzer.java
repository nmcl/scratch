import java.util.*;
import java.io.*;

public class HeightmapAnalyzer {
    
    public static void main(String[] args) {
        // Check if filename is provided as command line argument
        String filename = "input.txt";
        
        if (args.length > 0) {
            filename = args[0];
        }
        
        System.out.println("Reading heightmap from file: " + filename);
        System.out.println();
        
        // Analyze the heightmap
        int riskSum = analyzeHeightmap(filename);
        
        if (riskSum >= 0) {
            System.out.println();
            System.out.println("=" .repeat(60));
            System.out.println("FINAL RESULT:");
            System.out.println("Sum of risk levels of all low points: " + riskSum);
            System.out.println("=" .repeat(60));
        }
    }
    
    /**
     * Analyzes a heightmap from a file and returns the sum of risk levels.
     */
    public static int analyzeHeightmap(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                System.err.println("Please create the file with your heightmap data.");
                return -1;
            }
            
            // Read the heightmap into a 2D array
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
                return -1;
            }
            
            // Convert to 2D array
            int rows = lines.size();
            int cols = lines.get(0).length();
            int[][] heightmap = new int[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                String rowData = lines.get(i);
                for (int j = 0; j < rowData.length(); j++) {
                    heightmap[i][j] = rowData.charAt(j) - '0';
                }
            }
            
            // Display the heightmap
            System.out.println("Heightmap (" + rows + " x " + cols + "):");
            displayHeightmap(heightmap);
            System.out.println();
            
            // Find low points and calculate risk
            return findLowPointsAndCalculateRisk(heightmap);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Finds all low points in the heightmap and calculates total risk.
     */
    public static int findLowPointsAndCalculateRisk(int[][] heightmap) {
        int rows = heightmap.length;
        int cols = heightmap[0].length;
        int totalRisk = 0;
        int lowPointCount = 0;
        
        System.out.println("Finding low points...");
        System.out.println();
        
        List<LowPoint> lowPoints = new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLowPoint(heightmap, i, j)) {
                    int height = heightmap[i][j];
                    int risk = height + 1;
                    totalRisk += risk;
                    lowPointCount++;
                    
                    lowPoints.add(new LowPoint(i, j, height, risk));
                }
            }
        }
        
        // Display results
        System.out.println("Low points found: " + lowPointCount);
        System.out.println();
        
        if (!lowPoints.isEmpty()) {
            System.out.println("Details:");
            System.out.println("-" .repeat(60));
            System.out.printf("%-10s %-15s %-10s %-10s%n", "Position", "Coordinates", "Height", "Risk");
            System.out.println("-" .repeat(60));
            
            for (int i = 0; i < lowPoints.size(); i++) {
                LowPoint lp = lowPoints.get(i);
                System.out.printf("%-10s (%2d, %2d)       %-10d %-10d%n", 
                    "#" + (i + 1), lp.row, lp.col, lp.height, lp.risk);
            }
            System.out.println("-" .repeat(60));
        }
        
        return totalRisk;
    }
    
    /**
     * Checks if a position is a low point (lower than all adjacent positions).
     */
    public static boolean isLowPoint(int[][] heightmap, int row, int col) {
        int rows = heightmap.length;
        int cols = heightmap[0].length;
        int currentHeight = heightmap[row][col];
        
        // Check all four adjacent directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            // Check if the adjacent position is within bounds
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                // If any adjacent position is lower or equal, it's not a low point
                if (heightmap[newRow][newCol] <= currentHeight) {
                    return false;
                }
            }
        }
        
        // If we get here, all adjacent positions are higher
        return true;
    }
    
    /**
     * Displays the heightmap in a readable format.
     */
    public static void displayHeightmap(int[][] heightmap) {
        for (int[] row : heightmap) {
            for (int height : row) {
                System.out.print(height);
            }
            System.out.println();
        }
    }
    
    /**
     * Helper class to store low point information.
     */
    static class LowPoint {
        int row;
        int col;
        int height;
        int risk;
        
        LowPoint(int row, int col, int height, int risk) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.risk = risk;
        }
    }
    
    /**
     * Test method with example data.
     */
    public static void testWithExample() {
        System.out.println("Testing with example data:");
        System.out.println();
        
        int[][] exampleMap = {
            {2, 1, 9, 9, 9, 4, 3, 2, 1, 0},
            {3, 9, 8, 7, 8, 9, 4, 9, 2, 1},
            {9, 8, 5, 6, 7, 8, 9, 8, 9, 2},
            {8, 7, 6, 7, 8, 9, 6, 7, 8, 9},
            {9, 8, 9, 9, 9, 6, 5, 6, 7, 8}
        };
        
        System.out.println("Example Heightmap:");
        displayHeightmap(exampleMap);
        System.out.println();
        
        int risk = findLowPointsAndCalculateRisk(exampleMap);
        
        System.out.println();
        System.out.println("=" .repeat(60));
        System.out.println("Expected result: 15");
        System.out.println("Actual result:   " + risk);
        System.out.println("=" .repeat(60));
    }
}