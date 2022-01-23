import java.util.*;

public class MathsParser
{
    public MathsParser (boolean debug)
    {
        _debug = debug;
    }

    public long parse (Vector<String> data)
    {
        long finalResult = 0;

        for (int i = 0; i < data.size(); i++)
        {
            String currentLine = data.elementAt(i);
            long currentResult = 0;
            Stack<Character> operators = new Stack<Character>();
            Stack<Long> values = new Stack<Long>();
            char[] lineArray = currentLine.trim().toCharArray();
        }

        return finalResult;
    }

    private boolean _debug;
}