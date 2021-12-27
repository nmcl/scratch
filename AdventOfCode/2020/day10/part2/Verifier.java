import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final int EXAMPLE1_DIFFERENCE1 = 7;
    public static final int EXAMPLE1_DIFFERENCE2 = 0;
    public static final int EXAMPLE1_DIFFERENCE3 = 5;

    public static final String EXAMPLE2_DATA = "example2.txt";
    public static final int EXAMPLE2_DIFFERENCE1 = 22;
    public static final int EXAMPLE2_DIFFERENCE2 = 0;
    public static final int EXAMPLE2_DIFFERENCE3 = 10;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        if (check(EXAMPLE1_DATA, EXAMPLE1_DIFFERENCE1, EXAMPLE1_DIFFERENCE2, EXAMPLE1_DIFFERENCE3))
            return check(EXAMPLE2_DATA, EXAMPLE2_DIFFERENCE1, EXAMPLE2_DIFFERENCE2, EXAMPLE2_DIFFERENCE3);
        else
            return false;
    }

    private boolean check (String file, int diff1, int diff2, int diff3)
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

        con.getDifferenceThree().add(device.getAdapter());

        if (con.getDifferenceOne().size() == diff1)
        {
            if (con.getDifferenceTwo().size() == diff2)
            {
                if (con.getDifferenceThree().size() == diff3)
                {
                    return true;
                }
                else
                    System.out.println("Wrong difference 3 for "+file+": "+con.getDifferenceThree().size());
            }
            else
                System.out.println("Wrong difference 2 for "+file+": "+con.getDifferenceTwo().size());
        }
        else
            System.out.println("Wrong difference 1 for "+file+": "+con.getDifferenceOne().size());

        return false;
    }

    private boolean _debug;
}