import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Passport> passports = Batch.loadData(EXAMPLE_FILE, _debug);
        
        return false;
    }

    private boolean _debug;
}