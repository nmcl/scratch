import java.util.*;

public class Util
{
    public static final int NEWLINE = 10;

    public static String outputToString (LinkedList<String> output)
    {
        ListIterator<String> iter = output.listIterator();
        String toReturn = "";

        while (iter.hasNext())
        {
            String s = iter.next();
            int v = Integer.parseInt(s);

            toReturn += (char) v;
        }

        return toReturn;
    }

    private Util ()
    {
    }
}