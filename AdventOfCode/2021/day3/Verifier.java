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
        Vector<String> data = Util.loadData(EXAMPLE_DATA, _debug);
        String gamma = Gamma.getGamma(data);
        String epsilon = Epsilon.getEpsilon(data);
        
        return false;
    }

    private boolean _debug;
}