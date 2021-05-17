import java.util.*;
import java.io.*;

public class IntcodeUtil
{
    public static String[] convert (long[] input)
    {
        String[] converted = new String[input.length];

        for (int i = 0; i < input.length; i++)
            converted[i] = Long.toString(input[i]);

        return converted;
    }

    public static final Vector<String> readValues (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values = line.split(Intcode.DELIMITER);
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

        Vector<String> instructions = new Vector<String>();

        instructions.addAll(Arrays.asList(values));

        return instructions;
    }

    private IntcodeUtil ()
    {
    }
}