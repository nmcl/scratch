import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1 = "example1.txt";
    public static final long EXAMPLE1_RESULT = 231;
    public static final String EXAMPLE2 = "example2.txt";
    public static final long EXAMPLE2_RESULT = 51;
    public static final String EXAMPLE3 = "example3.txt";
    public static final long EXAMPLE3_RESULT = 46;
    public static final String EXAMPLE4 = "example4.txt";
    public static final long EXAMPLE4_RESULT = 1445;
    public static final String EXAMPLE5 = "example5.txt";
    public static final long EXAMPLE5_RESULT = 669060;
    public static final String EXAMPLE6 = "example6.txt";
    public static final long EXAMPLE6_RESULT = 23340;

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
            {
                data = Util.loadData(EXAMPLE3);

                result = p.parse(data);

                if (result == EXAMPLE3_RESULT)
                {
                    data = Util.loadData(EXAMPLE4);

                    result = p.parse(data);

                    if (result == EXAMPLE4_RESULT)
                    {
                        data = Util.loadData(EXAMPLE5);

                        result = p.parse(data);

                        if (result == EXAMPLE5_RESULT)
                        {
                            data = Util.loadData(EXAMPLE6);

                            result = p.parse(data);

                            if (result == EXAMPLE6_RESULT)
                                return true;
                            else
                                System.out.println("Incorrect value for "+EXAMPLE6+" "+result);
                        }
                        else
                            System.out.println("Incorrect value for "+EXAMPLE5+" "+result);
                    }
                    else
                        System.out.println("Incorrect value for "+EXAMPLE4+" "+result);
                }
                else
                    System.out.println("Incorrect value for "+EXAMPLE3+" "+result);
            }
            else
                System.out.println("Incorrect value for "+EXAMPLE2+" "+result);
        }
        else
            System.out.println("Incorrect value for "+EXAMPLE1+" "+result);

        return false;
    }

    private boolean _debug;
}