import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final int VALID_PASSPORTS = 2;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Passport> passports = Batch.loadData(EXAMPLE_FILE, _debug);
        Enumeration<Passport> iter = passports.elements();
        int numberOfValidPassports = 0;

        while (iter.hasMoreElements())
        {
            Passport p = iter.nextElement();

            if (_debug)
                System.out.println("Checking "+p+" and validity: "+p.isValid());

            if (p.isValid())
                numberOfValidPassports++;
        }

        if (numberOfValidPassports == VALID_PASSPORTS)
            return true;

        return false;
    }

    private boolean _debug;
}