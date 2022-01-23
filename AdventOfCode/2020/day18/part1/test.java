import java.util.Scanner;
import java.util.Stack;

public class test {
  public static void main(String[] args) {
    long result = 0;

    try (Scanner in = new Scanner("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")) {
      while (in.hasNextLine()) {
        String line = in.nextLine();

        long lineResult = 0;
        char operand = '\0';

        Stack<Character> operandStack = new Stack<>();
        Stack<Long> valueStack = new Stack<>();

        for (char character : line.trim().toCharArray()) {
          switch (character) {
            case ' ':
              break;
            case '+':
            case '*':
              operand = character;
              break;
            case '(':
              operandStack.push(operand);
              operand = '\0';
              valueStack.push(lineResult);
              lineResult = 0;

              System.out.println("1 currentResult "+lineResult);

              break;
            case ')':
              switch (operandStack.pop()) {
                case '+':
                  lineResult += valueStack.pop();
                  System.out.println("2 currentResult "+lineResult);
                  break;
                case '*':
                  lineResult *= valueStack.pop();
                  System.out.println("3 currentResult "+lineResult);
                  break;
                default:
                  valueStack.pop();
                  lineResult = lineResult;

                  System.out.println("4 currentResult "+lineResult);
                  break;
              }
              break;
            default:
              switch (operand) {
                case '+':
                  lineResult += character - '0';
                  System.out.println("5 currentResult "+lineResult);
                  break;
                case '*':
                  lineResult *= character - '0';
                  System.out.println("6 currentResult "+lineResult);
                  break;
                default:
                  lineResult = character - '0';
                  System.out.println("7 currentResult "+lineResult);
                  break;
              }
              break;
          }
        }

        result += lineResult;
      }
    }

    System.out.println(result);
  }
}