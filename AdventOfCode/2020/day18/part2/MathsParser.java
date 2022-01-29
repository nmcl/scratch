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
            Stack<Character> operators = new Stack<Character>();
            Stack<Character> nested = new Stack<Character>();
            Stack<Long> values = new Stack<Long>();

            for (int j = 0; j < lineArray.length; j++)
            {
                switch (lineArray[j])
                {
                    case Tokens.OPEN_BRACE:
                    {
                        nested.push(lineArray[j]));
                    }
                    break;
                    default:
                    {

                    }
                    break;
                }
            }
        }

        return finalResult;
    }

    private boolean _debug;
}