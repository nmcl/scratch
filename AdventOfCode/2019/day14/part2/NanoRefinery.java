import java.util.*;
import java.io.*;

public class NanoRefinery
{
    public NanoRefinery (Vector<Reaction> reactions, boolean debug)
    {
        _debug = debug;
        _reactions = reactions;
        _storage = new Vector<ChemicalQuantity>();  // where we will store excess ChemicalQuantities
        _amountOfOre = 0;

        if (_debug)
        {
            Enumeration<Reaction> iter = reactions.elements();

            while (iter.hasMoreElements())
                System.out.println(iter.nextElement());
        }
    }

    public final int oreNeeded (long amountOfFuel)
    {
        Reaction fuel = findReaction(Chemical.FUEL);

        /*
         * Look at the reactions needed to create the amount
         * of fuel. Work backwards from there.
         */

        if (fuel != null)
        {
            if (_debug)
                System.out.println("\nFuel equation: "+fuel);

            long fuelNeeded = ((amountOfFuel <= 0) ? fuel.chemicalCreated().getAmount() : amountOfFuel);
            long fuelCreated = 0;

            _amountOfOre = 0;

            while (fuelCreated < fuelNeeded)
            {
                Vector<ChemicalQuantity> fuelChemicalQuantities = fuel.getChemicalQuantities();    // maybe not all reactions loaded are needed
                Enumeration<ChemicalQuantity> iter = fuelChemicalQuantities.elements();                

                while (iter.hasMoreElements())
                {
                    ChemicalQuantity reaction = iter.nextElement();

                    if (_debug)
                        System.out.println("\nWorking on: "+reaction);
                    
                    synthesiseChemical(reaction);

                    if (_debug)
                    {
                        System.out.println("**---------------------------------------");
                        System.out.println("**COMPLETED ORE used for "+reaction+" is "+_amountOfOre);
                        System.out.println("**---------------------------------------");

                        printStorage();
                    }
                }

                fuelCreated += fuel.chemicalCreated().getAmount();
            }
        }
        else
            System.out.println("Error! No fuel required?!");
        
        return _amountOfOre;
    }

    /*
     * Go through each ChemicalQuantity and try to create the
     * required amount, storing excess in the inventory.
     * So check the inventory first, of course.
     */

    private void synthesiseChemical (ChemicalQuantity theReaction)
    {
        Reaction r = findReaction(theReaction.getChemical().getName());  // the reaction for the chemical needed
        int needed = theReaction.getAmount();  // the amount of chemical needed

        if (_debug)
        {
            System.out.println("\nSynthesising: "+theReaction.getChemical());
            System.out.println("Needed reaction: "+r);
            System.out.println("Quantity of "+theReaction.getChemical()+" needed: "+needed);
            System.out.println("Quantity which would be created from reaction: "+r.chemicalCreated().getAmount());
        }
        
        int amountCreated = checkInventory(theReaction);

        if (amountCreated >= needed)
            consumeFromInventory(theReaction);

        while (amountCreated < needed)
        {
            if (r.isOre())
            {
                /*
                 * If the chemical needed is ORE then we don't need
                 * to do anything special as it's always available at
                 * whatever quantity.
                 */

                if (_debug)
                    System.out.println("Reaction "+r+" uses ORE");
                
                int storage = checkInventory(theReaction);

                if (storage >= theReaction.getAmount())
                {
                    consumeFromInventory(theReaction);

                    amountCreated += theReaction.getAmount();
                }
                else
                {
                    _amountOfOre += r.getChemicalQuantities().elementAt(0).getAmount();
                    
                    amountCreated += r.chemicalCreated().getAmount();

                    storeChemical(theReaction.getChemical(), r.chemicalCreated().getAmount());

                    if (amountCreated >= needed)
                    {
                        if (_debug)
                            System.out.println("**Created "+(amountCreated - needed)+" more "+theReaction.getChemical()+" than needed");

                        consumeFromInventory(theReaction);

                        amountCreated = needed;
                    }
                }
            }
            else
            {
                /*
                 * Not ORE so we need to create the chemical using
                 * the reaction. Check the inventory first and update
                 * if we have excess.
                 */

                if (_debug)
                    System.out.println("**Reaction "+r+" does NOT use ORE.");

                int amountStored = checkInventory(theReaction);

                if (amountStored >= needed)
                {
                    /*
                     * There's enough of the chemical in storage so just
                     * use that and we're done!
                     */

                    consumeFromInventory(theReaction);

                    amountCreated = needed;
                }
                else
                {
                    /*
                     * Nothing in the store so create some.
                     */

                    Vector<ChemicalQuantity> chemicalQuantities = r.getChemicalQuantities();
                    Enumeration<ChemicalQuantity> iter = chemicalQuantities.elements();

                    while (iter.hasMoreElements())
                    {
                        ChemicalQuantity reaction = iter.nextElement();

                        synthesiseChemical(reaction);
                    }

                    if (_debug)
                        System.out.println("Created from combining other chemicals!");

                    storeChemical(r.chemicalCreated().getChemical(), r.chemicalCreated().getAmount());
                }
            }
        }
    }

    private void storeChemical (Chemical chem, int amount)
    {
        if (_debug)
            System.out.println("Storing "+amount+" of "+chem+" in the inventory.");

        ChemicalQuantity toStore = new ChemicalQuantity(chem, amount);
        int index = _storage.indexOf(toStore);

        if (index != -1)
        {
            ChemicalQuantity chemQ = _storage.elementAt(index);
            int currentQuantityInInventory = chemQ.getAmount();

            chemQ.setAmount(currentQuantityInInventory + amount);

            if (_debug)
                System.out.println("Inventory now storing: "+chemQ);
        }
        else
        {
            if (_debug)
                System.out.println("Adding to storage: "+toStore);

            _storage.add(toStore);
        }
    }

    /*
     * Check to see if the chemical is present in the inventory with at least the amount
     * needed.
     */

    private int checkInventory (ChemicalQuantity needed)
    {
        int amountPresent = 0;
        int index = _storage.indexOf(needed);

        if (index != -1)
            return _storage.elementAt(index).getAmount();

        return amountPresent;
    }

    /*
     * Consume the chemical needed and the amount from the inventory.
     * We've already determined presence of the chemical and the amount
     * but double check!
     */

    private boolean consumeFromInventory (ChemicalQuantity needed)
    {
        if (_debug)
            System.out.println("Consuming "+needed.getAmount()+" of chemical "+needed.getChemical()+" from storage.");

        boolean quantityPresent = false;
        int index = _storage.indexOf(needed);

        if (index != -1)
        {
            ChemicalQuantity stored = _storage.elementAt(index);
            int amountStored = stored.getAmount();

            if (amountStored >= needed.getAmount())
            {
                stored.setAmount(amountStored - needed.getAmount());

                if (stored.getAmount() == 0)
                    _storage.remove(stored);

                quantityPresent = true;
            }
            else
                System.out.println("ERROR - Chemical suddenly no longer present at the required amount in the inventory!");
        }
        else
            System.out.println("ERROR - Chemical suddenly no longer present in the inventory!");

        return quantityPresent;
    }

    private Reaction findReaction (String name)
    {
        Enumeration<Reaction> iter = _reactions.elements();

        while (iter.hasMoreElements())
        {
            Reaction r = iter.nextElement();

            if (r.chemicalCreated().getChemical().getName().equals(name))
                return r;
        }

        return null;
    }

    private void printStorage ()
    {
        Enumeration<ChemicalQuantity> iter = _storage.elements();

        System.out.println("\nStorage contents.");
        System.out.println("----------");
        while (iter.hasMoreElements())
            System.out.println(iter.nextElement());

        System.out.println("----------");
    }

    private boolean _debug;
    private Vector<Reaction> _reactions;
    private Vector<ChemicalQuantity> _storage;
    private int _amountOfOre;
}
