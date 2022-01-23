import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1 = "example1.txt";
    public static final long EXAMPLE1_RESULT = 71;
    public static final String EXAMPLE2 = "example2.txt";
    public static final long EXAMPLE2_RESULT = 51;

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
        {
            data = Util.loadData(EXAMPLE2);

            result = p.parse(data);

            if (result == EXAMPLE2_RESULT)
                return true;
            else
                System.out.println("Incorrect value for "+EXAMPLE2+" "+result);
        }
        else
            System.out.println("Incorrect value for "+EXAMPLE1+" "+result);

        return false;
    }

    private boolean _debug;
}