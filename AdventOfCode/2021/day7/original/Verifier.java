import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Vector<Crab> crabs = Util.loadCrabs(EXAMPLE_DATA, false);

        for (int i = 0; i < crabs.size(); i++)
            System.out.print(crabs.elementAt(i).horizontalPosition()+",");

        System.out.println();

        return false;
    }

    private boolean _debug;
}