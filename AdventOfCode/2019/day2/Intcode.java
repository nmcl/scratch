import java.io.*;

public class Intcode
{
    public enum OPCODE {
        ADD(1), MULTIPLY(2), HALT(99);

        OPCODE (int val)
        {
            this._val = val;
        }

        public int getVal ()
        {
            return _val;
        }

        private int _val;
    }

    public static final String DELIMITER = ",";

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-verify] [-debug]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
            {
                Intcode.verify();
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values = line.split(",");

                if (debug)
                {
                    for (String str : values)
                    {
                        System.out.println(str);
                    }
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

    private static void verify ()
    {

    }

    private static final String DATA_FILE = "instructions.txt";

    private static final String TEST_CODE_1 = "1,9,10,3,2,3,11,0,99,30,40,50";
    private static final String TEST_CODE_2 = "1,0,0,0,99";
    private static final String TEST_CODE_3 = "2,3,0,3,99";
    private static final String TEST_CODE_4 = "2,4,4,5,99,0";
    private static final String TEST_CODE_5 = "1,1,1,4,99,5,6,0,99";
}