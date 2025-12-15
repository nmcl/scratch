import java.util.*;
import java.io.*;

public class SegmentDisplayCounter {
    
    public static void main(String[] args) {
        // Check if filename is provided as command line argument
        String filename = "data.txt";
        
        if (args.length > 0) {
            filename = args[0];
        }
        
        System.out.println("Reading from file: " + filename);
        System.out.println();
        
        // Read and count from file
        int count = countEasyDigitsFromFile(filename);
        
        if (count >= 0) {
            System.out.println();
            System.out.println("FINAL RESULT:");
            System.out.println("Total count of digits 1, 4, 7, and 8: " + count);
            
            // Show detailed breakdown
            System.out.println();
            Map<String, Integer> breakdown = countAllDigitOccurrencesFromFile(filename);
            if (breakdown != null) {
                System.out.println("Breakdown by digit:");
                System.out.println("  Digit 1: " + breakdown.get("1") + " occurrences");
                System.out.println("  Digit 4: " + breakdown.get("4") + " occurrences");
                System.out.println("  Digit 7: " + breakdown.get("7") + " occurrences");
                System.out.println("  Digit 8: " + breakdown.get("8") + " occurrences");
            }
        }
    }
    
    /**
     * Counts occurrences of digits 1, 4, 7, and 8 in output values.
     * These digits have unique segment counts:
     * - 1 uses 2 segments
     * - 4 uses 4 segments
     * - 7 uses 3 segments
     * - 8 uses 7 segments
     */
    public static int countEasyDigits(String input) {
        int totalCount = 0;
        String[] lines = input.split("\n");
        
        System.out.println("Analyzing " + lines.length + " lines:");
        System.out.println("=" .repeat(60));
        
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;
            
            // Split by the pipe delimiter
            String[] parts = line.split("\\|");
            if (parts.length != 2) continue;
            
            // Get the output values (after the pipe)
            String outputSection = parts[1].trim();
            String[] outputValues = outputSection.split("\\s+");
            
            int lineCount = 0;
            StringBuilder lineResult = new StringBuilder();
            lineResult.append(String.format("Line %2d: ", i + 1));
            
            // Count digits with unique segment counts
            for (String signal : outputValues) {
                int length = signal.length();
                boolean isEasyDigit = false;
                String digit = "";
                
                switch (length) {
                    case 2: // Digit 1
                        isEasyDigit = true;
                        digit = "1";
                        break;
                    case 3: // Digit 7
                        isEasyDigit = true;
                        digit = "7";
                        break;
                    case 4: // Digit 4
                        isEasyDigit = true;
                        digit = "4";
                        break;
                    case 7: // Digit 8
                        isEasyDigit = true;
                        digit = "8";
                        break;
                }
                
                if (isEasyDigit) {
                    lineCount++;
                    lineResult.append(String.format("[%s=%s] ", signal, digit));
                }
            }
            
            totalCount += lineCount;
            lineResult.append(String.format("(count: %d)", lineCount));
            System.out.println(lineResult);
        }
        
        System.out.println("=" .repeat(60));
        return totalCount;
    }
    
    /**
     * Reads input from a file and counts easy digits.
     */
    public static int countEasyDigitsFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                System.err.println("Please create the file with your puzzle input.");
                return -1;
            }
            
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            
            return countEasyDigits(content.toString());
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Returns digit breakdown from file.
     */
    public static Map<String, Integer> countAllDigitOccurrencesFromFile(String filename) {
        try {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            
            return countAllDigitOccurrences(content.toString());
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Alternative method with detailed breakdown.
     */
    public static Map<String, Integer> countAllDigitOccurrences(String input) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("1", 0);
        counts.put("4", 0);
        counts.put("7", 0);
        counts.put("8", 0);
        
        String[] lines = input.split("\n");
        
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length != 2) continue;
            
            String[] outputValues = parts[1].trim().split("\\s+");
            
            for (String signal : outputValues) {
                int length = signal.length();
                
                switch (length) {
                    case 2:
                        counts.put("1", counts.get("1") + 1);
                        break;
                    case 3:
                        counts.put("7", counts.get("7") + 1);
                        break;
                    case 4:
                        counts.put("4", counts.get("4") + 1);
                        break;
                    case 7:
                        counts.put("8", counts.get("8") + 1);
                        break;
                }
            }
        }
        
        return counts;
    }
}