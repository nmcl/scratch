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
    private static final String TEST_RESULT_1 = "";
    private static final String TEST_CODE_2 = "1002,4,3,4,33";
    private static final String TEST_RESULT_2 = "";
}