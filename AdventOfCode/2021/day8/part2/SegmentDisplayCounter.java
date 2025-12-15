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
        
        // Read and decode all output values
        int sum = decodeAndSumOutputs(filename);
        
        if (sum >= 0) {
            System.out.println();
            System.out.println("=" .repeat(60));
            System.out.println("FINAL RESULT:");
            System.out.println("Sum of all output values: " + sum);
            System.out.println("=" .repeat(60));
        }
    }
    
    /**
     * Decodes all output values and returns their sum.
     */
    public static int decodeAndSumOutputs(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                System.err.println("Please create the file with your puzzle input.");
                return -1;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int totalSum = 0;
            int lineNum = 0;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                lineNum++;
                
                // Split by the pipe delimiter
                String[] parts = line.split("\\|");
                if (parts.length != 2) continue;
                
                String[] patterns = parts[0].trim().split("\\s+");
                String[] outputs = parts[1].trim().split("\\s+");
                
                // Decode this line
                Map<String, Integer> signalToDigit = deduceMapping(patterns);
                
                // Calculate the output value
                int outputValue = 0;
                for (String output : outputs) {
                    String normalized = normalizeSignal(output);
                    outputValue = outputValue * 10 + signalToDigit.get(normalized);
                }
                
                totalSum += outputValue;
                System.out.printf("Line %3d: %4d%n", lineNum, outputValue);
            }
            
            reader.close();
            return totalSum;
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Deduces the mapping from signal patterns to digits using logical deduction.
     */
    public static Map<String, Integer> deduceMapping(String[] patterns) {
        Map<String, Integer> signalToDigit = new HashMap<>();
        Map<Integer, String> digitToSignal = new HashMap<>();
        
        // Normalize all patterns (sort letters alphabetically)
        String[] normalized = new String[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            normalized[i] = normalizeSignal(patterns[i]);
        }
        
        // Step 1: Identify digits with unique segment counts
        for (String signal : normalized) {
            switch (signal.length()) {
                case 2: // Digit 1
                    signalToDigit.put(signal, 1);
                    digitToSignal.put(1, signal);
                    break;
                case 3: // Digit 7
                    signalToDigit.put(signal, 7);
                    digitToSignal.put(7, signal);
                    break;
                case 4: // Digit 4
                    signalToDigit.put(signal, 4);
                    digitToSignal.put(4, signal);
                    break;
                case 7: // Digit 8
                    signalToDigit.put(signal, 8);
                    digitToSignal.put(8, signal);
                    break;
            }
        }
        
        // Step 2: Deduce remaining digits using set operations
        String one = digitToSignal.get(1);
        String four = digitToSignal.get(4);
        String seven = digitToSignal.get(7);
        
        for (String signal : normalized) {
            if (signalToDigit.containsKey(signal)) continue; // Already identified
            
            int len = signal.length();
            int overlapWith1 = countOverlap(signal, one);
            int overlapWith4 = countOverlap(signal, four);
            
            if (len == 5) {
                // Could be 2, 3, or 5
                if (overlapWith1 == 2) {
                    signalToDigit.put(signal, 3); // 3 contains all of 1
                } else if (overlapWith4 == 3) {
                    signalToDigit.put(signal, 5); // 5 overlaps with 4 in 3 segments
                } else {
                    signalToDigit.put(signal, 2); // Must be 2
                }
            } else if (len == 6) {
                // Could be 0, 6, or 9
                if (overlapWith1 == 1) {
                    signalToDigit.put(signal, 6); // 6 only overlaps with 1 in 1 segment
                } else if (overlapWith4 == 4) {
                    signalToDigit.put(signal, 9); // 9 contains all of 4
                } else {
                    signalToDigit.put(signal, 0); // Must be 0
                }
            }
        }
        
        return signalToDigit;
    }
    
    /**
     * Normalizes a signal by sorting its characters alphabetically.
     */
    private static String normalizeSignal(String signal) {
        char[] chars = signal.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    /**
     * Counts how many characters in signal1 are also in signal2.
     */
    private static int countOverlap(String signal1, String signal2) {
        Set<Character> set2 = new HashSet<>();
        for (char c : signal2.toCharArray()) {
            set2.add(c);
        }
        
        int count = 0;
        for (char c : signal1.toCharArray()) {
            if (set2.contains(c)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Alternative method for testing with string input.
     */
    public static int decodeAndSumOutputsFromString(String input) {
        String[] lines = input.split("\n");
        int totalSum = 0;
        
        System.out.println("Analyzing " + lines.length + " lines:");
        System.out.println("=" .repeat(60));
        
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split("\\|");
            if (parts.length != 2) continue;
            
            String[] patterns = parts[0].trim().split("\\s+");
            String[] outputs = parts[1].trim().split("\\s+");
            
            Map<String, Integer> signalToDigit = deduceMapping(patterns);
            
            int outputValue = 0;
            StringBuilder decoded = new StringBuilder();
            for (String output : outputs) {
                String normalized = normalizeSignal(output);
                int digit = signalToDigit.get(normalized);
                outputValue = outputValue * 10 + digit;
                decoded.append(digit);
            }
            
            totalSum += outputValue;
            System.out.printf("Line %2d: %s = %d%n", i + 1, decoded, outputValue);
        }
        
        System.out.println("=" .repeat(60));
        return totalSum;
    }
}
