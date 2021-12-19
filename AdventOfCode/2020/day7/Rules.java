import java.util.*;

public class Rules
{
    public static final String BAGS = "bags";
    public static final String BAGS_CONTAINS = "bags contain";
    public static final String NO_BAGS = "no other bags";
    public static final String SEPARATOR = ",";
    public static final String TERMINATOR = ".";

    public Rules (boolean debug)
    {
        _debug = debug;
    }

    public Inventory parse (String dataFile)
    {
        Vector<String> rules = Util.loadData(dataFile, _debug);

        return null;
    }

    private boolean _debug;
}