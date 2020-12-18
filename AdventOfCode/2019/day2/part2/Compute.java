import java.io.*;

public class Compute
{
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean dump = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-verify] [-debug] [-dump]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
            {
                Intcode.verify(debug);
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-dump".equals(args[i]))
                dump = true;
        }

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

                    parseAndExecute(values, debug);

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

    private static void dumpData (String[] values)
    {
        for (String str : values)
        {
            System.out.println(str);
        }
    }

    private static final Intcode _theComputer = new Intcode();

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