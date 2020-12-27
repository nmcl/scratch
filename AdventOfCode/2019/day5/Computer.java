import java.io.*;

public class Computer
{
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean runVerifier = false;
        int defaultInput = 1;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-verify] [-input <value>] [-debug]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
                runVerifier = true;

            if ("-debug".equals(args[i]))
                debug = true;
        }

        /*
         * Create the computer which will do the real work.
         */

        _theComputer = new Intcode(debug);

        if (runVerifier)
        {
            Verifier theVerifier = new Verifier(_theComputer, debug);

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

    private static Intcode _theComputer = null;

    private static final String DATA_FILE = "instructions.txt";
}