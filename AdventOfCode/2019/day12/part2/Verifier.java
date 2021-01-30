import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public static final int EXAMPLE1_REPEAT_TIME = 2772;
    public static final long EXAMPLE2_REPEAT_TIME = 4686774924L;

    public Verifier (boolean debug)
    {
        _activator = null;
        _debug = debug;
    }

    public final boolean verify ()
    {
        return verifyExample1() || verifyExample2();
    }

    // we don't check all the data, just a sample.

    private final boolean verifyExample1 ()
    {
        boolean result = false;
        
        _activator = new MoonSystem(EXAMPLE1_DATA, _debug);

        long[] periods = _activator.periodicity();

        for (int i = 0; i < periods.length; i++)
            System.out.println("**got back "+periods[i]+" for axis "+i);

        long val = lcm(lcm(periods[0], periods[1]), periods[2]);

        System.out.println("**lcm "+val);

        return result;
    }

    private final boolean verifyExample2 ()
    {
        boolean result = false;
        
        _activator = new MoonSystem(EXAMPLE2_DATA, _debug);

        long[] periods = _activator.periodicity();

        for (int i = 0; i < periods.length; i++)
            System.out.println("**got back "+periods[i]+" for axis "+i);
        
        long val = lcm(lcm(periods[0], periods[1]), periods[2]);

        System.out.println("**lcm "+val);

        return result;
    }

    public long lcm (long i, long y)
    {
        int n;
        int x;
        long s = 1;
        long t = 1;

        for (n = 1;; n++)
        {
            s = i * n;

            for (x = 1; t < s; x++) 
            {
                t = y * x;
            }

            if (s == t)
                break;
        }

        return s;
    }

    private MoonSystem _activator;
    private boolean _debug;
}