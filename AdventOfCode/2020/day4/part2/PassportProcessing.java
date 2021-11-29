import java.util.*;

public class PassportProcessing
{
    public static final String INPUT_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Vector<Passport> passports = Batch.loadData(INPUT_FILE, debug);
        Enumeration<Passport> iter = passports.elements();
        int numberOfValidPassports = 0;

        while (iter.hasMoreElements())
        {
            Passport p = iter.nextElement();

            if (debug)
                System.out.println("Checking "+p+" and validity: "+p.isValid());

            if (p.isValid())
                numberOfValidPassports++;
        }

        System.out.println("Number of valid passports: "+numberOfValidPassports);
    }
}