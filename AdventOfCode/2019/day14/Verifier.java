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
        Vector<ChemicalQuantity> inventory = new Vector<ChemicalQuantity>();  // where we will store excess ChemicalQuantitys

        /*
         * Look at the reactions needed to create the amount
         * of fuel. Work backwards from there.
         */

        if (fuel != null)
        {
            System.out.println("**Fuel equation: "+fuel);

            int fuelNeeded = fuel.chemicalCreated().getAmount();
            boolean completed = false;
            Vector<ChemicalQuantity> ChemicalQuantitys = fuel.getChemicalQuantitys();    // maybe not all reactions loaded are needed

            oreNeeded = createNeededAmount(ChemicalQuantitys, reactions, inventory);

            System.out.println("**final ore needed: "+oreNeeded);
        }
        else
            System.out.println("Error! No fuel required?!");
        
        return false;
    }

    /*
     * Go through each ChemicalQuantity and try to create the
     * required amount, storing excess in the inventory.
     * So check the inventory first, of course.
     */

    private int createNeededAmount (Vector<ChemicalQuantity> ChemicalQuantitys, Vector<Reaction> reactions, Vector<ChemicalQuantity> inventory)
    {
        int oreNeeded = 0;
        Enumeration<ChemicalQuantity> iter = ChemicalQuantitys.elements();

        System.out.println("\n**scanning ChemicalQuantitys");

        while (iter.hasMoreElements())
        {
            ChemicalQuantity chemicalAndQuantity = iter.nextElement();
            System.out.println("**ChemicalQuantity "+chemicalAndQuantity);

            Reaction r = findReaction(chemicalAndQuantity.getChemical().getName(), reactions);
            System.out.println("**found needed reaction: "+r);

            int needed = chemicalAndQuantity.getAmount();
            System.out.println("**quantity needed "+needed);
            System.out.println("**quantity which would be created "+r.chemicalCreated().getAmount());

            /*
             * TODO we may need to run the same reaction multiple
             * times to get the desired amount.
             */

            if (r.isOre())
            {
                System.out.println("**reaction uses ORE");
                checkInventory(chemicalAndQuantity, inventory);
                oreNeeded += r.getChemicalQuantitys().elementAt(0).getAmount();

                System.out.println("**oreNeeded "+oreNeeded);
            }
            else
            {
                System.out.println("**reaction does NOT use ORE");
                checkInventory(chemicalAndQuantity, inventory);
                oreNeeded += createNeededAmount(r.getChemicalQuantitys(), reactions, inventory);
            }
        }

        return oreNeeded;
    }

    /*
     * Check to see if the chemical is present in the inventory with at least the amount
     * needed.
     */

    private boolean checkInventory (ChemicalQuantity needed, Vector<ChemicalQuantity> inventory)
    {
        System.out.println("**CHECKING INVENTORY**");

        boolean quantityPresent = false;
        int index = inventory.indexOf(needed);

        if (index != -1)
        {
            System.out.println("**PRESENT in inventory");

            ChemicalQuantity stored = inventory.elementAt(index);

            if (stored.getAmount() >= needed.getAmount())
                quantityPresent = true;
        }

        return quantityPresent;
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

    private boolean _debug;
    private Parser _theParser;
}