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

    public void parseAndExecute (String[] values, boolean debug)
    {
        for (int i = 0; i < values.length; i++)
        {
            String str = values[i];

            if (debug)
                System.out.println("Working on entry "+i+" with "+str);

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

                     if (debug)
                        System.out.println("Adding "+values[position1]+" and "+values[position2]);

                     int sum = Integer.valueOf(values[position1])+Integer.valueOf(values[position2]);

                     if (debug)
                        System.out.println("Storing "+sum+" at position "+store);

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

                    if (debug)
                        System.out.println("Multiplying "+values[position1]+" and "+values[position2]);

                    int product = Integer.valueOf(values[position1])*Integer.valueOf(values[position2]);

                    if (debug)
                        System.out.println("Storing "+product+" at position "+store);

                    values[store] = String.valueOf(product);

                    i = i+3;  // move the pointer on.
                }
                break;
                case Intcode.HALT:
                {
                    /*
                     * Means that the program is finished and should immediately halt.
                     */

                     if (debug)
                        System.out.println("Halting execution.");

                     return;
                }
                default:
                {
                    System.out.println("Unknown opcocde "+str+" encountered");

                    return;
                }
            }
        }
    }

    /*
     * Before running the program, replace position 1 with the value 12 and
     * replace position 2 with the value 2.
     */

    public void resetState (String[] values, boolean debug)
    {
        if (debug)
            System.out.println("Replacing position 1 with 12 and position 2 with 2");
            
        values[1] = "12";
        values[2] = "2";
    }

    public void verify (boolean debug)
    {
        String[] values = TEST_CODE_1.split(SEPARATOR);
        String str = null;

        if (debug)
            System.out.println("Verifying "+TEST_CODE_1);

        parseAndExecute(values, debug);

        str = convert(values);
 
        if (str.equals(TEST_RESULT_1))
        {
            if (debug)
                System.out.println("Verifying "+TEST_CODE_2);
            
            values = TEST_CODE_2.split(SEPARATOR);

            parseAndExecute(values, debug);

            str = convert(values);
 
            if (str.equals(TEST_RESULT_2))
            {
                if (debug)
                    System.out.println("Verifying "+TEST_CODE_3);

                values = TEST_CODE_3.split(SEPARATOR);

                parseAndExecute(values, debug);

                str = convert(values);
 
                if (str.equals(TEST_RESULT_3))
                {
                    if (debug)
                        System.out.println("Verifying "+TEST_CODE_4);

                    values = TEST_CODE_4.split(SEPARATOR);

                    parseAndExecute(values, debug);

                    str = convert(values);
 
                    if (str.equals(TEST_RESULT_4))
                    {
                        if (debug)
                            System.out.println("Verifying "+TEST_CODE_5);
            
                        values = TEST_CODE_5.split(SEPARATOR);

                        parseAndExecute(values, debug);

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


    private static final String DATA_FILE = "instructions.txt";
    private static final String SEPARATOR = ",";

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