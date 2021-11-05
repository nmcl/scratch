import java.util.*;

public class Verifier
{
    public static final int EXAMPLE_INT1 = 979;
    public static final int EXAMPLE_INT2 = 366;
    public static final int EXAMPLE_INT3 = 675;

    public static final int EXAMPLE_TOTAL = 2020;

    public static final Integer[] EXAMPLE_FIGURES = { 1721, 979, 366, 299, 675, 1456 };

    public Verifier (boolean debug)
    {
        _figures = new Vector<Integer>();
        
        for (int i = 0; i < EXAMPLE_FIGURES.length; i++)
            _figures.add(EXAMPLE_FIGURES[i]);

        _totaliser = new Total(_figures, debug);
        _debug = debug;
    }

    public boolean verify ()
    {
        Integer[] results = _totaliser.sum(EXAMPLE_TOTAL);

        if (results != null)
        {
            if (results.length == 2)
            {
                if (((results[0] == EXAMPLE_INT1) && (results[1] == EXAMPLE_INT2)) ||
                    ((results[0] == EXAMPLE_INT2) && (results[1] == EXAMPLE_INT1)))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private Vector<Integer> _figures;
    private Total _totaliser;
    private boolean _debug;
}