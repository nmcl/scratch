import java.util.*;

public class Rules
{
    public static final String BAGS = "bags";
    public static final String BAGS_CONTAINS = "bags contain ";
    public static final String NO_BAGS = "no other bags.";
    public static final String CONTAINS = "contain";
    public static final String SEPARATOR = ", ";
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

                if (_debug)
                    System.out.println("Loading bag "+theBag);

                inv.add(theBag);

                String remainder = line.substring(startIndex + BAGS_CONTAINS.length());

                if (_debug)
                    System.out.println("Remainder: "+remainder);

                if (!remainder.equals(NO_BAGS))
                {
                    boolean finished = false;

                    startIndex = 0;

                    while (!finished)
                    {
                        int endIndex = remainder.indexOf(Rules.SEPARATOR, startIndex);
                        Bag contains = null;

                        if (endIndex != -1)
                        {
                            contains = containsBag(remainder, startIndex, endIndex, false);

                            remainder = remainder.substring(endIndex +2);
                        }
                        else
                        {
                            endIndex = remainder.length() -1;

                            contains = containsBag(remainder, startIndex, endIndex, true);

                            finished = true;
                        }

                        if (_debug)
                            System.out.println("Contains "+contains);

                        System.out.println("adding "+contains+" and "+contains.quantity());

                        theBag.add(contains);
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

    private Bag containsBag (String data, int startIndex, int endIndex, boolean end)
    {
        System.out.println("data "+data);

        int spaceIndex = data.indexOf(Rules.SPACE, startIndex);
        String number = data.substring(startIndex, spaceIndex);
        int quantity = Integer.parseInt(number);

        String bagType = null;

        if (!end)
            bagType = data.substring(spaceIndex +1, data.indexOf(SEPARATOR));
        else
            bagType = data.substring(spaceIndex +1, endIndex);

        System.out.println("**bagType "+bagType);

        Bag containsBag = new Bag(bagType, quantity);

        return containsBag;
    }

    private boolean _debug;
}