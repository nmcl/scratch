import java.util.*;

public class Util
{
    public static final int NEWLINE = 10;

    public String[] outputToString (LinkedList<String> output)
    {
        Iterator<String> iter = output.descendingIterator();

        while (iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }

    private Util ()
    {
    }
}