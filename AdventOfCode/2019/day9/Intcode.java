import java.io.*;

public class Intcode
{
    public static final String DELIMITER = ",";

    /*
     * This implementation is stateless other than being placed
     * into debug mode where it will output whatever action it
     * takes.
     */

    public Intcode (String[] values, boolean debug)
    {
        _debug = debug;
        _instructionPointer = 0;
        _values = new String[values.length];
        _currentState = "-1";
        _status = Status.CREATED;
        _relativeBase = 0;

        System.arraycopy(values, 0, _values, 0, values.length);
    }

    public final boolean hasHalted ()
    {
        return (_status == Status.HALTED);
    }

    public final String currentState ()
    {
        return _currentState;
    }

    public final int status ()
    {
        return _status;
    }

    /**
     * Execute the commands given.
     * 
     * @param initialInput1 initial input param 1
     * @param initialInput2 initial input param 2
     * @param output any output that may be generated before termination.
     * @return true if the program code has completed, false otherwise.
     */

     // maybe move the initial parameter to the constructor?

    public String executeProgram (long initialInput1, long initialInput2)
    {
        if (hasHalted())
        {
            if (_debug)
                System.out.println("Intocde computer has halted!");

            return _currentState;
        }

        if (_debug)
            System.out.println("Intcode inputs <"+initialInput1+", "+initialInput2+"> and instruction pointer: "+_instructionPointer);
        
        for (int i = _instructionPointer; i < _values.length; i++)
        {
            String str = getOpcode(_values[i]);
            int opcode = Integer.valueOf(str);
            int[] modes = ParameterMode.getModes(_values[i]);

            if (_debug)
            {
                System.out.println("\nWorking on element "+i+" which is command "+Instructions.commandToString(opcode)+
                                        " with parameter modes ...");

                ParameterMode.printModes(modes);
            }

            /*
             * Now factor in the parameter modes.
             */

            switch (opcode)
            {
                case Instructions.ADD:
                {
                    /*
                     * Opcode 1 adds together numbers read from two positions
                     * and stores the result in a third position. The three integers
                     * immediately after the opcode tell you these three positions - the
                     * first two indicate the positions from which you should read the
                     * input values, and the third indicates the position at which
                     * the output should be stored.
                     */

                     long param1 = Long.valueOf(_values[i+1]);
                     long param2 = Long.valueOf(_values[i+2]);
                     long param3 = Long.valueOf(_values[i+3]);

                     if (modes[0] == ParameterMode.POSITION_MODE)
                        param1 = Integer.valueOf(_values[(int) param1]);

                     if (modes[1] == ParameterMode.POSITION_MODE)
                        param2 = Integer.valueOf(_values[(int) param2]);

                     if (_debug)
                        System.out.println("Adding "+param1+" and "+param2);

                     long sum = param1+param2;

                     if (_debug)
                        System.out.println("Storing "+sum+" at position "+param3);

                     _values[(int) param3] = String.valueOf(sum);

                     i = i+3;  // move the pointer on.
                }
                break;
                case Instructions.MULTIPLY:
                {
                    /*
                     * Opcode 2 works exactly like opcode 1, except it multiplies the
                     * two inputs instead of adding them. Again, the three integers after
                     * the opcode indicate where the inputs and outputs are, not their values.
                     */

                    int param1 = Integer.valueOf(_values[i+1]);
                    int param2 = Integer.valueOf(_values[i+2]);
                    int param3 = Integer.valueOf(_values[i+3]);

                    if (modes[0] == ParameterMode.POSITION_MODE)
                        param1 = Integer.valueOf(_values[param1]);

                    if (modes[1] == ParameterMode.POSITION_MODE)
                        param2 = Integer.valueOf(_values[param2]);

                    if (_debug)
                        System.out.println("Multiplying "+param1+" and "+param2);

                    int product = Integer.valueOf(param1)*Integer.valueOf(param2);

                    if (_debug)
                        System.out.println("Storing "+product+" at position "+param3);

                    _values[param3] = String.valueOf(product);

                    i = i+3;  // move the pointer on.
                }
                break;
                case Instructions.INPUT_AND_STORE:
                {
                    /*
                     * Opcode 3 takes a single integer as input and saves it to
                     * the position given by its only parameter.
                     */

                     int param1 = Integer.valueOf(_values[i+1]);

                     if (_debug)
                        System.out.println("Storing "+((_status == Status.CREATED) ? initialInput1 : initialInput2)+" at position "+param1);

                     _values[param1] = String.valueOf(((_status == Status.CREATED) ? initialInput1 : initialInput2));

                     i = i+1;  // move the pointer on.
                }
                break;
                case Instructions.OUTPUT:
                {
                    /*
                     * Opcode 4 outputs the value of its only parameter.
                     */

                    int param1 = Integer.valueOf(_values[i+1]);

                    if (modes[0] == ParameterMode.IMMEDIATE_MODE)
                        _currentState = Integer.toString(param1);
                    else
                        _currentState = _values[param1];

                     if (_debug)
                        System.out.println("Outputting value "+_currentState+" from entry "+param1);

                     i = i+1;  // move the pointer on.

                     _instructionPointer = i+1;
                    _status = Status.PAUSED;

                     return _currentState;
                }
 //               break;
                case Instructions.JUMP_IF_TRUE:
                {
                    /*
                     * If the first parameter is non-zero, it sets the instruction pointer to
                     * the value from the second parameter. Otherwise, it does nothing.
                     */

                    int param1 = Integer.valueOf(_values[i+1]);
                    int param2 = Integer.valueOf(_values[i+2]);

                    if (modes[0] == ParameterMode.POSITION_MODE)
                        param1 = Integer.valueOf(_values[param1]);

                    if (modes[1] == ParameterMode.POSITION_MODE)
                        param2 = Integer.valueOf(_values[param2]);

                    if (_debug)
                        System.out.println("Checking "+param1+" != 0 and might jump to "+param2);

                    if (param1 != 0)
                    {
                        i = param2 -1;  // remember we're in a for-loop!

                        if (_debug)
                            System.out.println("Will jump to "+param2);
                    }
                    else
                        i = i+2;
                }
                break;
                case Instructions.JUMP_IF_FALSE:
                {
                    /*
                     * If the first parameter is zero, it sets the instruction pointer to the value
                     * from the second parameter. Otherwise, it does nothing.
                     */

                    int param1 = Integer.valueOf(_values[i+1]);
                    int param2 = Integer.valueOf(_values[i+2]);

                    if (modes[0] == ParameterMode.POSITION_MODE)
                        param1 = Integer.valueOf(_values[param1]);

                    if (modes[1] == ParameterMode.POSITION_MODE)
                        param2 = Integer.valueOf(_values[param2]);

                    if (_debug)
                        System.out.println("Checking "+param1+" == 0 and might jump to "+param2);

                    if (param1 == 0)
                    {
                        i = param2 -1;  // remember we're in a for-loop!

                        if (_debug)
                            System.out.println("Will jump to "+param2);
                    }
                    else
                        i = i+2;
                }
                break;
                case Instructions.LESS_THAN:
                {
                    /*
                     * If the first parameter is less than the second parameter, it stores 1
                     * in the position given by the third parameter. Otherwise, it stores 0.
                     */

                    int param1 = Integer.valueOf(_values[i+1]);
                    int param2 = Integer.valueOf(_values[i+2]);
                    int param3 = Integer.valueOf(_values[i+3]);

                    if (modes[0] == ParameterMode.POSITION_MODE)
                        param1 = Integer.valueOf(_values[param1]);

                    if (modes[1] == ParameterMode.POSITION_MODE)
                        param2 = Integer.valueOf(_values[param2]);

                    if (_debug)
                    {
                        System.out.println("Checking whether "+param1+" < "+param2);
                        System.out.print("Storing ");
                    }

                    if (param1 < param2)
                    {
                        if (_debug)
                            System.out.print("1");

                        _values[param3] = "1";
                    }
                    else
                    {
                        if (_debug)
                            System.out.print("0");

                        _values[param3] = "0";
                    }

                    if (_debug)
                        System.out.println(" at location "+param3);

                    i = i+3;  // move the pointer on.
                }
                break;
                case Instructions.EQUALS:
                {
                    /*
                     * If the first parameter is equal to the second parameter, it stores 1
                     * in the position given by the third parameter. Otherwise, it stores 0.
                     */

                    int param1 = Integer.valueOf(_values[i+1]);
                    int param2 = Integer.valueOf(_values[i+2]);
                    int param3 = Integer.valueOf(_values[i+3]);

                    if (modes[0] == ParameterMode.POSITION_MODE)
                        param1 = Integer.valueOf(_values[param1]);

                    if (modes[1] == ParameterMode.POSITION_MODE)
                        param2 = Integer.valueOf(_values[param2]);

                    if (_debug)
                    {
                        System.out.println("Checking whether "+param1+" is equal to "+param2);
                        System.out.print("Storing ");
                    }

                    if (param1 == param2)
                    {
                        if (_debug)
                            System.out.print("1");

                        _values[param3] = "1";
                    }
                    else
                    {
                        if (_debug)
                            System.out.print("0");

                        _values[param3] = "0";
                    }

                    if (_debug)
                        System.out.println(" at location "+param3);

                    i = i+3;  // move the pointer on.
                }
                break;
                case Instructions.RELATIVE_BASE:
                {
                    /*
                     * Set the relative base.
                     */

                    _relativeBase = Integer.valueOf(_values[i+1]);
                }
                break;
                case Instructions.HALT:
                {
                    /*
                     * Means that the program is finished and should immediately halt.
                     */

                     if (_debug)
                        System.out.println("Halting execution.");

                     _instructionPointer = _values.length;
                    _status = Status.HALTED;

                     return _currentState;
                }
                default:
                {
                    System.out.println("Unknown opcode "+str+" encountered");

                    _instructionPointer = _values.length;  // stop any further execution.
                    _status = Status.HALTED;
                }
            }

            _status = Status.RUNNING;
        }

        return _currentState;
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

    private boolean _debug;
    private int _instructionPointer;
    private String[] _values;
    private String _currentState;
    private int _status;
    private int _relativeBase;
}