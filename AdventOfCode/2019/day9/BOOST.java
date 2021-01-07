import java.io.*;
import java.util.*;

public class BOOST
{
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean runVerifier = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-verify] [-debug]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                runVerifier = true;
        }


        if (runVerifier)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("Verified ok!");
            else
                System.out.println("Verify failed!");
                
            System.exit(0);
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

        Intcode theComputer = new Intcode(instructions, 1, debug);

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