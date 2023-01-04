import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int NUMBER_OF_FISH_18 = 26;
    public static final int NUMBER_OF_FISH_80 = 5934;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Vector<Integer> ages = Util.loadAges(EXAMPLE_DATA, _debug);
        Generator g = new Generator(_debug);
        Vector<Integer> fish = g.evolve(18, ages);

        if (fish.size() == NUMBER_OF_FISH_18)
        {
            ages = Util.loadAges(EXAMPLE_DATA, _debug);  // reset
            fish = g.evolve(80, ages);

            if (fish.size() == NUMBER_OF_FISH_80)
                return true;
            else
                System.out.println("Wrong number of fish after 80 days: "+fish.size());
        }
        else
            System.out.println("Wrong number of fish after 18 days: "+fish.size());

        return false;
    }

    private boolean _debug;
}