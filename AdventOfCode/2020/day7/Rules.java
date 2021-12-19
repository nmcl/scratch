import java.util.*;

public class Rules
{
    public static final String BAGS = "bags";
    public static final String BAGS_CONTAINS = "bags contain";
    public static final String NO_BAGS = " no other bags";
    public static final String CONTAINS = "contain";
    public static final String SEPARATOR = ",";
    public static final String TERMINATOR = ".";

    public Rules (boolean debug)
    {
        _debug = debug;
    }

    public Inventory parse (String dataFile)
    {
        Vector<String> rules = Util.loadData(dataFile, _debug);
        Inventory inv = new Inventory(_debug);
        Enumeration<String> iter = rules.elements();
        boolean error = false;

        while (iter.hasMoreElements() && !error)
        {
            String line = iter.nextElement();
            int index = line.indexOf(BAGS_CONTAINS);  // name of bag

            if (index != -1)
            {
                String bagColour = line.substring(0, index);
                Bag theBag = new Bag(bagColour);

                inv.add(theBag);

                String contents = line.substring(index + BAGS_CONTAINS.length());
                
                if (!contents.equals(NO_BAGS))
                {
                    index = 0;

                    while (index < contents.length())
                    {

                    }
                }
            }
            else
                error = true;
        }

        return inv;
    }

    private boolean _debug;
}