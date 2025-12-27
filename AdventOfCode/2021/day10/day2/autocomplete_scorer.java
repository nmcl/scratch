import java.util.*;
import java.io.*;

public class autocomplete_scorer {
    
    // Points for autocomplete characters
    private static final Map<Character, Integer> AUTOCOMPLETE_POINTS = new HashMap<Character, Integer>();
    
    // Matching pairs
    private static final Map<Character, Character> PAIRS = new HashMap<Character, Character>();
    
    static {
        AUTOCOMPLETE_POINTS.put(')', 1);
        AUTOCOMPLETE_POINTS.put(']', 2);
        AUTOCOMPLETE_POINTS.put('}', 3);
        AUTOCOMPLETE_POINTS.put('>', 4);
        
        PAIRS.put('(', ')');
        PAIRS.put('[', ']');
        PAIRS.put('{', '}');
        PAIRS.put('<', '>');
    }
    
    public static void main(String[] args) {
        String filename = "data.txt";
        
        if (args.length > 0) {
            filename = args[0];
        }
        
        System.out.println("Reading navigation subsystem from file: " + filename);
        System.out.println();
        
        analyzeIncompleteLines(filename);
    }
    
    /**
     * Analyzes incomplete lines and finds the middle score.
     */
    public static void analyzeIncompleteLines(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;
            
            List<CompletionResult> completions = new ArrayList<CompletionResult>();
            int corruptedCount = 0;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                lineNumber++;
                
                // Check if line is corrupted or incomplete
                LineStatus status = analyzeLine(line);
                
                if (status.isCorrupted()) {
                    corruptedCount++;
                } else if (status.isIncomplete()) {
                    // Generate completion string
                    String completion = generateCompletion(status.openingChars);
                    long score = calculateAutocompleteScore(completion);
                    completions.add(new CompletionResult(lineNumber, line, completion, score));
                }
            }
            
            reader.close();
            
            // Display results
            System.out.println("AUTOCOMPLETE ANALYSIS");
            System.out.println("======================================================================");
            System.out.println("Total lines analyzed: " + lineNumber);
            System.out.println("Corrupted lines (discarded): " + corruptedCount);
            System.out.println("Incomplete lines: " + completions.size());
            System.out.println();
            
            if (completions.isEmpty()) {
                System.out.println("No incomplete lines found.");
                return;
            }
            
            System.out.println("Completion strings and scores:");
            System.out.println("----------------------------------------------------------------------");
            
            for (CompletionResult cr : completions) {
                System.out.printf("Line %d:%n", cr.lineNumber);
                System.out.println("  Completion: " + cr.completion);
                System.out.printf("  Score: %,d%n", cr.score);
                System.out.println();
            }
            
            // Sort scores
            List<Long> scores = new ArrayList<Long>();
            for (CompletionResult cr : completions) {
                scores.add(cr.score);
            }
            Collections.sort(scores);
            
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Sorted scores: " + scores);
            System.out.println();
            
            // Find middle score
            int middleIndex = scores.size() / 2;
            long middleScore = scores.get(middleIndex);
            
            System.out.println("======================================================================");
            System.out.printf("Total incomplete lines: %d%n", scores.size());
            System.out.printf("Middle position: %d (index %d in sorted list)%n", 
                middleIndex + 1, middleIndex);
            System.out.println();
            System.out.printf("*** MIDDLE SCORE: %,d ***%n", middleScore);
            System.out.println("======================================================================");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Analyzes a line to determine if it's corrupted, incomplete, or valid.
     */
    public static LineStatus analyzeLine(String line) {
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            // If it's an opening character, push to stack
            if (PAIRS.containsKey(c)) {
                stack.push(c);
            }
            // If it's a closing character
            else if (AUTOCOMPLETE_POINTS.containsKey(c)) {
                if (stack.isEmpty()) {
                    // Unexpected closing character - corrupted
                    return new LineStatus(true, false, null);
                }
                
                char opening = stack.pop();
                char expectedClosing = PAIRS.get(opening);
                
                if (c != expectedClosing) {
                    // Mismatch - corrupted
                    return new LineStatus(true, false, null);
                }
            }
        }
        
        // If stack is not empty, line is incomplete
        if (!stack.isEmpty()) {
            return new LineStatus(false, true, stack);
        }
        
        // Line is valid (complete and correct)
        return new LineStatus(false, false, null);
    }
    
    /**
     * Generates completion string from remaining opening characters.
     */
    public static String generateCompletion(Stack<Character> stack) {
        StringBuilder completion = new StringBuilder();
        
        // Pop all remaining opening characters and add their closing pairs
        while (!stack.isEmpty()) {
            char opening = stack.pop();
            char closing = PAIRS.get(opening);
            completion.append(closing);
        }
        
        return completion.toString();
    }
    
    /**
     * Calculates autocomplete score for a completion string.
     * Start with 0, then for each character: multiply by 5 and add character's point value.
     */
    public static long calculateAutocompleteScore(String completion) {
        long score = 0;
        
        for (int i = 0; i < completion.length(); i++) {
            char c = completion.charAt(i);
            score = score * 5 + AUTOCOMPLETE_POINTS.get(c);
        }
        
        return score;
    }
    
    /**
     * Status of a line after analysis.
     */
    static class LineStatus {
        boolean corrupted;
        boolean incomplete;
        Stack<Character> openingChars;
        
        LineStatus(boolean corrupted, boolean incomplete, Stack<Character> openingChars) {
            this.corrupted = corrupted;
            this.incomplete = incomplete;
            if (openingChars != null) {
                this.openingChars = new Stack<Character>();
                this.openingChars.addAll(openingChars);
            }
        }
        
        boolean isCorrupted() {
            return corrupted;
        }
        
        boolean isIncomplete() {
            return incomplete;
        }
    }
    
    /**
     * Result of completing an incomplete line.
     */
    static class CompletionResult {
        int lineNumber;
        String originalLine;
        String completion;
        long score;
        
        CompletionResult(int lineNumber, String originalLine, String completion, long score) {
            this.lineNumber = lineNumber;
            this.originalLine = originalLine;
            this.completion = completion;
            this.score = score;
        }
    }
    
    /**
     * Test with example data.
     */
    public static void testWithExample() {
        System.out.println("Testing with example data:");
        System.out.println();
        
        String[] examples = {
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",      // corrupted
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",         // corrupted
            "[{[{({}]{}}([{[{{{}}([]",        // corrupted
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",         // corrupted
            "<{([([[(<>()){}]>(<<{{",         // corrupted
            "<{([{{}}[<[[[<>{}]]]>[]]"
        };
        
        List<Long> scores = new ArrayList<Long>();
        int corruptedCount = 0;
        
        System.out.println("Analyzing example lines:");
        System.out.println("----------------------------------------------------------------------");
        
        for (int i = 0; i < examples.length; i++) {
            LineStatus status = analyzeLine(examples[i]);
            
            if (status.isCorrupted()) {
                corruptedCount++;
                System.out.printf("Line %d: CORRUPTED (discarded)%n", i + 1);
            } else if (status.isIncomplete()) {
                String completion = generateCompletion(status.openingChars);
                long score = calculateAutocompleteScore(completion);
                scores.add(score);
                System.out.printf("Line %d: INCOMPLETE%n", i + 1);
                System.out.println("  Completion: " + completion);
                System.out.printf("  Score: %,d%n", score);
            }
        }
        
        System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Corrupted lines discarded: " + corruptedCount);
        System.out.println("Incomplete lines found: " + scores.size());
        System.out.println();
        
        Collections.sort(scores);
        System.out.println("Sorted scores: " + scores);
        System.out.println();
        
        long middleScore = scores.get(scores.size() / 2);
        System.out.println("Middle score: " + middleScore);
        System.out.println("Expected: 288957");
        System.out.println("======================================================================");
    }
}
