import java.io.*;
import java.util.*;

/*
 * The Emergency Hull Painting Robot
 */

public class EHPR
{
    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
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

        Intcode theComputer = new Intcode(instructions, 2, debug);

        Vector<String> results = new Vector<String>();

        while (!theComputer.hasHalted())
            results = theComputer.executeProgram();

        Enumeration<String> iter = results.elements();

        System.out.println("Got back:");

        while (iter.hasMoreElements())
        {
            String item = iter.nextElement();

            System.out.println(item);
        }
    }
    
    private static final String DATA_FILE = "instructions.txt";
}