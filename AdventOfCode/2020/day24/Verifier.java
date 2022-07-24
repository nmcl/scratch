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
        Vector<String> lines = Util.readLines(EXAMPLE_DATA);
        
        return false;
    }

    private boolean _debug;
}