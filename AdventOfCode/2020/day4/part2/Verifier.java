import java.util.*;

public class Verifier
{
    public static final String INVALID_DATA = "invalid.txt";
    public static final String VALID_DATA = "valid.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Passport> passports = Batch.loadData(INVALID_DATA, _debug);
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

        if (numberOfValidPassports > 0)
        {
            System.out.println("Failed invalid check.");

            return false;
        }

        passports = Batch.loadData(VALID_DATA, _debug);
        iter = passports.elements();
        
        int numberOfInvalidPassports = 0;

        while (iter.hasMoreElements())
        {
            Passport p = iter.nextElement();

            if (_debug)
                System.out.println("Checking "+p+" and validity: "+p.isValid());

            if (!p.isValid())
                numberOfInvalidPassports++;
        }

        if (numberOfInvalidPassports > 0)
        {
            System.out.println("Failed valid check.");

            return false;
        }

        return true;
    }

    private boolean _debug;
}