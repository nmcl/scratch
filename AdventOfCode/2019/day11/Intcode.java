import java.io.*;
import java.util.*;

public class Intcode
{
    public static final String DELIMITER = ",";

    public static final String INITIALISED_MEMORY = "0";

    /*
     * This implementation is stateless other than being placed
     * into debug mode where it will output whatever action it
     * takes.
     */

    public Intcode (Vector<String> values, int input, boolean debug)
    {
        _debug = debug;
        _instructionPointer = 0;
        _output = new Vector<String>();
        _memory = new Vector<String>(values);
        _initialInput = input;
        _status = Status.CREATED;
        _relativeBase = 0;
    }

    public final boolean hasHalted ()
    {
        return (_status == Status.HALTED);
    }

    public final int status ()
    {
        return _status;
    }

    public final boolean hasOutput ()
    {
        System.out.println("**output size "+_output.size());

        return (_output.size() > 0);
    }

    public final String getOutput ()
    {
        return _output.remove(0);
    }

    /**
     * Execute the commands given.
     * 
     * @return a representation of the result
     */

     // maybe move the initial parameter to the constructor? Or change this to a Vector/array for multiple states?

    public int executeProgram ()
    {
        if (hasHalted())
        {
            if (_debug)
                System.out.println("Intocde computer has halted!");

            return _status;
        }

        if (_debug)
            System.out.println("Intcode input <"+_initialInput+"> and instruction pointer: "+_instructionPointer);

        while (!hasHalted())
        {
            String str = getOpcode(_memory.elementAt(_instructionPointer));
            int opcode = Integer.valueOf(str);
            int[] modes = ParameterMode.getModes(_memory.elementAt(_instructionPointer));

            //if (_debug)
            {
                System.out.println("\nWorking on element "+_instructionPointer+" which is command "+Instructions.commandToString(opcode)+
                                        " with parameter modes ...");

                //ParameterMode.printModes(modes);
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

                     long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));
                     long param2 = Long.valueOf(getValue(_instructionPointer+2, modes[1], false));
                     int param3 = Integer.valueOf(getValue(_instructionPointer+3, modes[2], true));

                     if (_debug)
                        System.out.println("Adding "+param1+" and "+param2);

                     long sum = param1+param2;

                     if (_debug)
                        System.out.println("Storing "+sum+" at position "+param3);

                     setValue(param3, String.valueOf(sum));

                     _instructionPointer += 4; // move the pointer on.
                }
                break;
                case Instructions.MULTIPLY:
                {
                    /*
                     * Opcode 2 works exactly like opcode 1, except it multiplies the
                     * two inputs instead of adding them. Again, the three integers after
                     * the opcode indicate where the inputs and outputs are, not their values.
                     */

                    long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));
                    long param2 = Long.valueOf(getValue(_instructionPointer+2, modes[1], false));
                    int param3 = Integer.valueOf(getValue(_instructionPointer+3, modes[2], true));

                    if (_debug)
                        System.out.println("Multiplying "+param1+" and "+param2);

                    long product = Long.valueOf(param1)*Long.valueOf(param2);

                    if (_debug)
                        System.out.println("Storing "+product+" at position "+param3);

                    setValue(param3, String.valueOf(product));

                    _instructionPointer += 4;  // move the pointer on.
                }
                break;
                case Instructions.INPUT_AND_STORE:
                {
                    /*
                     * Opcode 3 takes a single integer as input and saves it to
                     * the position given by its only parameter.
                     */

                     int param1 = Integer.valueOf(getValue(_instructionPointer+1, modes[0], true));

                     if (_debug)
                        System.out.println("Storing "+_initialInput+" at position "+param1);

                     setValue(param1, String.valueOf(_initialInput));

                     _instructionPointer += 2;  // move the pointer on.
                }
                break;
                case Instructions.OUTPUT:
                {
                    /*
                     * Opcode 4 outputs the value of its only parameter.
                     */

                    long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));

                    //if (_debug)
                        System.out.println("Adding value "+param1+" to output state.");

                    _output.add(Long.toString(param1));

                    _instructionPointer += 2;  // move the pointer on.

                    _status = Status.PAUSED;

                     return _status;
                }
 //               break;
                case Instructions.JUMP_IF_TRUE:
                {
                    /*
                     * If the first parameter is non-zero, it sets the instruction pointer to
                     * the value from the second parameter. Otherwise, it does nothing.
                     */

                    long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));
                    long param2 = Long.valueOf(getValue(_instructionPointer+2, modes[1], false));

                    if (_debug)
                        System.out.println("Checking "+param1+" != 0 and might jump to "+param2);

                    if (param1 != 0)
                    {
                        _instructionPointer = (int) param2;

                        if (_debug)
                            System.out.println("Will jump to "+param2);
                    }
                    else
                        _instructionPointer += 3;
                }
                break;
                case Instructions.JUMP_IF_FALSE:
                {
                    /*
                     * If the first parameter is zero, it sets the instruction pointer to the value
                     * from the second parameter. Otherwise, it does nothing.
                     */

                    long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));
                    long param2 = Long.valueOf(getValue(_instructionPointer+2, modes[1], false));

                    if (_debug)
                        System.out.println("Checking "+param1+" == 0 and might jump to "+param2);

                    if (param1 == 0)
                    {
                        _instructionPointer = (int) param2;

                        if (_debug)
                            System.out.println("Will jump to "+param2);
                    }
                    else
                        _instructionPointer += 3;
                }
                break;
                case Instructions.LESS_THAN:
                {
                    /*
                     * If the first parameter is less than the second parameter, it stores 1
                     * in the position given by the third parameter. Otherwise, it stores 0.
                     */

                    long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));
                    long param2 = Long.valueOf(getValue(_instructionPointer+2, modes[1], false));
                    int param3 = Integer.valueOf(getValue(_instructionPointer+3, modes[2], true));

                    if (_debug)
                    {
                        System.out.println("Checking whether "+param1+" < "+param2);
                        System.out.print("Storing ");
                    }

                    if (param1 < param2)
                    {
                        if (_debug)
                            System.out.print("1");

                        setValue(param3, "1");
                    }
                    else
                    {
                        if (_debug)
                            System.out.print("0");

                        setValue(param3, "0");
                    }

                    if (_debug)
                        System.out.println(" at location "+param3);

                    _instructionPointer += 4;  // move the pointer on.
                }
                break;
                case Instructions.EQUALS:
                {
                    /*
                     * If the first parameter is equal to the second parameter, it stores 1
                     * in the position given by the third parameter. Otherwise, it stores 0.
                     */

                    long param1 = Long.valueOf(getValue(_instructionPointer+1, modes[0], false));
                    long param2 = Long.valueOf(getValue(_instructionPointer+2, modes[1], false));
                    int param3 = Integer.valueOf(getValue(_instructionPointer+3, modes[2], true));

                    if (_debug)
                    {
                        System.out.println("Checking whether "+param1+" is equal to "+param2);
                        System.out.print("Storing ");
                    }

                    if (param1 == param2)
                    {
                        if (_debug)
                            System.out.print("1");

                        setValue(param3, "1");
                    }
                    else
                    {
                        if (_debug)
                            System.out.print("0");

                        setValue(param3, "0");
                    }

                    if (_debug)
                        System.out.println(" at location "+param3);

                    _instructionPointer += 4;  // move the pointer on.
                }
                break;
                case Instructions.RELATIVE_BASE:
                {
                    /*
                     * The relative base increases (or decreases, if the value is negative)
                     * by the value of the parameter.
                     */

                    int param1 = Integer.valueOf(getValue(_instructionPointer+1, modes[0], false));

                    _relativeBase += param1;

                    if (_debug)
                        System.out.println("Relative base now "+_relativeBase);

                    _instructionPointer += 2;
                }
                break;
                case Instructions.HALT:
                {
                    /*
                     * Means that the program is finished and should immediately halt.
                     */

                     if (_debug)
                        System.out.println("Halting execution.");

                     _instructionPointer = _memory.size();
                    _status = Status.HALTED;

                    return _status;
                }
                default:
                {
                    System.out.println("Unknown opcode "+str+" encountered");

                    _instructionPointer = _memory.size();  // stop any further execution.
                    _status = Status.HALTED;
                }
            }

            _status = Status.RUNNING;
        }

        return _status;
    }

    // these methods ensure capacity is available

    private String getValue (int index, int mode, boolean isOutput)
    {
        String param = null;
        
        switch (mode)
        {
            case ParameterMode.POSITION_MODE:
            {
                param = getValue(index);

                if (!isOutput)
                    param = getValue(Integer.valueOf(param));
            }
            break;
            case ParameterMode.IMMEDIATE_MODE:
            {
                param = getValue(index);
            }
            break;
            case ParameterMode.RELATIVE_MODE:
            {
                param = getValue(index);

                if (!isOutput)
                    param = getValue(Integer.valueOf(param) + _relativeBase);
                else
                    param = Integer.toString(Integer.valueOf(param) + _relativeBase);
            }
            break;
            default:
            {
                System.out.println("Unknown Parameter Mode found: "+mode);
                param = "-1";
            }
        }
        
        return param;
    }

    private String getValue (int i)
    {
        if (_memory.size() <= i)
            _memory.setSize(i+EXPANSION_FACTOR);

        String str = _memory.elementAt(i);

        if (str == null)
        {
            str = INITIALISED_MEMORY;
            _memory.set(i, str);
        }

        return str;
    }

    private void setValue (int i, String str)
    {
        if (_memory.size() <= i)
            _memory.setSize(i+EXPANSION_FACTOR);

        _memory.set(i, str);
    }

    private String getOpcode (String digits)
    {
        String opcode = null;

        if ((digits != null) && (digits.length() > 2))
            opcode = digits.substring(digits.length()-2);
        else
            opcode = digits;

        return opcode;
    }

    private boolean _debug;
    private int _instructionPointer;
    private Vector<String> _output;
    private Vector<String> _memory;
    private int _initialInput;
    private int _status;
    private int _relativeBase;

    private static final int EXPANSION_FACTOR = 10;  // if we need to increase memory, add a bit more to reduce multiple back-to-back expansions
}