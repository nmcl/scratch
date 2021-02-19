import java.util.*;
import java.io.*;

public class NanoFactory
{
    public NanoFactory (Vector<Reaction> reactions, boolean debug)
    {
        _debug = debug;
        _reactions = reactions;
        _storage = new Vector<ChemicalQuantity>();  // where we will store excess ChemicalQuantities

        if (_debug)
        {
            Enumeration<Reaction> iter = reactions.elements();

            while (iter.hasMoreElements())
                System.out.println(iter.nextElement());
        }
    }

    /*
     * 10 ORE => 10 A
     * 1 ORE => 1 B
     * 7 A, 1 B => 1 C
     * 7 A, 1 C => 1 D
     * 7 A, 1 D => 1 E
     * 7 A, 1 E => 1 FUEL
     */

    public final int oreNeeded ()
    {
        int oreNeeded = 0;
        Reaction fuel = findReaction(Chemical.FUEL);

        /*
         * Look at the reactions needed to create the amount
         * of fuel. Work backwards from there.
         */

        if (fuel != null)
        {
            if (_debug)
                System.out.println("\nFuel equation: "+fuel);

            int fuelNeeded = fuel.chemicalCreated().getAmount();
            boolean completed = false;
            Vector<ChemicalQuantity> ChemicalQuantities = fuel.getChemicalQuantities();    // maybe not all reactions loaded are needed

            oreNeeded = createNeededAmount(ChemicalQuantities);
        }
        else
            System.out.println("Error! No fuel required?!");
        
        return oreNeeded;
    }

    /*
     * Go through each ChemicalQuantity and try to create the
     * required amount, storing excess in the inventory.
     * So check the inventory first, of course.
     */

    private int createNeededAmount (Vector<ChemicalQuantity> chemicalQuantities)
    {
        int oreNeeded = 0;
        Enumeration<ChemicalQuantity> iter = chemicalQuantities.elements();

        if (_debug)
            System.out.println("\nScanning reactions.");

        while (iter.hasMoreElements())
        {
            ChemicalQuantity chemicalAndQuantity = iter.nextElement();  // the chemical needed and the amount of it
            Reaction r = findReaction(chemicalAndQuantity.getChemical().getName());  // the reaction for the chemical needed
            int needed = chemicalAndQuantity.getAmount();  // the amount of chemical needed

            if (_debug)
            {
                System.out.println("\nChemicalQuantity "+chemicalAndQuantity);
                System.out.println("Needed reaction: "+r);
                System.out.println("Quantity of "+chemicalAndQuantity.getChemical()+" needed: "+needed);
                System.out.println("Quantity which would be created from reaction: "+r.chemicalCreated().getAmount());
            }

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

                if (_debug)
                    System.out.println("Reaction uses ORE");

                int amountCreated = 0;
                
                do
                {
                    System.out.println("**amount created so far: "+amountCreated);

                    int storage = checkInventory(chemicalAndQuantity);

                    System.out.println("**amount from  inventory: "+storage);

                    if (storage >= chemicalAndQuantity.getAmount())
                    {
                        consumeFromInventory(chemicalAndQuantity);

                        amountCreated += chemicalAndQuantity.getAmount();
                    }
                    else
                    {
                        oreNeeded += r.getChemicalQuantities().elementAt(0).getAmount();
                        
                        amountCreated += r.chemicalCreated().getAmount();

                        if (amountCreated > needed)
                        {
                            if (_debug)
                                System.out.println("Created "+(amountCreated - needed)+" more "+r.chemicalCreated()+" than needed");

                            storeExcessChemical(chemicalAndQuantity.getChemical(), amountCreated - needed);

                            amountCreated = needed;
                        }
                        else
                            System.out.println("**Not enough created.");
                    }

                    System.out.println("**after all that: "+amountCreated);

                } while (amountCreated != needed);
            }
            else
            {
                int amountCreated = 0;

                do
                {
                    /*
                    * Not ORE so we need to create the chemical using
                    * the reaction. Check the inventory first and update
                    * if we have excess.
                    */

                    if (_debug)
                        System.out.println("Reaction does NOT use ORE.");

                    int amountStored = checkInventory(chemicalAndQuantity);

                    if (amountStored >= chemicalAndQuantity.getAmount())
                    {
                        /*
                        * There's enough of the chemical in storage so just
                        * use that and we're done!
                        */

                        consumeFromInventory(chemicalAndQuantity);

                        amountCreated += chemicalAndQuantity.getAmount();
                    }
                    
                    System.out.println("**LOOPING");
                    
                    oreNeeded += createNeededAmount(r.getChemicalQuantities());

                    System.out.println("**GOT BACK:" +amountCreated);
                    
                } while (amountCreated != needed);
            }
        }

        return oreNeeded;
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
