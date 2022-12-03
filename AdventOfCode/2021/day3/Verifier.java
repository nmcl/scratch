import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final String EXAMPLE_GAMMA = "10110";
    public static final String EXAMPLE_EPSILON = "01001";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> data = Util.loadData(EXAMPLE_DATA, _debug);
        String gamma = Gamma.getGamma(data);
        String epsilon = Epsilon.getEpsilon(data);

        System.out.println("Gamma: "+gamma);
        System.out.println("Epsilon: "+epsilon);
        
        return false;
    }

    private boolean _debug;
}