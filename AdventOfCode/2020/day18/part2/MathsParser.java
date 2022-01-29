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
     * 
     * Handle different precedence  to part 1.
     */

    public long parse (Vector<String> data)
    {
        long finalResult = 0L;

        /*
         * Since + has precedence, we need to unwind the entire
         * equation and compute those values first before doing *
         */

        for (int i = 0; i < data.size(); i++)
        {
            String currentLine = data.elementAt(i);
            char[] lineArray = currentLine.trim().replaceAll("\\s", "").toCharArray(); // remove all spaces
            Vector<Character> unrolled = new Vector<Character>();
            Stack<Character> nested = new Stack<Character>();
            Stack<Long> values = new Stack<Long>();

            for (int j = 0; j < lineArray.length; j++)
            {
                switch (lineArray[j])
                {
                    case Tokens.OPEN_BRACE:
                    {
                        nested.push(lineArray[j]);
                    }
                    break;
                    case Tokens.CLOSE_BRACE:
                    {
                        boolean done = false;

                        while (!done && !nested.isEmpty())
                        {
                            Character c = nested.pop();

                            if (c != Tokens.OPEN_BRACE)
                                unrolled.add(c);
                            else
                                done = true;
                        }
                    }
                    break;
                    case Tokens.PLUS:
                    {
                        boolean done = false;

                        while (!done && !nested.isEmpty())
                        {
                            Character c = nested.pop();

                            if (c == Tokens.OPEN_BRACE)
                                done = true;
                            else
                            {
                                if (c == Tokens.PLUS)
                                    unrolled.add(c);
                            }
                        }

                        nested.push(lineArray[j]);
                    }
                    break;
                    case Tokens.MULTIPLY:
                    {
                        boolean done = false;

                        while (!done && !nested.isEmpty())
                        {

                        }
                    }
                    break;
                    default:
                    {
                        unrolled.add(lineArray[j]);
                    }
                    break;
                }
            }
        }

        return finalResult;
    }

    private boolean _debug;
}