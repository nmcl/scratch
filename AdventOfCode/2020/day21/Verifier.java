import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final String[] ALLERGEN_FREE = { "kfcds", "nhms", "sbzzf", "trh" };
    public static final int INGREDIENT_COUNT_1 = 5;
    public static final int INGREDIENT_COUNT_2 = 2;

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

        System.out.println("here");
        
        return false;
    }

    private boolean _debug;
}