import java.util.*;

public class MathsParser
{
    public MathsParser (boolean debug)
    {
        _debug = debug;
    }

    /**
     * All numbers within expressions are single digit so we can continue to use them as
     * characters.
     */

    public long parse (Vector<String> data)
    {
        long finalResult = 0L;

        for (int i = 0; i < data.size(); i++)
        {
            String currentLine = data.elementAt(i);
            long currentResult = 0L;
            Stack<Character> operators = new Stack<Character>();
            Stack<Long> values = new Stack<Long>();
            char[] lineArray = currentLine.trim().replaceAll("\\s", "").toCharArray(); // remove all spaces
            char operator = Tokens.SPACE;

            for (int j = 0; j < lineArray.length; j++)
            {
                switch (lineArray[j])
                {
                    case Tokens.PLUS:
                    case Tokens.MULTIPLY:
                    {
                        operator = lineArray[j];
                    }
                    break;
                    case Tokens.OPEN_BRACE:
                    {
                        operators.push(operator);
                        operator = Tokens.SPACE;

                        values.push(currentResult);
                        currentResult = 0L;
                    }
                    break;
                    case Tokens.CLOSE_BRACE:
                    {
                        switch (operators.pop())
                        {
                            case Tokens.PLUS:
                            {
                                currentResult += values.pop();
                            }
                            break;
                            case Tokens.MULTIPLY:
                            {
                                currentResult *= values.pop();
                            }
                            break;
                            default:
                            {
                                currentResult = values.pop();
                            }
                            break;
                        }
                    }
                    break;
                    default:
                    {
                        switch (operator)
                        {
                            case Tokens.PLUS:
                            {
                                currentResult += Character.getNumericValue(lineArray[j]);
                            }
                            break;
                            case Tokens.MULTIPLY:
                            {
                                currentResult *= Character.getNumericValue(lineArray[j]);
                            }
                            break;
                            default:
                            {
                                currentResult = Character.getNumericValue(lineArray[j]);
                            }
                            break;
                        }
                    }
                }
            }

            finalResult += currentResult;
        }

        return finalResult;
    }

    private boolean _debug;
}