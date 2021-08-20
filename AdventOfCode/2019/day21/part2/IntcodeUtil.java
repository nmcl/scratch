import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class IntcodeUtil
{
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

    public static final String[] convertStringToASCIIArray (String input)
    {
        byte[] toArray = input.getBytes(StandardCharsets.US_ASCII);
        String[] toReturn = new String[toArray.length];

        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = ""+toArray[i];

        return toReturn;
    }

    private IntcodeUtil ()
    {
    }
}