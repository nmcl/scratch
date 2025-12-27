import java.util.*;
import java.io.*;

public class SyntaxChecker {
    
    // Points for each illegal character
    private static final Map<Character, Integer> ERROR_POINTS = new HashMap<>();
    
    static {
        ERROR_POINTS.put(')', 3);
        ERROR_POINTS.put(']', 57);
        ERROR_POINTS.put('}', 1197);
        ERROR_POINTS.put('>', 25137);
    }
    
    // Matching pairs
    private static final Map<Character, Character> PAIRS = new HashMap<>();
    
    static {
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
        
        analyzeSyntax(filename);
    }
    
    /**
     * Analyzes the syntax of all lines in the file.
     */
    public static void analyzeSyntax(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;
            int totalScore = 0;
            
            List<CorruptedLine> corruptedLines = new ArrayList<>();
            List<Integer> incompleteLines = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                lineNumber++;
                
                SyntaxResult result = checkLineSyntax(line);
                
                if (result.isCorrupted()) {
                    int points = ERROR_POINTS.get(result.illegalChar);
                    totalScore += points;
                    corruptedLines.add(new CorruptedLine(
                        lineNumber, 
                        line, 
                        result.illegalChar, 
                        result.expected,
                        result.position,
                        points
                    ));
                } else if (result.isIncomplete()) {
                    incompleteLines.add(lineNumber);
                }
            }
            
            reader.close();
            
            // Display results
            System.out.println("Analysis Complete");
            System.out.println("=" .repeat(70));
            System.out.println("Total lines analyzed: " + lineNumber);
            System.out.println("Corrupted lines: " + corruptedLines.size());
            System.out.println("Incomplete lines: " + incompleteLines.size());
            System.out.println();
            
            if (!corruptedLines.isEmpty()) {
                System.out.println("CORRUPTED LINES:");
                System.out.println("-" .repeat(70));
                
                for (CorruptedLine cl : corruptedLines) {
                    System.out.println("Line " + cl.lineNumber + ":");
                    System.out.println("  " + cl.content);
                    System.out.println("  " + " ".repeat(cl.position) + "^");
                    System.out.printf("  Expected '%c', but found '%c' instead%n", 
                        cl.expected, cl.illegalChar);
                    System.out.printf("  Points: %d%n", cl.points);
                    System.out.println();
                }
                
                System.out.println("=" .repeat(70));
                System.out.println("TOTAL SYNTAX ERROR SCORE: " + totalScore);
                System.out.println("=" .repeat(70));
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Checks a single line for syntax errors.
     * Returns a SyntaxResult indicating if the line is valid, corrupted, or incomplete.
     */
    public static SyntaxResult checkLineSyntax(String line) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            // If it's an opening character, push to stack
            if (PAIRS.containsKey(c)) {
                stack.push(c);
            }
            // If it's a closing character
            else if (ERROR_POINTS.containsKey(c)) {
                if (stack.isEmpty()) {
                    // Unexpected closing character with no opening
                    return new SyntaxResult(true, c, '?', i);
                }
                
                char opening = stack.pop();
                char expectedClosing = PAIRS.get(opening);
                
                // Check if this is the correct closing character
                if (c != expectedClosing) {
                    // Found a mismatch - corrupted line
                    return new SyntaxResult(true, c, expectedClosing, i);
                }
            }
        }
        
        // If stack is not empty, line is incomplete
        if (!stack.isEmpty()) {
            return new SyntaxResult(false, stack);
        }
        
        // Line is valid (complete and correct)
        return new SyntaxResult();
    }
    
    /**
     * Result of syntax checking a line.
     */
    static class SyntaxResult {
        boolean corrupted;
        boolean incomplete;
        char illegalChar;
        char expected;
        int position;
        Stack<Character> remainingStack;
        
        // Constructor for corrupted line
        SyntaxResult(boolean corrupted, char illegalChar, char expected, int position) {
            this.corrupted = corrupted;
            this.illegalChar = illegalChar;
            this.expected = expected;
            this.position = position;
            this.incomplete = false;
        }
        
        // Constructor for incomplete line
        SyntaxResult(boolean incomplete, Stack<Character> remainingStack) {
            this.incomplete = incomplete;
            this.remainingStack = remainingStack;
            this.corrupted = false;
        }
        
        // Constructor for valid line
        SyntaxResult() {
            this.corrupted = false;
            this.incomplete = false;
        }
        
        boolean isCorrupted() {
            return corrupted;
        }
        
        boolean isIncomplete() {
            return incomplete;
        }
    }
    
    /**
     * Information about a corrupted line.
     */
    static class CorruptedLine {
        int lineNumber;
        String content;
        char illegalChar;
        char expected;
        int position;
        int points;
        
        CorruptedLine(int lineNumber, String content, char illegalChar, 
                     char expected, int position, int points) {
            this.lineNumber = lineNumber;
            this.content = content;
            this.illegalChar = illegalChar;
            this.expected = expected;
            this.position = position;
            this.points = points;
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
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        };
        
        int totalScore = 0;
        List<String> corruptedResults = new ArrayList<>();
        
        for (int i = 0; i < examples.length; i++) {
            SyntaxResult result = checkLineSyntax(examples[i]);
            
            if (result.isCorrupted()) {
                int points = ERROR_POINTS.get(result.illegalChar);
                totalScore += points;
                String msg = String.format("Line %d: Expected '%c', but found '%c' instead (%d points)",
                    i + 1, result.expected, result.illegalChar, points);
                corruptedResults.add(msg);
            }
        }
        
        System.out.println("Corrupted lines found: " + corruptedResults.size());
        System.out.println();
        
        for (String msg : corruptedResults) {
            System.out.println(msg);
        }
        
        System.out.println();
        System.out.println("=" .repeat(70));
        System.out.println("Total syntax error score: " + totalScore);
        System.out.println("Expected: 26397");
        System.out.println("=" .repeat(70));
    }
}