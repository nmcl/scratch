import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<JoltageAdapter> adapters = Util.loadData(EXAMPLE1_DATA, _debug);
        Device device = Util.largest(adapters, _debug);
        
        return false;
    }

    private boolean _debug;
}