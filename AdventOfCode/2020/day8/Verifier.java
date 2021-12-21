import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<OpCode> instructions = Util.loadData(EXAMPLE_DATA, _debug);
        
        return false;
    }

    private boolean _debug;
}