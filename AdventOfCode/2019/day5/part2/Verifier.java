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

        str = _theComputer.parseAndExecute(values, Integer.parseInt(TEST_INPUT_1));

        if (TEST_RESULT_1.equals(str))
        {
            if (_debug)
                System.out.println("\nVerifying "+TEST_CODE_2);

            values = TEST_CODE_2.split(Intcode.DELIMITER);
            str = _theComputer.parseAndExecute(values, Integer.parseInt(TEST_INPUT_2));

            if (TEST_RESULT_2.equals(str))
            {
                System.out.println("Final data from "+TEST_CODE_2);
                
                dumpData(values);

                if (_debug)
                    System.out.println("\nVerifying "+TEST_CODE_3);

                values = TEST_CODE_3.split(Intcode.DELIMITER);
                str = _theComputer.parseAndExecute(values, Integer.parseInt(TEST_INPUT_3));

                if (TEST_RESULT_3.equals(str))
                {
                    System.out.println("Final data from "+TEST_CODE_3);
                
                    dumpData(values);

                    if (_debug)
                        System.out.println("Verifying "+TEST_CODE_4);

                    values = TEST_CODE_4.split(Intcode.DELIMITER);
                    str = _theComputer.parseAndExecute(values, 9); // should != 8
                    
                    if ("0".equals(str))
                    {
                        if (_debug)
                            System.out.println("\nVerifying "+TEST_CODE_5);

                        values = TEST_CODE_5.split(Intcode.DELIMITER);
                        str = _theComputer.parseAndExecute(values, 2); // should be < 8

                        if ("2".equals(str))
                        {
                            if (_debug)
                                System.out.println("\nVerifying "+TEST_CODE_6);

                            values = TEST_CODE_6.split(Intcode.DELIMITER);
                            str = _theComputer.parseAndExecute(values, 9); // should != 8

                            if ("0".equals(str))
                            {
                                if (_debug)
                                    System.out.println("\nVerifying "+TEST_CODE_7);

                                values = TEST_CODE_7.split(Intcode.DELIMITER);
                                str = _theComputer.parseAndExecute(values, 2); // should be < 8

                                if ("1".equals(str))
                                    System.out.println("Verified ok!");
                                else
                                    System.out.println("Verify failed for "+TEST_CODE_7);
                            }
                            else
                                System.out.println("Verify failed for "+TEST_CODE_6);
                        }
                        else
                            System.out.println("Verify failed for "+TEST_CODE_5);
                    }
                    else
                        System.out.println("Verify failed for "+TEST_CODE_4);
                }
                else
                    System.out.println("Verify failed for "+TEST_CODE_3);
            }
            else
                System.out.println("Verify failed for "+TEST_CODE_2);
        }
        else
            System.out.println("Verify failed for "+TEST_CODE_1);
    }

    private String convert (String[] values)
    {
        String str = values[0];

        for (int i = 1; i < values.length; i++)
            str += Intcode.DELIMITER + values[i];

        return str;
    }

    private final void dumpData (String[] values)
    {
        for (String str : values)
        {
            System.out.println(str);
        }
    }

    private Intcode _theComputer = null;
    private boolean _debug = false;

    private static final String TEST_CODE_1 = "3,0,4,0,99";
    private static final String TEST_INPUT_1 = "1234";
    private static final String TEST_RESULT_1 = "1234";
    private static final String TEST_CODE_2 = "1002,4,3,4,33";
    private static final String TEST_INPUT_2 = "0";
    private static final String TEST_RESULT_2 = "";
    private static final String TEST_CODE_3 = "1101,100,-1,4,0";
    private static final String TEST_INPUT_3 = "0";
    private static final String TEST_RESULT_3 = "";
    private static final String TEST_CODE_4 = "3,9,8,9,10,9,4,9,99,-1,8";
    private static final String TEST_CODE_5 = "3,9,7,9,10,9,4,9,99,-1,8";
    private static final String TEST_CODE_6 = "3,3,1108,-1,8,3,4,3,99";
    private static final String TEST_CODE_7 = "3,3,1107,-1,8,3,4,3,99";
}