import java.util.*;

public class Rules
{
    public static final String BAGS = "bags";
    public static final String BAGS_CONTAINS = "bags contain";
    public static final String NO_BAGS = " no other bags";
    public static final String CONTAINS = "contain";
    public static final String SEPARATOR = ",";
    public static final String TERMINATOR = ".";
    public static final String SPACE = " ";

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
            int startIndex = line.indexOf(BAGS_CONTAINS);  // name of bag

            if (startIndex != -1)
            {
                String bagColour = line.substring(0, startIndex);
                Bag theBag = new Bag(bagColour);

                inv.add(theBag);

                String remainder = line.substring(startIndex + BAGS_CONTAINS.length());

                if (!remainder.equals(NO_BAGS))
                {
                    boolean finished = false;

                    startIndex = 0;

                    while (!finished)
                    {
                        int endIndex = remainder.indexOf(Rules.SEPARATOR, startIndex);

                        if (startIndex != -1)
                        {
                            int spaceIndex = remainder.indexOf(Rules.SPACE, startIndex);
                            String number = remainder.substring(startIndex, spaceIndex);
                        }
                    }
                }
            }
            else
            {
                System.out.println("Error, no bag name found!");

                error = true;
            }
        }

        return inv;
    }

    private boolean _debug;
}