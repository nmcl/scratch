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
        int previousDepth = slidingWindowTotal(depths, 0);

        if (_debug)
            System.out.println(previousDepth+" (N/A - no previous measurement)");

        for (int i = 1; i < depths.size(); i++)
        {
            int value = slidingWindowTotal(depths, i);

            if (value != -1)
            {
                if (value > previousDepth)
                {
                    if (_debug)
                        System.out.println(value+" (increased)");

                    count++;
                }
                else
                {
                    if (value == previousDepth)
                    {
                        if (_debug)
                            System.out.println(value+" (no change)");
                    }
                    else
                    {
                        if (_debug)
                            System.out.println(value+" (decreased)");
                    }
                }

                previousDepth = value;
            }
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