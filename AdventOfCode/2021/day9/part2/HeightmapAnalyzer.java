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
        analyzeHeightmapComplete(filename);
    }
    
    /**
     * Complete analysis: finds low points, basins, and calculates both parts.
     */
    public static void analyzeHeightmapComplete(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                System.err.println("Please create the file with your heightmap data.");
                return;
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
                return;
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
            
            // Part 1: Find low points and calculate risk
            System.out.println("PART 1: Low Points Analysis");
            System.out.println("=" .repeat(60));
            int totalRisk = findLowPointsAndCalculateRisk(heightmap);
            System.out.println("Sum of risk levels: " + totalRisk);
            System.out.println();
            
            // Part 2: Find basins and calculate product of three largest
            System.out.println("PART 2: Basin Analysis");
            System.out.println("=" .repeat(60));
            int product = findBasinsAndCalculateProduct(heightmap);
            System.out.println();
            System.out.println("=" .repeat(60));
            System.out.println("Product of three largest basins: " + product);
            System.out.println("=" .repeat(60));
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Finds all low points in the heightmap and calculates total risk.
     */
    public static int findLowPointsAndCalculateRisk(int[][] heightmap) {
        int rows = heightmap.length;
        int cols = heightmap[0].length;
        int totalRisk = 0;
        
        List<LowPoint> lowPoints = new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLowPoint(heightmap, i, j)) {
                    int height = heightmap[i][j];
                    int risk = height + 1;
                    totalRisk += risk;
                    lowPoints.add(new LowPoint(i, j, height, risk));
                }
            }
        }
        
        System.out.println("Low points found: " + lowPoints.size());
        System.out.println();
        
        if (!lowPoints.isEmpty()) {
            System.out.printf("%-10s %-15s %-10s %-10s%n", "Position", "Coordinates", "Height", "Risk");
            System.out.println("-" .repeat(50));
            
            for (int i = 0; i < lowPoints.size(); i++) {
                LowPoint lp = lowPoints.get(i);
                System.out.printf("%-10s (%2d, %2d)       %-10d %-10d%n", 
                    "#" + (i + 1), lp.row, lp.col, lp.height, lp.risk);
            }
        }
        
        return totalRisk;
    }
    
    /**
     * Finds all basins and calculates the product of the three largest.
     */
    public static int findBasinsAndCalculateProduct(int[][] heightmap) {
        int rows = heightmap.length;
        int cols = heightmap[0].length;
        
        // Find all low points first
        List<Point> lowPoints = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isLowPoint(heightmap, i, j)) {
                    lowPoints.add(new Point(i, j));
                }
            }
        }
        
        // Calculate basin size for each low point
        List<Basin> basins = new ArrayList<>();
        for (Point lowPoint : lowPoints) {
            int size = calculateBasinSize(heightmap, lowPoint.row, lowPoint.col);
            basins.add(new Basin(lowPoint.row, lowPoint.col, size));
        }
        
        // Sort basins by size (descending)
        basins.sort((a, b) -> Integer.compare(b.size, a.size));
        
        System.out.println("Total basins found: " + basins.size());
        System.out.println();
        
        System.out.println("All basins (sorted by size):");
        System.out.printf("%-10s %-20s %-10s%n", "Rank", "Low Point", "Size");
        System.out.println("-" .repeat(50));
        
        for (int i = 0; i < basins.size(); i++) {
            Basin basin = basins.get(i);
            String marker = (i < 3) ? " *** LARGEST ***" : "";
            System.out.printf("%-10s (%2d, %2d)          %-10d%s%n", 
                "#" + (i + 1), basin.row, basin.col, basin.size, marker);
        }
        
        // Calculate product of three largest
        if (basins.size() >= 3) {
            int product = basins.get(0).size * basins.get(1).size * basins.get(2).size;
            System.out.println();
            System.out.printf("Three largest basins: %d × %d × %d%n", 
                basins.get(0).size, basins.get(1).size, basins.get(2).size);
            return product;
        }
        
        return 0;
    }
    
    /**
     * Calculates the size of a basin using flood fill (BFS).
     */
    public static int calculateBasinSize(int[][] heightmap, int startRow, int startCol) {
        int rows = heightmap.length;
        int cols = heightmap[0].length;
        
        boolean[][] visited = new boolean[rows][cols];
        Queue<Point> queue = new LinkedList<>();
        
        queue.add(new Point(startRow, startCol));
        visited[startRow][startCol] = true;
        int size = 0;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            size++;
            
            // Check all four adjacent directions
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                
                // Check bounds
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    // Check if not visited, not a 9, and not already in basin
                    if (!visited[newRow][newCol] && heightmap[newRow][newCol] != 9) {
                        visited[newRow][newCol] = true;
                        queue.add(new Point(newRow, newCol));
                    }
                }
            }
        }
        
        return size;
    }
    
    /**
     * Checks if a position is a low point (lower than all adjacent positions).
     */
    public static boolean isLowPoint(int[][] heightmap, int row, int col) {
        int rows = heightmap.length;
        int cols = heightmap[0].length;
        int currentHeight = heightmap[row][col];
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (heightmap[newRow][newCol] <= currentHeight) {
                    return false;
                }
            }
        }
        
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
     * Helper class to store point coordinates.
     */
    static class Point {
        int row;
        int col;
        
        Point(int row, int col) {
            this.row = row;
            this.col = col;
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
     * Helper class to store basin information.
     */
    static class Basin {
        int row;
        int col;
        int size;
        
        Basin(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
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
        
        System.out.println("PART 1: Low Points Analysis");
        System.out.println("=" .repeat(60));
        int risk = findLowPointsAndCalculateRisk(exampleMap);
        System.out.println("Sum of risk levels: " + risk);
        System.out.println("Expected: 15");
        System.out.println();
        
        System.out.println("PART 2: Basin Analysis");
        System.out.println("=" .repeat(60));
        int product = findBasinsAndCalculateProduct(exampleMap);
        System.out.println();
        System.out.println("=" .repeat(60));
        System.out.println("Product of three largest basins: " + product);
        System.out.println("Expected: 1134 (9 × 9 × 14)");
        System.out.println("=" .repeat(60));
    }
}