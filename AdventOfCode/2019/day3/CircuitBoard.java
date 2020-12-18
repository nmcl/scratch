import java.io.*;

public class CircuitBoard
{
    public static final String SEPARATOR = ",";
    
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean dump = false;
        boolean runVerifier = false;
        int target = 19690720;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-value target] [-help] [-verify] [-debug] [-dump]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
                runVerifier = true;

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-dump".equals(args[i]))
                dump = true;

            if ("-value".equals(args[i]))
                target = Integer.parseInt(args[i+1]);
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
                    resetState(values, debug);  // not really needed for this part.

                    Cruncher bruteForce = new Cruncher(_theComputer, values, debug);

                    String[] results = bruteForce.crunch(target);

                    if (results != null)
                    {
                        System.out.println("To achieve the target of "+target+" requires a noun of "+results[0]+" and a verb of "+results[1]);

                        int total = 100 * Integer.parseInt(results[0]) + Integer.parseInt(results[1]);

                        System.out.println("And the answer is: "+total);
                    }
                    else
                        System.out.println("Could not achieve the target of "+target);
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

    private static final String DATA_FILE = "data.txt";
}