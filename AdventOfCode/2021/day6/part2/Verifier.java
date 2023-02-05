import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final long NUMBER_OF_FISH_256 = 26984457539L;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Vector<Integer> ages = Util.loadAges(EXAMPLE_DATA, _debug);
        Generator g = new Generator(_debug);
        long[] fish = g.evolve(256, ages);
        long total = 0;

        for (int i = 0; i < fish.length; i++)
            total += fish[i];

        if (total == NUMBER_OF_FISH_256)
            return true;
        else
            System.out.println("Wrong number of fish after 256 days: "+total);

        return false;
    }

    private boolean _debug;
}