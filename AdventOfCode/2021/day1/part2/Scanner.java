import java.util.*;

public class Scanner
{
    public static final int WINDOW_SIZE = 3;

    public Scanner (boolean debug)
    {
        _debug = debug;
    }

    public int increasingDepth (String dataFile)
    {
        Vector<Integer> depths = Util.loadData(dataFile, _debug);
        int count = 0;
        int previousDepth = depths.elementAt(0);

        if (_debug)
            System.out.println(previousDepth+" (N/A - no previous measurement)");

        for (int i = 1; i < depths.size(); i++)
        {
            if (depths.elementAt(i) > previousDepth)
            {
                if (_debug)
                    System.out.println(depths.elementAt(i)+" (increased)");

                count++;
            }
            else
            {
                if (_debug)
                    System.out.println(depths.elementAt(i)+" (decreased)");
            }

            previousDepth = depths.elementAt(i);
        }

        return count;
    }

    private int slidingWindowTotal (Vector<Integer> depths, int startIndex)
    {
        int total = 0;

        for (int i = 0; i < WINDOW_SIZE; i++)
        {
            if ((startIndex + i) < depths.size())
                total += depths.elementAt(startIndex + i);
            else
                return -1;  // not enough elements left
        }

        return total;
    }

    private boolean _debug;
}