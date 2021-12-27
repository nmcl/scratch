import java.util.*;

public class Connector
{
    public static final int SEAT_JOLTAGE = 0;
    public static final int JOLTAGE_RANGE = 3;

    public Connector (boolean debug)
    {
        _debug = debug;
    }

    public Vector<JoltageAdapter> connect (Vector<JoltageAdapter> adapters)
    {
        Vector<JoltageAdapter> toReturn = new Vector<JoltageAdapter>();
        int joltage = 0;

        while (adapters.size() > 0)
        {
            boolean found = false;

            for (int i = 0; (i < adapters.size()) && !found; i++)
            {

            }

        }

        return null;
    }

    private boolean _debug;
}