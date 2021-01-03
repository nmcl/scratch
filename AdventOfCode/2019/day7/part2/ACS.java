import java.io.*;
import java.util.*;

public class ACS
{
    public static final int NUMBER_OF_AMPLIFIERS = 5;
    public static final String DIGITS = "56789";
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

        Vector<String> permutations = new Vector<String>();

        // Each phase setting is used exactly once.

//        permutation(DIGITS, permutations);

        permutations.add("98765");
        
        if (runVerifier)
        {
            Verifier theVerifier = new Verifier(permutations, debug);

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

            AmplifierSeries amps = new AmplifierSeries(permutations, debug);

            amps.setProgram(values);

            System.out.println("Maximum thruster signal: "+amps.maxThrusterSignal());
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

    // because ... https://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html

    private static void permutation (String str, Vector<String> options)
    { 
		permutation("", str, options); 
	}
	
    private static void permutation (String prefix, String str, Vector<String> options)
    {
        int n = str.length();
        
        if (n == 0)
            options.add(prefix);
        else
        {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), options);
		}
    }
    
    private static final String DATA_FILE = "instructions.txt";
}