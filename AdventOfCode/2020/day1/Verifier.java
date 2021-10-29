import java.util.*;

public class Verifier
{
    public static final int EXAMPLE_INT1 = 1721;
    public static final int EXAMPLE_INT2 = 299;

    public static final Integer[] EXAMPLE_FIGURES = { 1721, 979, 366, 299, 675, 1456 };

    public Verifier (boolean debug)
    {
        _figures = new Vector<Integer>();
        _debug = debug;

        _figures.copyInto(EXAMPLE_FIGURES);
    }

    public boolean verify ()
    {
        // use Total

        return false;
    }

    private Vector<Integer> _figures;
    private boolean _debug;
}