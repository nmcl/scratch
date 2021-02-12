import java.util.*;
import java.io.*;

public class Parser
{
    public static final String DELIMITER = ",";
    public static final String PRODUCES = "=>";
    public static final String SPACE = " ";

    public Parser (boolean debug)
    {
        _debug = debug;
    }

    public Vector<Reaction> loadData (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Reaction> reactions = new Vector<Reaction>();

        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Parser read: "+line);

                Reaction react = new Reaction();
                Vector<Reactant> reactants = new Vector<Reactant>();

                /*
                 * Line format:
                 * 
                 * - ALWAYS start with a number
                 * - ALWAYS follow with a String
                 * - Then EITHER a comma if more than one chemical OR => to denote end of reactants
                 * - ALWAYS end in a number and a String
                 */

                 boolean allReactants = false;
                 int ptr = 0;
                 int createsPointer = 0;

                 do
                 {
                    int quantitySpace = line.indexOf(SPACE, ptr);

                    if (quantitySpace != -1)
                    {
                        int quantity = Integer.parseInt(line.substring(ptr, quantitySpace));
                        int chemDelim = line.indexOf(DELIMITER, quantitySpace);
                        String chem = null;

                        if (chemDelim == -1)  // no more chemicals
                        {
                            chemDelim = line.indexOf(PRODUCES, quantitySpace);
                            allReactants = true;
                            createsPointer = chemDelim;
                        }

                        chem = line.substring(quantitySpace+1, chemDelim);

                        ptr += chemDelim +2;  // move on past comma and space!

                        reactants.add(new Reactant(new Chemical(chem), quantity));
                    }
                    else
                    {
                        System.out.println("Error in parsing line!");

                        allReactants = true;
                    }

                    react.setReactants(reactants);

                } while (!allReactants);

                // mow what do these reactants give us?

                createsPointer += PRODUCES.length() +1;

                int createsSpace = line.indexOf(SPACE, createsPointer);
                int createsQuantity = Integer.parseInt(line.substring(createsPointer, createsSpace));
                String chemCreated = line.substring(createsSpace +1);

                react.setCreated(new Reactant(new Chemical(chemCreated), createsQuantity));

                reactions.add(react);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return reactions;
    }

    private boolean _debug;
}