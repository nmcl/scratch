import java.util.*;

public class Scanner
{
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
            System.out.println("Initial depth: "+previousDepth);

        for (int i = 1; i < depths.size(); i++)
        {
            if (depths.elementAt(i) > previousDepth)
            {
                if (_debug)
                    System.out.println("New depth "+depths.elementAt(i)+" is greater than "+previousDepth);

                count++;
            }
            else
            {
                if (_debug)
                    System.out.println("New depth "+depths.elementAt(i)+" is less than "+previousDepth);
            }

            previousDepth = depths.elementAt(i);
        }

        return count;
    }

    private boolean _debug;
}