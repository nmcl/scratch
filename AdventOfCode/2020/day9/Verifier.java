import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_INPUT = "example.txt";

    public static final int EXAMPLE_PREAMBLE = 5;
    public static final int INVALID_NUMBER = 127;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Integer> data = Util.loadData(EXAMPLE_INPUT, _debug);
        XMAS parser = new XMAS(_debug);
        Vector<Integer> results = parser.validate(data, EXAMPLE_PREAMBLE);

        if (results.size() == 1)
        {
            if (results.elementAt(0) == INVALID_NUMBER)
                return true;
            else
                System.out.println("Wrong invalid entry: "+results.elementAt(0));
        }
        else
            System.out.println("Wrong number of invalid entries: "+results.size());

        return false;
    }

    private boolean _debug;
}