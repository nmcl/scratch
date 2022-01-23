import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1 = "example1.txt";
    public static final long EXAMPLE1_RESULT = 71;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> data = Util.loadData(EXAMPLE1);
        MathsParser p = new MathsParser(_debug);
        Long result = p.parse(data);

        if (result == EXAMPLE1_RESULT)
            return true;
        else
        {
            System.out.println("Incorrect value for "+EXAMPLE1+" "+result);

            return false;
        }
    }

    private boolean _debug;
}