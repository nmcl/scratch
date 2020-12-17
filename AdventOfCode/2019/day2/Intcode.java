import java.io.*;

public class Intcode
{
    /*
     * Could be enum values but ints are just so much easier!
     */

    public static final int ADD = 1;
    public static final int MULTIPLY = 2;
    public static final int HALT = 99;
   
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
                else
                    parseAndExecute(values);
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

    private static void parseAndExecute (String[] values)
    {
        for (int i = 0; i < values.length; i++)
        {
            String str = values[i];

            switch (Integer.valueOf(str))
            {
                case Intcode.ADD:
                {
                    /*
                     * Opcode 1 adds together numbers read from two positions
                     * and stores the result in a third position. The three integers
                     * immediately after the opcode tell you these three positions - the
                     * first two indicate the positions from which you should read the
                     * input values, and the third indicates the position at which
                     * the output should be stored.
                     */

                     int position1 = Integer.valueOf(values[i+1]);
                     int position2 = Integer.valueOf(values[i+2]);
                     int store = Integer.valueOf(values[i+3]);

                     int sum = Integer.valueOf(position1)+Integer.valueOf(position2);

                     values[store] = String.valueOf(sum);

                     i = i+3;  // move the pointer on.
                }
                break;
                case Intcode.MULTIPLY:
                {
                    /*
                     * Opcode 2 works exactly like opcode 1, except it multiplies the
                     * two inputs instead of adding them. Again, the three integers after
                     * the opcode indicate where the inputs and outputs are, not their values.
                     */

                    int position1 = Integer.valueOf(values[i+1]);
                    int position2 = Integer.valueOf(values[i+2]);
                    int store = Integer.valueOf(values[i+3]);

                    int product = Integer.valueOf(position1)*Integer.valueOf(position2);

                    values[store] = String.valueOf(product);

                    i = i+3;  // move the pointer on.
                }
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