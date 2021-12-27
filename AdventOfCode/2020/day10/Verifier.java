import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final int EXAMPLE1_DIFFERENCE1 = 7;
    public static final int EXAMPLE1_DIFFERENCE2 = 0;
    public static final int EXAMPLE1_DIFFERENCE3 = 5;

    public static final String EXAMPLE2_DATA = "example2.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<JoltageAdapter> adapters = Util.loadData(EXAMPLE1_DATA, _debug);
        Device device = Util.largest(adapters, _debug);
        Connector con = new Connector(_debug);
        Vector<JoltageAdapter> sorted = con.connect(adapters);

        sorted.add(device.getAdapter());

        for (int i = 0; i < sorted.size(); i++)
        {
            System.out.println("Adapter: "+sorted.elementAt(i).outputJoltage());
        }

        con.getDifferenceThree().add(device.getAdapter());

        if (con.getDifferenceOne().size() == EXAMPLE1_DIFFERENCE1)
        {
            if (con.getDifferenceTwo().size() == EXAMPLE1_DIFFERENCE2)
            {
                if (con.getDifferenceThree().size() == EXAMPLE1_DIFFERENCE3)
                {
                    return true;
                }
                else
                    System.out.println("Wrong difference 3 for "+EXAMPLE1_DATA+": "+con.getDifferenceThree().size());
            }
            else
                System.out.println("Wrong difference 2 for "+EXAMPLE1_DATA+": "+con.getDifferenceTwo().size());
        }
        else
            System.out.println("Wrong difference 1 for "+EXAMPLE1_DATA+": "+con.getDifferenceOne().size());

        return false;
    }

    private boolean _debug;
}