import java.util.*;

public class FunctionRoutine
{
    public static final int MAX_CHARACTERS = 20;

    public static final String ROUTINE_A = "A";
    public static final String ROUTINE_B = "B";
    public static final String ROUTINE_C = "C";

    public static final int ROUTINE_A_INDEX = 0;
    public static final int ROUTINE_B_INDEX = 1;
    public static final int ROUTINE_C_INDEX = 2;

    public FunctionRoutine (Stack<String> pathTaken, boolean debug)
    {
        _path = pathTaken;
        _functions = null;

        /*
         * Now convert the path into a series of commands
         * such as L,4 or R,8.
         */

        createCommands();

        _debug = debug;
    }

    public FunctionRoutine (String sequence, boolean debug)
    {
        _path = null;
        _functions = null;

        recreateCommands(sequence);

        _debug = debug;
    }

    public Vector<MovementFunction> createMovementFunctions ()
    {
        /*
         * Now turn the series of commands into functions
         * A, B and C based on repeated commands.
         * 
         * There are only 3 possible functions.
         * 
         * This means one function always starts at the beginning.
         * 
         * One function always ends at the ending (!) assuming it's not a repeat
         * of the first.
         * 
         * Then using both the first and the last fragment to find the third
         * and split the entire sequence into functions.
         */

        String fullCommand = getCommandString();

        if (_debug)
            System.out.println("Full command "+fullCommand);

        /*
         * Find repeated strings. Assume minimum of 2 commands.
         */

        _functions = new Vector<MovementFunction>();

        MovementFunction func = getFirstMovementFunction(fullCommand, 2);

        func.setName(ROUTINE_A);

        if (_debug)
            System.out.println("First function is "+func+"\n");

        if (func != null)
        {
            fullCommand = fullCommand.replace(func.getCommand(), "");  // remove repeating commands
    
            recreateCommands(fullCommand);

            fullCommand = getCommandString();

            func = getLastMovementFunction(fullCommand, 2);

            func.setName(ROUTINE_B);

            if (_debug)
                System.out.println("Last function is "+func+"\n");

            if (func != null)
            {
                fullCommand = fullCommand.replace(func.getCommand(), "");  // remove repeating commands

                recreateCommands(fullCommand);

                func = getFirstMovementFunction(fullCommand, 2);

                /*
                 * This could return null if the remaining string has more than
                 * one way to break it up and we choose the coarsest option which
                 * means we only need 2 functions. In which case we should go back
                 * and try with less commands.
                 */

                func.setName(ROUTINE_C);

                if (func != null)
                {
                    if (_debug)
                        System.out.println("Second function is "+func+"\n");

                    fullCommand = fullCommand.replace(func.getCommand(), "");  // remove repeating commands

                    fullCommand = fullCommand.replace(",,", ""); // remove duplicates

                    if (fullCommand.length() != 0)
                        System.out.println("Commands still remaining! "+fullCommand);
                }
            }
        }

        return _functions;
    }

    public final String getCommandString ()
    {
        String fullCommand = "";

        for (int i = _commands.size() -1; i >= 0; i--)
        {
            fullCommand += _commands.elementAt(i);

            if (i != 0)
                fullCommand += ",";
        }

        return fullCommand;
    }

    /*
     * Return the first repeating function.
     * 
     * fullCommand is the String to search.
     * startingCommand is the command from which to begin the search.
     * numberOfCommands is the number of commands to pull together.
     */

    private MovementFunction getFirstMovementFunction (String commandString, int numberOfCommands)
    {
        if (_debug)
            System.out.println("getFirstMovementFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        return getMovementFunction(commandString, 0, _commands.size(), numberOfCommands);
    }

    private MovementFunction getLastMovementFunction (String commandString, int numberOfCommands)
    {
        if (_debug)
            System.out.println("getLastMovementFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementFunction routine = findLastRepeatFunction(commandString, numberOfCommands);

        if (routine == null)
        {
            System.out.println("Error - no repeating function!");

            return null;
        }
        else
        {
            _functions.add(routine);
            
            return routine;
        }
    }

    private MovementFunction getMovementFunction (String commandString, int startingCommand, int endCommand, int numberOfCommands)
    {
        if (_debug)
            System.out.println("getMovementFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementFunction routine = findRepeatFunction(commandString, startingCommand, endCommand, numberOfCommands);

        if (routine == null)
        {
            System.out.println("Error - no repeating function!");

            return null;
        }
        else
        {
            _functions.add(routine);
            
            return routine;
        }
    }

    private MovementFunction findRepeatFunction (String commandString, int startingCommand, int endCommand, int numberOfCommands)
    {
        if (_debug)
            System.out.println("findRepeatFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        if (numberOfCommands < (startingCommand + endCommand))
        {
            String repeat = getCommandString(startingCommand, numberOfCommands);

            if (repeat.length() <= MAX_CHARACTERS)
            {
                if (commandString.indexOf(repeat, repeat.length()) != -1)  // it repeats so try another command
                {
                    MovementFunction next = findRepeatFunction(commandString, startingCommand, endCommand, numberOfCommands +1);

                    if (next == null)
                        return new MovementFunction(repeat, numberOfCommands);
                    else
                        return next;
                }
                else
                {
                    if (_debug)
                        System.out.println("Does not repeat: "+repeat);
                }
            }
            else
            {
                if (_debug)
                    System.out.println("Command string too long.");
            }
        }

        return null;
    }

    private String getCommandString (int start, int numberOfCommands)
    {
        String str = "";

        for (int i = start; i < (start + numberOfCommands); i++)
        {
            int commandNumber = _commands.size() - 1 - i;

            str += _commands.elementAt(commandNumber);

            if ((i+1) < (start + numberOfCommands))
                str += ",";
        }

        return str;
    }

    private MovementFunction findLastRepeatFunction (String commandString, int numberOfCommands)
    {
        if (_debug)
            System.out.println("findLastRepeatFunction searching "+commandString+" with "+numberOfCommands+" commands");

        String repeat = getLastCommandString(numberOfCommands);

        if (_debug)
            System.out.println("Scanning for "+repeat);

        if (repeat.length() <= MAX_CHARACTERS)
        {
            if (commandString.indexOf(repeat, repeat.length()) != -1)  // it repeats so try another command
            {
                MovementFunction next = findLastRepeatFunction(commandString, numberOfCommands +1);

                if (next == null)
                    return new MovementFunction(repeat, numberOfCommands);
                else
                    return next;
            }
            else
                System.out.println("Does not repeat: "+repeat);
        }
        else
        {
            if (_debug)
                System.out.println("Command string too long.");
        }

        return null;
    }

    private String getLastCommandString (int numberOfCommands)
    {
        String str = "";

        for (int i = numberOfCommands -1; i >= 0; i--)
        {
            str += _commands.elementAt(i);

            if (i != 0)
                str += ",";
        }

        return str;
    }

    private void recreateCommands (String sequence)
    {
        Stack<String> temp = new Stack<String>();

        StringTokenizer tokeniser = new StringTokenizer(sequence, ",");

        while (tokeniser.hasMoreTokens())
        {
            String token1 = tokeniser.nextToken();
            String token2 = tokeniser.nextToken();
            String str = token1+","+token2;

            temp.push(str);
        }

        _commands = new Vector<String>();

        boolean end = false;

        while (!end)
        {
            try
            {
                String command = temp.pop();

                _commands.add(command);
            }
            catch (Exception ex)
            {
                end = true;
            }
        }
    }

    private void createCommands ()
    {
        String pathElement = null;

        _commands = new Vector<String>(_path.size());

        /*
         * Pop the track to reverse it and get commands from the
         * starting position.
         */

        do
        {
            try
            {
                pathElement = _path.pop();

                String str = pathElement.charAt(0)+","+pathElement.length();

                _commands.add(str);
            }
            catch (Exception ex)
            {
                pathElement = null;
            }

        } while (pathElement != null);

        if (_debug)
        {
            for (int i = _commands.size() -1; i >= 0; i--)
            {
                System.out.println("Commands: "+_commands.elementAt(i));
            }
        }
    }

    private Stack<String> _path;
    private Vector<MovementFunction>_functions;
    private Vector<String> _commands;
    private boolean _debug;
}