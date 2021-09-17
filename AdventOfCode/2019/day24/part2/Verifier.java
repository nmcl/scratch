import java.util.*;

public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final String DEPTH_BASE = "depth";

    public static final int ITERATIONS = 10;  // number of minutes to run
    public static final long BUGS_PRESENT = 99;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        return false;
    }

    private boolean _debug;
}