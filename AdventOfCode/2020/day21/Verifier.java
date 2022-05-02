import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Food> foods = Util.loadRules(EXAMPLE_FILE, _debug);

        if (_debug)
            System.out.println("Got back:\n"+foods);

        Babel fish = new Babel(_debug);

        fish.translate(foods);

        return false;
    }

    private boolean _debug;
}