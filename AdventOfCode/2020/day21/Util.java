import java.util.*;
import java.io.*;

public class Util
{
    public static final String ALLERGEN_DELIMITER = "(contains";

    public static final Vector<Food> loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Food> values = new Vector<Food>();

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));
         
            while ((line = reader.readLine()) != null)
            {
                Food f = new Food();
                String[] foodData = line.split(" ");
                boolean allergens = false;

                for (int i = 0; i < foodData.length; i++)
                {
                    if (!allergens)
                    {
                        if (foodData[i].startsWith(ALLERGEN_DELIMITER))
                            allergens = true;
                        else
                            f.addIngredient(foodData[i]);
                    }
                    else
                        f.addAllergen(foodData[i].substring(0, foodData[i].length() -1));
                }

                values.add(f);

                if (debug)
                    System.out.println("\nLoaded:\n"+f);
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

        return values;
    }

    // prevent instantiation

    private Util ()
    {
    }
}