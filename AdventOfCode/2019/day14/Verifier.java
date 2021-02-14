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

        System.out.println("\n**scanning reactions");

        while (iter.hasMoreElements())
        {
            ChemicalQuantity chemicalAndQuantity = iter.nextElement();  // the chemical needed and the amount of it
            System.out.println("\n**ChemicalQuantity "+chemicalAndQuantity);

            Reaction r = findReaction(chemicalAndQuantity.getChemical().getName(), reactions);  // the reaction for the chemical needed
            System.out.println("**found needed reaction: "+r);

            int needed = chemicalAndQuantity.getAmount();  // the amount of chemical needed
            System.out.println("**quantity of "+chemicalAndQuantity.getChemical()+" needed: "+needed);
            System.out.println("**quantity which would be created from reaction: "+r.chemicalCreated().getAmount());

            /*
             * TODO we may need to run the same reaction multiple
             * times to get the desired amount.
             */

            if (r.isOre())
            {
                /*
                 * If the chemical needed is ORE then we don't need
                 * to do anything special as it's always available at
                 * whatever quantity.
                 */

                System.out.println("**reaction uses ORE");

                oreNeeded += r.getChemicalQuantitys().elementAt(0).getAmount();

                System.out.println("**oreNeeded "+oreNeeded);
            }
            else
            {
                /*
                 * Not ORE so we need to create the chemical using
                 * the reaction. Check the inventory first and update
                 * if we have excess.
                 */

                System.out.println("**reaction does NOT use ORE");

                int amountStored = checkInventory(chemicalAndQuantity, inventory);

                if (amountStored >= chemicalAndQuantity.getAmount())
                {
                    /*
                     * There's enough of the chemical in storage so just
                     * use that and we're done!
                     */

                    consumeFromInventory(chemicalAndQuantity, inventory);
                }
                else
                {
                    /*
                     * Not enough in storage so create more, add it
                     * and check again.
                     */

                     addToInventory(chemicalAndQuantity, r, inventory);
                }

                oreNeeded += createNeededAmount(r.getChemicalQuantitys(), reactions, inventory);
            }
        }

        return oreNeeded;
    }

    /*
     * Add chemical amount to the inventory.
     */

    private void addToInventory (ChemicalQuantity needed, Reaction react, Vector<ChemicalQuantity> inventory)
    {
        System.out.println("**ADDING TO INVENTORY**");

        int index = inventory.indexOf(needed);

        if (index != -1)
        {
            System.out.println("**CHEMICAL ALREADY in inventory");

            ChemicalQuantity chem = inventory.elementAt(index);
            System.out.println("**present "+chem);
            int currentQuantityInInventory = chem.getAmount();

            chem.setAmount(currentQuantityInInventory + react.chemicalCreated().getAmount());
        }
        else
        {
            System.out.println("**CHEMICAL NOT in inventory");

            ChemicalQuantity chem = new ChemicalQuantity(react.chemicalCreated());
            System.out.println("**adding "+chem);
            inventory.add(chem);
        }
    }

    /*
     * Check to see if the chemical is present in the inventory with at least the amount
     * needed.
     */

    private int checkInventory (ChemicalQuantity needed, Vector<ChemicalQuantity> inventory)
    {
        System.out.println("**CHECKING INVENTORY**");

        int amountPresent = 0;
        int index = inventory.indexOf(needed);

        if (index != -1)
        {
            System.out.println("**PRESENT in inventory");

            return inventory.elementAt(index).getAmount();
        }
        else
            System.out.println("**NOT PRESENT in inventory");

        return amountPresent;
    }

    /*
     * Consume the chemical needed and the amount from the inventory.
     * We've already determined presence of the chemical and the amount
     * but double check!
     */

    private boolean consumeFromInventory (ChemicalQuantity needed, Vector<ChemicalQuantity> inventory)
    {
        System.out.println("**CONSUMING FROM INVENTORY**");

        boolean quantityPresent = false;
        int index = inventory.indexOf(needed);

        if (index != -1)
        {
            ChemicalQuantity stored = inventory.elementAt(index);
            int amountStored = stored.getAmount();

            if (amountStored >= needed.getAmount())
            {
                stored.setAmount(amountStored - needed.getAmount());

                quantityPresent = true;
            }
            else
                System.out.println("ERROR - Chemical suddenly no longer present at the required amount in the inventory!");
        }
        else
            System.out.println("ERROR - Chemical suddenly no longer present in the inventory!");

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