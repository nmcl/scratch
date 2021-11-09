import java.util.*;

public class PasswordChecker
{
    public static final String DATA_FILE = "input.txt";
    public static void main (String args[])
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

        int validCount = 0;
        Vector<PasswordData> passwords = Util.loadData(DATA_FILE, debug);
        Enumeration<PasswordData> iter = passwords.elements();

        while (iter.hasMoreElements())
        {
            if (iter.nextElement().valid())
                validCount++;
        }

        System.out.println("Number of valid passwords: "+validCount);
    }
}