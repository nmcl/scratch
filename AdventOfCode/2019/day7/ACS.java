import java.io.*;

public class ACS
{
    public static final int NUMBER_OF_AMPLIFIERS = 5;
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

            theVerifier.verify();
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
    }

    private static final String DATA_FILE = "instructions.txt";
}