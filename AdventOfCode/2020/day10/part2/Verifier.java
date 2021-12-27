import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final long EXAMPLE1_ARRANGEMENTS = 8;

    public static final String EXAMPLE2_DATA = "example2.txt";
    public static final long EXAMPLE2_ARRANGEMENTS = 19208;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        if (check(EXAMPLE1_DATA, EXAMPLE1_ARRANGEMENTS))
            return check(EXAMPLE2_DATA, EXAMPLE2_ARRANGEMENTS);
        else
            return false;
    }

    private boolean check (String file, long combinations)
    {
        Vector<JoltageAdapter> adapters = Util.loadData(file, _debug);
        Device device = Util.largest(adapters, _debug);
        Connector con = new Connector(_debug);
        Vector<JoltageAdapter> sorted = con.connect(adapters);

        sorted.add(device.getAdapter());

        if (_debug)
        {
            for (int i = 0; i < sorted.size(); i++)
            {
                System.out.println("Adapter: "+sorted.elementAt(i).outputJoltage());
            }
        }

        long value = con.combinations(sorted);

        if (value == combinations)
            return true;
        else
            System.out.println("Wrong number of combinations for "+file+": "+value);

        return false;
    }

    private boolean _debug;
}