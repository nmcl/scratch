import java.util.*;

public class Util
{
    public static final int NEWLINE = 10;

    public static String outputToString (LinkedList<String> output)
    {
        Iterator<String> iter = output.descendingIterator();
        String toReturn = "";

        while (iter.hasNext())
        {
            int v = Integer.getInteger(iter.next());

            toReturn += (char) v;
        }

        return toReturn;
    }

    private Util ()
    {
    }
}