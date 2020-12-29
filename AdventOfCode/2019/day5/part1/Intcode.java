import java.io.*;

public class Intcode
{
    /*
     * Could be enum values but ints are just so much easier!
     */

     /**
      * The Opcodes for the computer.
      */

    public static final int ADD = 1;
    public static final int MULTIPLY = 2;
    public static final int INPUT_AND_STORE = 3;
    public static final int OUTPUT = 4;
    public static final int HALT = 99;
   
    /**
     * The Parameter modes.
     */

    public static final int POSITION_MODE = 0;  // parameter is interpreted as a position
    public static final int IMMEDIATE_MODE = 1; // parameter is interpreted as a value

    private static final int MAX_PARAMETERS = 4;

    public static final String DELIMITER = ",";

    /*
     * This implementation is stateless other than being placed
     * into debug mode where it will output whatever action it
     * takes.
     */

    public Intcode (boolean debug)
    {
        _debug = debug;
    }

    public String parseAndExecute (String[] values, int initialInput)
    {
        String returnValue = "";

        for (int i = 0; i < values.length; i++)
        {
            String str = getOpcode(values[i]);
            int[] modes = getModes(values[i]);

            if (_debug)
            {
                System.out.println("Working on entry "+i+" with command "+str+
                                        " and parameter modes ...");
                printModes(modes);
            }

            /*
             * Now factor in the parameter modes.
             */

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

                     int param1 = Integer.valueOf(values[i+1]);
                     int param2 = Integer.valueOf(values[i+2]);
                     int param3 = Integer.valueOf(values[i+3]);

                     if (modes[0] == POSITION_MODE)
                        param1 = Integer.valueOf(values[param1]);

                     if (modes[1] == POSITION_MODE)
                        param2 = Integer.valueOf(values[param2]);

                     if (_debug)
                        System.out.println("Adding "+param1+" and "+param2);

                     int sum = param1+param2;

                     if (_debug)
                        System.out.println("Storing "+sum+" at position "+param3);

                     values[param3] = String.valueOf(sum);

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

                    int param1 = Integer.valueOf(values[i+1]);
                    int param2 = Integer.valueOf(values[i+2]);
                    int param3 = Integer.valueOf(values[i+3]);

                    if (modes[0] == POSITION_MODE)
                        param1 = Integer.valueOf(values[param1]);

                    if (modes[1] == POSITION_MODE)
                        param2 = Integer.valueOf(values[param2]);

                    if (_debug)
                        System.out.println("Multiplying "+param1+" and "+param2);

                    int product = Integer.valueOf(param1)*Integer.valueOf(param2);

                    if (_debug)
                        System.out.println("Storing "+product+" at position "+param3);

                    values[param3] = String.valueOf(product);

                    i = i+3;  // move the pointer on.
                }
                break;
                case Intcode.INPUT_AND_STORE:
                {
                    /*
                     * Opcode 3 takes a single integer as input and saves it to
                     * the position given by its only parameter.
                     */

                     int param1 = Integer.valueOf(values[i+1]);
                    System.out.println("**param1 "+param1);

                     //if (modes[0] == POSITION_MODE)
                        //param1 = Integer.valueOf(values[param1]);

                     if (_debug)
                        System.out.println("Storing "+initialInput+" at position "+param1);

                     values[param1] = String.valueOf(initialInput);

                     i = i+1;  // move the pointer on.
                }
                break;
                case Intcode.OUTPUT:
                {
                    /*
                     * Opcode 4 outputs the value of its only parameter.
                     */

                     int param1 = Integer.valueOf(values[i+1]);

                     if (_debug)
                        System.out.println("Pulling value from entry "+param1);

                     returnValue = values[param1];

                     i = i+1;  // move the pointer on.
                }
                break;
                case Intcode.HALT:
                {
                    /*
                     * Means that the program is finished and should immediately halt.
                     */

                     if (_debug)
                        System.out.println("Halting execution with "+returnValue);

                     return returnValue;
                }
                default:
                {
                    System.out.println("Unknown opcocde "+str+" encountered");

                    return "NaN";
                }
            }
        }

        return returnValue;
    }

    private String getOpcode (String digits)
    {
        if (_debug)
            System.out.println("Command: "+digits);
        
        String opcode = null;

        if ((digits != null) && (digits.length() > 2))
            opcode = digits.substring(digits.length()-2);
        else
            opcode = digits;
       
        if (_debug)
            System.out.println("Opcode: "+opcode);

        return opcode;
    }

    /*
     * Return the modes for the parameters, including default mode
     * (POSITION_MODE) if nothing is defined.
     */

    private int[] getModes (String digits)
    {
        int[] theModes = new int[MAX_PARAMETERS];

        for (int i = 0; i < MAX_PARAMETERS; i++)
            theModes[i] = POSITION_MODE;

        if ((digits != null) && (digits.length() > 2))
        {
            String allModes = digits.substring(0, digits.length()-2);

            if (_debug)
                System.out.println("Mode string: "+allModes);

            char[] modeArray = allModes.toCharArray();

            for (int j = modeArray.length-1; j >= 0; j--)
            {
                if (modeArray[j] == '1')
                {
                    if (_debug)
                        System.out.println("Param "+j+" is IMMEDIATE");

                    theModes[modeArray.length-j-1] = IMMEDIATE_MODE;
                }
            }
        }

        if (_debug)
            printModes(theModes);

        return theModes;
    }

    private void printModes (int[] modes)
    {
        for (int i = 0; i < modes.length; i++)
        {
            System.out.println("Parameter "+i+" is "+((modes[i] == IMMEDIATE_MODE) ? "immediate mode": "position mode"));
        }
    }

    private boolean _debug;
}