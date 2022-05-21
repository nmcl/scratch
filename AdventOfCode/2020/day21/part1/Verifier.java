import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final int INGREDIENT_COUNT = 5;

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
        int occurrences = fish.translate(foods);

        if (occurrences == INGREDIENT_COUNT)
            return true;

        System.out.println("Wrong number of occurrences: "+occurrences);
        
        return false;
    }

    private boolean _debug;
}