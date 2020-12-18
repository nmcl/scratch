import java.io.*;

public class Computer
{
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean dump = false;
        boolean runVerifier = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-verify] [-debug] [-dump]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
                runVerifier = true;

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-dump".equals(args[i]))
                dump = true;
        }

        _theComputer = new Intcode(debug);

        if (runVerifier)
        {
            verify(debug);
            System.exit(0);
        }

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values = line.split(SEPARATOR);

                if (dump)
                    dumpData(values);
                else
                {
                    resetState(values, debug);

                    System.out.println("The value at address 0 is: " + _theComputer.parseAndExecute(values));

                    if (debug)  
                        dumpData(values);
                }
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }
    }

    private static final void dumpData (String[] values)
    {
        for (String str : values)
        {
            System.out.println(str);
        }
    }

    private static final void verify (boolean debug)
    {
        String[] values = TEST_CODE_1.split(SEPARATOR);
        String str = null;

        if (debug)
            System.out.println("Verifying "+TEST_CODE_1);

        _theComputer.parseAndExecute(values);

        str = convert(values);
 
        if (str.equals(TEST_RESULT_1))
        {
            if (debug)
                System.out.println("Verifying "+TEST_CODE_2);
            
            values = TEST_CODE_2.split(SEPARATOR);

            _theComputer.parseAndExecute(values);

            str = convert(values);
 
            if (str.equals(TEST_RESULT_2))
            {
                if (debug)
                    System.out.println("Verifying "+TEST_CODE_3);

                values = TEST_CODE_3.split(SEPARATOR);

                _theComputer.parseAndExecute(values);

                str = convert(values);
 
                if (str.equals(TEST_RESULT_3))
                {
                    if (debug)
                        System.out.println("Verifying "+TEST_CODE_4);

                    values = TEST_CODE_4.split(SEPARATOR);

                    _theComputer.parseAndExecute(values);

                    str = convert(values);
 
                    if (str.equals(TEST_RESULT_4))
                    {
                        if (debug)
                            System.out.println("Verifying "+TEST_CODE_5);
            
                        values = TEST_CODE_5.split(SEPARATOR);

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

    public static String convert (String[] values)
    {
        String str = values[0];

        for (int i = 1; i < values.length; i++)
            str += SEPARATOR + values[i];

        return str;
    }

    /*
     * Before running the program, replace position 1 with the value 12 and
     * replace position 2 with the value 2.
     */

    private static final void resetState (String[] values, boolean debug)
    {
        if (debug)
            System.out.println("Replacing position 1 with 12 and position 2 with 2");
            
        values[1] = "12";
        values[2] = "2";
    }

    private static Intcode _theComputer = null;

    private static final String SEPARATOR = ",";

    private static final String DATA_FILE = "instructions.txt";

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