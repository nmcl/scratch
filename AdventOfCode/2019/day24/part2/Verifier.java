import java.util.*;

public class Verifier
{
    public static final String EXAMPLE = "example.txt";

    public static final int ITERATIONS = 10;  // number of minutes to run
    public static final long BUGS_PRESENT = 99;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Grid theWorld = new Grid(EXAMPLE, _debug);

        System.out.println(theWorld);
        
        for (int i = 0; i < ITERATIONS; i++)
            theWorld.evolve();
        
        System.out.println("After evolving for "+ITERATIONS+" minutes ...");

        System.out.println(theWorld);

        long bugCount = theWorld.totalBugCount();

        if (bugCount == BUGS_PRESENT)  // could check the actual layers to be sure, but not for now ...
            return true;

        System.out.println("Wrong number of bugs: "+bugCount);

        return false;
    }

    private boolean _debug;
}