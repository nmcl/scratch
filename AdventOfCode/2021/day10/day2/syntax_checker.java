import java.util.*;
import java.io.*;

public class syntax_checker {
    
    // Points for each illegal character (Part 1)
    private static final Map<Character, Integer> ERROR_POINTS = new HashMap<Character, Integer>();
    
    // Points for autocomplete characters (Part 2)
    private static final Map<Character, Integer> AUTOCOMPLETE_POINTS = new HashMap<Character, Integer>();
    
    // Matching pairs
    private static final Map<Character, Character> PAIRS = new HashMap<Character, Character>();
    
    static {
        ERROR_POINTS.put(')', 3);
        ERROR_POINTS.put(']', 57);
        ERROR_POINTS.put('}', 1197);
        ERROR_POINTS.put('>', 25137);
        
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
        
        analyzeComplete(filename);
    }
    
    /**
     * Complete analysis: finds corrupted lines (Part 1) and incomplete lines (Part 2).
     */
    public static void analyzeComplete(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.err.println("Error: File '" + filename + "' not found!");
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;
            
            List<CorruptedLine> corruptedLines = new ArrayList<CorruptedLine>();
            List<IncompleteLine> incompleteLines = new ArrayList<IncompleteLine>();
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                lineNumber++;
                
                SyntaxResult result = checkLineSyntax(line);
                
                if (result.isCorrupted()) {
                    int points = ERROR_POINTS.get(result.illegalChar);
                    corruptedLines.add(new CorruptedLine(
                        lineNumber, 
                        line, 
                        result.illegalChar, 
                        result.expected,
                        result.position,
                        points
                    ));
                } else if (result.isIncomplete()) {
                    // Generate completion string
                    String completion = generateCompletion(result.remainingStack);
                    long score = calculateAutocompleteScore(completion);
                    incompleteLines.add(new IncompleteLine(
                        lineNumber,
                        line,
                        completion,
                        score
                    ));
                }
            }
            
            reader.close();
            
            // Display Part 1 results
            System.out.println("PART 1: SYNTAX ERROR CHECKING");
            System.out.println("======================================================================");
            System.out.println("Total lines analyzed: " + lineNumber);
            System.out.println("Corrupted lines: " + corruptedLines.size());
            System.out.println("Incomplete lines: " + incompleteLines.size());
            System.out.println();
            
            int totalErrorScore = 0;
            if (!corruptedLines.isEmpty()) {
                System.out.println("Corrupted lines:");
                for (CorruptedLine cl : corruptedLines) {
                    totalErrorScore += cl.points;
                    System.out.printf("  Line %d: Expected '%c', found '%c' (%d points)%n",
                        cl.lineNumber, cl.expected, cl.illegalChar, cl.points);
                }
                System.out.println();
                System.out.println("Total syntax error score: " + totalErrorScore);
            }
            
            // Display Part 2 results
            System.out.println();
            System.out.println("PART 2: AUTOCOMPLETE");
            System.out.println("======================================================================");
            
            if (!incompleteLines.isEmpty()) {
                System.out.println("Finding completion strings for incomplete lines...");
                System.out.println();
                
                // Collect all scores
                List<Long> scores = new ArrayList<Long>();
                for (IncompleteLine il : incompleteLines) {
                    scores.add(il.score);
                    System.out.printf("Line %d: Completion string = '%s', Score = %,d%n",
                        il.lineNumber, il.completion, il.score);
                }
                
                System.out.println();
                System.out.println("Sorting scores...");
                Collections.sort(scores);
                
                System.out.println("Sorted scores: " + scores);
                System.out.println();
                
                // Find and display the middle score
                int middleIndex = scores.size() / 2;
                long middleScore = scores.get(middleIndex);
                
                System.out.println("======================================================================");
                System.out.printf("Total incomplete lines: %d%n", scores.size());
                System.out.printf("Middle position: %d (index %d in sorted list)%n", 
                    middleIndex + 1, middleIndex);
                System.out.println();
                System.out.printf("*** MIDDLE SCORE: %,d ***%n", middleScore);
                System.out.println("======================================================================");
            } else {
                System.out.println("No incomplete lines found.");
            }
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
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
     * Checks a single line for syntax errors.
     */
    public static SyntaxResult checkLineSyntax(String line) {
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            // If it's an opening character, push to stack
            if (PAIRS.containsKey(c)) {
                stack.push(c);
            }
            // If it's a closing character
            else if (ERROR_POINTS.containsKey(c)) {
                if (stack.isEmpty()) {
                    return new SyntaxResult(true, c, '?', i);
                }
                
                char opening = stack.pop();
                char expectedClosing = PAIRS.get(opening);
                
                if (c != expectedClosing) {
                    return new SyntaxResult(true, c, expectedClosing, i);
                }
            }
        }
        
        // If stack is not empty, line is incomplete
        if (!stack.isEmpty()) {
            Stack<Character> copyStack = new Stack<Character>();
            copyStack.addAll(stack);
            return new SyntaxResult(false, copyStack);
        }
        
        // Line is valid
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
        
        SyntaxResult(boolean corrupted, char illegalChar, char expected, int position) {
            this.corrupted = corrupted;
            this.illegalChar = illegalChar;
            this.expected = expected;
            this.position = position;
            this.incomplete = false;
        }
        
        SyntaxResult(boolean incomplete, Stack<Character> remainingStack) {
            this.incomplete = incomplete;
            this.remainingStack = remainingStack;
            this.corrupted = false;
        }
        
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
     * Information about an incomplete line.
     */
    static class IncompleteLine {
        int lineNumber;
        String content;
        String completion;
        long score;
        
        IncompleteLine(int lineNumber, String content, String completion, long score) {
            this.lineNumber = lineNumber;
            this.content = content;
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
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        };
        
        List<Long> incompleteScores = new ArrayList<Long>();
        int errorScore = 0;
        
        System.out.println("PART 1: Corrupted lines");
        System.out.println("----------------------------------------------------------------------");
        
        for (int i = 0; i < examples.length; i++) {
            SyntaxResult result = checkLineSyntax(examples[i]);
            
            if (result.isCorrupted()) {
                int points = ERROR_POINTS.get(result.illegalChar);
                errorScore += points;
                System.out.printf("Line %d: Expected '%c', found '%c' (%d points)%n",
                    i + 1, result.expected, result.illegalChar, points);
            } else if (result.isIncomplete()) {
                String completion = generateCompletion(result.remainingStack);
                long score = calculateAutocompleteScore(completion);
                incompleteScores.add(score);
            }
        }
        
        System.out.println();
        System.out.println("Total error score: " + errorScore + " (expected: 26397)");
        
        System.out.println();
        System.out.println("PART 2: Incomplete lines");
        System.out.println("----------------------------------------------------------------------");
        
        Collections.sort(incompleteScores);
        long median = incompleteScores.get(incompleteScores.size() / 2);
        
        System.out.println("Completion scores: " + incompleteScores);
        System.out.println();
        System.out.println("Middle score: " + median + " (expected: 288957)");
        System.out.println("======================================================================");
    }
}
