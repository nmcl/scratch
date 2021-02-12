import java.util.*;
import java.io.*;

public class Verifier
{
    public static final String EXAMPLE1_FILE = "example1.txt";
    public static final int TOTAL_ORE_1 = 31;
    public static final String EXAMPLE2_FILE = "example2.txt";
    public static final int TOTAL_ORE_2 = 165;
    public static final String EXAMPLE3_FILE = "example3.txt";
    public static final int TOTAL_ORE_3 = 13312;
    public static final String EXAMPLE4_FILE = "example4.txt";
    public static final int TOTAL_ORE_4 = 180697;
    public static final String EXAMPLE5_FILE = "example5.txt";
    public static final int TOTAL_ORE_5 = 2210736;

    public Verifier (boolean debug)
    {
        _debug = debug;

        _theParser = new Parser(debug);
    }

    /*
     * 10 ORE => 10 A
     * 1 ORE => 1 B
     * 7 A, 1 B => 1 C
     * 7 A, 1 C => 1 D
     * 7 A, 1 D => 1 E
     * 7 A, 1 E => 1 FUEL
     */

    public final boolean verify ()
    {
        Vector<Reaction> reactions = _theParser.loadData(EXAMPLE1_FILE);

        //if (_debug)
        {
            Enumeration<Reaction> iter = reactions.elements();

            while (iter.hasMoreElements())
                System.out.println(iter.nextElement());
        }

        HashMap<Chemical, Integer> inventory = new HashMap<Chemical, Integer>();
        Reaction fuel = findReaction(Chemical.FUEL, reactions);
        Vector<Reactant> inventory = new Vector<Reactant>();

        if (fuel != null)
        {
            boolean completed = false;
            int fuelNeeded = fuel.chemicalCreated().getAmount();
            Vector<Reactant> reactants = fuel.getReactants();

            /*
             * Go through each reactant and try to create the
             * required amount, storing excess in the inventory.
             * So check the inventory first, of course.
             */
        }
        else
            System.out.println("Error! No fuel required?!");
        
        return false;
    }

    private Reaction findReaction (String name, Vector<Reaction> reactions)
    {
        Enumeration<Reaction> iter = reactions.elements();

        while (iter.hasMoreElements())
        {
            Reaction r = iter.nextElement();

            if (r.chemicalCreated().getChemical().getName().equals(name))
                return r;
        }

        return null;
    }

    private int takeFromInventory (Reactant name, Vector<Reactant> inventory, int amountNeeded)
    {
        Enumeration<Reactant> iter = inventory.elements();
        int amount = -1;

        while (iter.hasMoreElements())
        {
            Reactant r = iter.nextElement();

            if (r.getChemical().equals(name.getChemical()))
            {
                int amountFound = r.getAmount();

                if (amountFound > 0)
                {
                    if (amountFound >= amountNeeded)
                        amount = amountNeeded;
                    else
                        amount = amountNeeded - amountFound;

                    r.setAmount(amountFound - amount);
                }
            }
        }

        return amount;
    }

    private boolean _debug;
    private Parser _theParser;
}