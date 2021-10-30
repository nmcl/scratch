import java.util.*;

public class Verifier
{
    public static final int EXAMPLE_INT1 = 1721;
    public static final int EXAMPLE_INT2 = 299;

    public static final Integer[] EXAMPLE_FIGURES = { 1721, 979, 366, 299, 675, 1456 };

    public Verifier (boolean debug)
    {
        _figures = new Vector<Integer>();
        _totaliser = new Total(_figures);
        _debug = debug;

        _figures.copyInto(EXAMPLE_FIGURES);
    }

    public boolean verify ()
    {
        return false;
    }

    private Vector<Integer> _figures;
    private Total _totaliser;
    private boolean _debug;
}