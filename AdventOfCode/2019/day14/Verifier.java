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
        int oreNeeded = 0;

        if (_debug)
        {
            Enumeration<Reaction> iter = reactions.elements();

            while (iter.hasMoreElements())
                System.out.println(iter.nextElement());
        }

        Reaction fuel = findReaction(Chemical.FUEL, reactions);
        Vector<Reactant> inventory = new Vector<Reactant>();  // where we will store excess reactants

        /*
         * Look at the reactions needed to create the amount
         * of fuel. Work backwards from there.
         */

        if (fuel != null)
        {
            System.out.println("**Fuel equation: "+fuel);

            int fuelNeeded = fuel.chemicalCreated().getAmount();
            boolean completed = false;
            Vector<Reactant> reactants = fuel.getReactants();    // maybe not all reactions loaded are needed

            oreNeeded = createNeededAmount(reactants, reactions, inventory);

            System.out.println("**final ore needed: "+oreNeeded);
        }
        else
            System.out.println("Error! No fuel required?!");
        
        return false;
    }

    /*
     * Go through each reactant and try to create the
     * required amount, storing excess in the inventory.
     * So check the inventory first, of course.
     */

    private int createNeededAmount (Vector<Reactant> reactants, Vector<Reaction> reactions, Vector<Reactant> inventory)
    {
        int oreNeeded = 0;
        Enumeration<Reactant> iter = reactants.elements();

        System.out.println("\n**scanning reactants");

        while (iter.hasMoreElements())
        {
            Reactant react = iter.nextElement();
            System.out.println("**reactant "+react);

            Reaction r = findReaction(react.getChemical().getName(), reactions);
            System.out.println("**found needed reaction: "+r);

            int needed = react.getAmount();
            System.out.println("**quantity needed "+needed);
            System.out.println("**quantity created "+r.chemicalCreated().getAmount());

            if (r.isOre())
            {
                System.out.println("**reaction uses ORE");

                oreNeeded += r.getReactants().elementAt(0).getAmount();

                System.out.println("**oreNeeded "+oreNeeded);

                /*
                System.out.println("**creates "+r.chemicalCreated().getAmount());
                int foundInInventory = takeFromInventory(react, inventory, needed);

                System.out.println("**found "+foundInInventory+" in inventory");

                if (foundInInventory < needed)
                {
                    
                }*/
            }
            else
            {
                System.out.println("**reaction does NOT use ORE");

                oreNeeded += createNeededAmount(r.getReactants(), reactions, inventory);
            }
        }

        return oreNeeded;
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