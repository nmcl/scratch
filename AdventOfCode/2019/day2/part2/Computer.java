import java.io.*;

public class Computer
{
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean dump = false;
        boolean runVerifier = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-verify] [-debug] [-dump]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
                runVerifier = true;

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-dump".equals(args[i]))
                dump = true;
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

                if (dump)
                    dumpData(values);
                else
                {
                    resetState(values, debug);

                    System.out.println("The value at address 0 is: " + _theComputer.parseAndExecute(values));

                    if (debug)  
                        dumpData(values);
                }
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

    private static final void dumpData (String[] values)
    {
        for (String str : values)
        {
            System.out.println(str);
        }
    }

    /*
     * Before running the program, replace position 1 with the value 12 and
     * replace position 2 with the value 2.
     */

    private static final void resetState (String[] values, boolean debug)
    {
        if (debug)
            System.out.println("Replacing position 1 with 12 and position 2 with 2");
            
        values[1] = "12";
        values[2] = "2";
    }

    private static Intcode _theComputer = null;

    private static final String DATA_FILE = "instructions.txt";
}