import java.util.*;
import java.io.*;

public class NanoRefinery
{
    public NanoRefinery (Vector<Reaction> reactions, boolean debug)
    {
        _debug = debug;
        _reactions = reactions;
        _storage = new Vector<ChemicalQuantity>();  // where we will store excess ChemicalQuantities

        //if (_debug)
        {
            Enumeration<Reaction> iter = reactions.elements();

            while (iter.hasMoreElements())
                System.out.println(iter.nextElement());
        }
    }

    public final int oreNeeded ()
    {
        int amountOfOre = 0;
        Reaction fuel = findReaction(Chemical.FUEL);

        /*
         * Look at the reactions needed to create the amount
         * of fuel. Work backwards from there.
         */

        if (fuel != null)
        {
           // if (_debug)
                System.out.println("\nFuel equation: "+fuel);

            int fuelNeeded = fuel.chemicalCreated().getAmount();
            Vector<ChemicalQuantity> fuelChemicalQuantities = fuel.getChemicalQuantities();    // maybe not all reactions loaded are needed
            Enumeration<ChemicalQuantity> iter = fuelChemicalQuantities.elements();

            while (iter.hasMoreElements())
            {
                ChemicalQuantity reaction = iter.nextElement();
                System.out.println("\n**Working on: "+reaction);
                
                amountOfOre += createNeededAmount(reaction);

                System.out.println("**ORE used for "+reaction+" is "+amountOfOre);
            }
        }
        else
            System.out.println("Error! No fuel required?!");
        
        return amountOfOre;
    }

    /*
     * Go through each ChemicalQuantity and try to create the
     * required amount, storing excess in the inventory.
     * So check the inventory first, of course.
     */

    private int createNeededAmount (ChemicalQuantity theReaction)
    {
        int amountOfOre = 0;
        Reaction r = findReaction(theReaction.getChemical().getName());  // the reaction for the chemical needed
        int needed = theReaction.getAmount();  // the amount of chemical needed
        int numberOfTimesReactionNeedsToRun = theReaction.getAmount() / r.chemicalCreated().getAmount();

        if ((theReaction.getAmount() % r.chemicalCreated().getAmount()) != 0)
            numberOfTimesReactionNeedsToRun++;

        //if (_debug)
        {
            System.out.println("\ncreateNeededAmount of: "+theReaction.getChemical());
            System.out.println("Needed reaction: "+r);
            System.out.println("Quantity of "+theReaction.getChemical()+" needed: "+needed);
            System.out.println("Quantity which would be created from reaction: "+r.chemicalCreated().getAmount());
        }

        int amountCreated = 0;

        for (int i = 0; i < numberOfTimesReactionNeedsToRun; i++)
        {
            System.out.println("**Loop "+(i+1)+" for reaction "+theReaction);
            System.out.println("**amount of Ore at this stage "+amountOfOre);
            System.out.println("**amount created so far "+amountCreated);
            
            if (r.isOre())
            {
                /*
                 * If the chemical needed is ORE then we don't need
                 * to do anything special as it's always available at
                 * whatever quantity.
                 */

                //if (_debug)
                    System.out.println("Reaction uses ORE");
                
                int storage = checkInventory(theReaction);

                if (storage >= theReaction.getAmount())
                {
                    consumeFromInventory(theReaction);

                    amountCreated += theReaction.getAmount();
                }
                else
                {
                    System.out.println("**amountOfOre before: "+amountOfOre);

                    amountOfOre += r.getChemicalQuantities().elementAt(0).getAmount();
                    
                    System.out.println("**amountOfOre after: "+amountOfOre);

                    amountCreated += r.chemicalCreated().getAmount();

                    if (amountCreated > needed)
                    {
                        //if (_debug)
                            System.out.println("Created "+(amountCreated - needed)+" more "+r.chemicalCreated()+" than needed");

                        storeExcessChemical(theReaction.getChemical(), amountCreated - needed);

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

                //if (_debug)
                    System.out.println("**Reaction "+r+" does NOT use ORE.");

                int amountStored = checkInventory(theReaction);

                if (amountStored >= theReaction.getAmount())
                {
                    /*
                     * There's enough of the chemical in storage so just
                     * use that and we're done!
                     */

                    consumeFromInventory(theReaction);
                }
                else
                {
                    System.out.println("**Storage lookup failed for "+theReaction);

                    Vector<ChemicalQuantity> chemicalQuantities = r.getChemicalQuantities();
                    Enumeration<ChemicalQuantity> iter = chemicalQuantities.elements();

                    while (iter.hasMoreElements())
                    {
                        ChemicalQuantity reaction = iter.nextElement();
                        System.out.println("\n**Reaction to utilise "+reaction);

                        int oreReturned = createNeededAmount(reaction);

                        System.out.println("\n**oreReturned "+oreReturned+" for "+reaction);

                        amountOfOre += oreReturned;
                    }
                }
            }
        }

        System.out.println("\n**oreReturned "+amountOfOre+" for "+theReaction);

        return amountOfOre;
    }

    private void storeExcessChemical (Chemical chem, int amount)
    {
        if (_debug)
            System.out.println("Storing excess "+chem+" in the inventory.");

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
            System.out.println("Consuming chemical from storage.");

        boolean quantityPresent = false;
        int index = _storage.indexOf(needed);

        if (index != -1)
        {
            ChemicalQuantity stored = _storage.elementAt(index);
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

    private boolean _debug;
    private Vector<Reaction> _reactions;
    private Vector<ChemicalQuantity> _storage;
}
