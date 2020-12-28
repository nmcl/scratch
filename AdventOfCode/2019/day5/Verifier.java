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
                System.out.println("Verified ok!");
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

    private Intcode _theComputer = null;
    private boolean _debug = false;

    private static final String TEST_CODE_1 = "3,0,4,0,99";
    private static final String TEST_INPUT_1 = "1234";
    private static final String TEST_RESULT_1 = "1234";
    private static final String TEST_CODE_2 = "1002,4,3,4,33";
    private static final String TEST_INPUT_2 = "0";
    private static final String TEST_RESULT_2 = "";
}