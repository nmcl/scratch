import java.util.*;
import java.io.*;

public class Parser
{
    public static final String DELIMITER = ",";
    public static final String PRODUCES = "=>";
    public static final String SPACE = " ";
    public static final String ORE = "ORE";

    public Parser (boolean debug)
    {
        _debug = debug;
    }

    public void loadData (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                System.out.println("**read "+line);

                /*
                 * Line format:
                 * 
                 * - ALWAYS start with a number
                 * - ALWAYS follow with a String
                 * - Then EITHER a comma if more than one chemical OR => to denote end of reactants
                 * - ALWAYS end in a number and a String
                 */

                 Vector<Integer> quantities = new Vector<Integer>();
                 Vector<Chemical> chemicals = new Vector<Chemical>();
                 boolean allReactants = false;
                 int ptr = 0;

                 do
                 {
                    int quantitySpace = line.indexOf(SPACE, ptr);

                    System.out.println("**space at "+quantitySpace);

                    if (quantitySpace != -1)
                    {
                        int quantity = Integer.parseInt(line.substring(ptr, quantitySpace));
                        int chemDelim = line.indexOf(DELIMITER, quantitySpace);
                        String chem = null;

                        System.out.println("**chemDelim "+chemDelim);

                        if (chemDelim == -1)  // no more chemicals
                        {
                            chemDelim = line.indexOf(PRODUCES, quantitySpace);
                            allReactants = true;
                        }

                        chem = line.substring(quantitySpace+1, chemDelim-1);

                        ptr += chemDelim;

                        System.out.println("**got "+quantity+" and "+chem);
                    }
                    else
                        allReactants = true;

                } while (!allReactants);
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
    }

    private boolean _debug;
}