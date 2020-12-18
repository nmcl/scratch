public class Verifier
{
    public Verifier (Intcode computer, boolean debug)
    {
        _theComputer = computer;
        _debug = debug;
    }

    public void verify ()
    {
        String[] values = TEST_CODE_1.split(Intcode.DELIMITER);
        String str = null;

        if (_debug)
            System.out.println("Verifying "+TEST_CODE_1);

        _theComputer.parseAndExecute(values);

        str = convert(values);
 
        if (str.equals(TEST_RESULT_1))
        {
            if (_debug)
                System.out.println("Verifying "+TEST_CODE_2);
            
            values = TEST_CODE_2.split(Intcode.DELIMITER);

            _theComputer.parseAndExecute(values);

            str = convert(values);
 
            if (str.equals(TEST_RESULT_2))
            {
                if (_debug)
                    System.out.println("Verifying "+TEST_CODE_3);

                values = TEST_CODE_3.split(Intcode.DELIMITER);

                _theComputer.parseAndExecute(values);

                str = convert(values);
 
                if (str.equals(TEST_RESULT_3))
                {
                    if (_debug)
                        System.out.println("Verifying "+TEST_CODE_4);

                    values = TEST_CODE_4.split(Intcode.DELIMITER);

                    _theComputer.parseAndExecute(values);

                    str = convert(values);
 
                    if (str.equals(TEST_RESULT_4))
                    {
                        if (_debug)
                            System.out.println("Verifying "+TEST_CODE_5);
            
                        values = TEST_CODE_5.split(Intcode.DELIMITER);

                        _theComputer.parseAndExecute(values);

                        str = convert(values);
 
                        if (str.equals(TEST_RESULT_5))
                            System.out.println("Verified ok.");
                        else
                        System.out.println("Verify failed on "+TEST_RESULT_5+" with "+str);
                    }
                    else
                        System.out.println("Verify failed on "+TEST_RESULT_4+" with "+str);
                }
                else
                    System.out.println("Verify failed on "+TEST_RESULT_3+" with "+str);
            }
            else
                System.out.println("Verify failed on "+TEST_RESULT_2+" with "+str);
        }
        else
            System.out.println("Verify failed on "+TEST_RESULT_1+" with "+str);
    }

    private String convert (String[] values)
    {
        String str = values[0];

        for (int i = 1; i < values.length; i++)
            str += Intcode.DELIMITER + values[i];

        return str;
    }

    private Intcode _theComputer = null;
    private boolean _debug = false;

    private static final String TEST_CODE_1 = "1,9,10,3,2,3,11,0,99,30,40,50";
    private static final String TEST_RESULT_1 = "3500,9,10,70,2,3,11,0,99,30,40,50";
    private static final String TEST_CODE_2 = "1,0,0,0,99";
    private static final String TEST_RESULT_2 = "2,0,0,0,99";
    private static final String TEST_CODE_3 = "2,3,0,3,99";
    private static final String TEST_RESULT_3 = "2,3,0,6,99";
    private static final String TEST_CODE_4 = "2,4,4,5,99,0";
    private static final String TEST_RESULT_4 = "2,4,4,5,99,9801";
    private static final String TEST_CODE_5 = "1,1,1,4,99,5,6,0,99";
    private static final String TEST_RESULT_5 = "30,1,1,4,2,5,6,0,99";
}