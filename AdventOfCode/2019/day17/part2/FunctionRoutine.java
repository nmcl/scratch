import java.util.*;

/*
 *
Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

= A

Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

= A

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6

= B

Commands: R,8
Commands: R,10
Commands: L,6

= C

Commands: R,8
Commands: R,10
Commands: L,6

= C

Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

= A

Commands: R,8
Commands: R,10
Commands: L,6

= C

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6

= B

Commands: R,8
Commands: R,10
Commands: L,6

= C

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6

= B
 */

public class FunctionRoutine
{
    public static final int NUMBER_OF_FUNCTIONS = 3;

    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;

    public static final int MAX_CHARACTERS = 20;

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

    public Vector<MovementRoutine> createMovementFunctions ()
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

        System.out.println("Full command "+fullCommand);

        /*
         * Find repeated strings. Assume minimum of 2 commands.
         */

        _functions = new Vector<MovementRoutine>();

        System.out.println("fullCommand length "+fullCommand.length());

        System.out.println("search string is "+fullCommand);

        MovementRoutine func = getFirstMovementRoutine(fullCommand, 2);

        System.out.println("First function is "+func+"\n");

        if (func != null)
        {
            fullCommand = fullCommand.replace(func.getCommand(), "");  // remove repeating commands
    
            recreateCommands(fullCommand);

            fullCommand = getCommandString();

            System.out.println("fullCommand now "+fullCommand);

            func = getLastMovementRoutine(fullCommand, 2);

            System.out.println("Last function is "+func+"\n");

            if (func != null)
            {
                fullCommand = fullCommand.replace(func.getCommand(), "");  // remove repeating commands

                System.out.println("fullCommand now "+fullCommand);

                recreateCommands(fullCommand);

                func = getFirstMovementRoutine(fullCommand, 2);

                if (func != null)
                {
                    System.out.println("Second function is "+func+"\n");

                    fullCommand = fullCommand.replace(func.getCommand(), "");  // remove repeating commands

                    fullCommand = fullCommand.replace(",,", ""); 

                    System.out.println("fullCommand now "+fullCommand+" and "+fullCommand.length());
                }
            }
        }

        return _functions;
    }

    /*
     * Return the first repeating function.
     * 
     * fullCommand is the String to search.
     * startingCommand is the command from which to begin the search.
     * numberOfCommands is the number of commands to pull together.
     */

    private MovementRoutine getFirstMovementRoutine (String commandString, int numberOfCommands)
    {
        System.out.println("getFirstMovementRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        return getMovementRoutine(commandString, 0, _commands.size(), numberOfCommands);
    }

    private MovementRoutine getLastMovementRoutine (String commandString, int numberOfCommands)
    {
        System.out.println("getLastMovementRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementRoutine routine = findLastRepeatRoutine(commandString, numberOfCommands);

        System.out.println("**etLastMovementRoutine got back "+routine);

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

    private MovementRoutine getMovementRoutine (String commandString, int startingCommand, int endCommand, int numberOfCommands)
    {
        System.out.println("getMovementRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementRoutine routine = findRepeatRoutine(commandString, startingCommand, endCommand, numberOfCommands);

        System.out.println("**getMovementRoutine got back "+routine);

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

    private MovementRoutine findRepeatRoutine (String commandString, int startingCommand, int endCommand, int numberOfCommands)
    {
        System.out.println("findRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        if (numberOfCommands < (startingCommand + endCommand))
        {
            String repeat = getCommandString(startingCommand, numberOfCommands);

            if (repeat.length() <= MAX_CHARACTERS)
            {
                if (commandString.indexOf(repeat, repeat.length()) != -1)  // it repeats so try another command
                {
                    System.out.println("Repeat: "+repeat);

                    MovementRoutine next = findRepeatRoutine(commandString, startingCommand, endCommand, numberOfCommands +1);

                    if (next == null)
                        return new MovementRoutine(repeat, numberOfCommands);
                    else
                        return next;
                }
                else
                    System.out.println("Does not repeat: "+repeat);
            }
            else
                System.out.println("Command string too long.");
        }

        return null;
    }

    private String getCommandString (int start, int numberOfCommands)
    {
        String str = "";

        System.out.println("getCommandString starting command "+start+" and number "+numberOfCommands);

        for (int i = start; i < (start + numberOfCommands); i++)
        {
            int commandNumber = _commands.size() - 1 - i;

            System.out.println("Adding command "+commandNumber);

            str += _commands.elementAt(commandNumber);

            if ((i+1) < (start + numberOfCommands))
                str += ",";
        }

        System.out.println("**Command string created: "+str);

        return str;
    }

    private MovementRoutine findLastRepeatRoutine (String commandString, int numberOfCommands)
    {
        System.out.println("findLastRoutine searching "+commandString+" with "+numberOfCommands+" commands");

        String repeat = getLastCommandString(numberOfCommands);

        System.out.println("Scanning for "+repeat);

        if (repeat.length() <= MAX_CHARACTERS)
        {
            if (commandString.indexOf(repeat, repeat.length()) != -1)  // it repeats so try another command
            {
                System.out.println("Repeat: "+repeat);

                MovementRoutine next = findLastRepeatRoutine(commandString, numberOfCommands +1);

                if (next == null)
                    return new MovementRoutine(repeat, numberOfCommands);
                else
                    return next;
            }
            else
                System.out.println("Does not repeat: "+repeat);
        }
        else
            System.out.println("Command string too long.");

        return null;
    }

    private String getLastCommandString (int numberOfCommands)
    {
        String str = "";

        System.out.println("getLastCommandString using "+numberOfCommands+" commands");

        for (int i = numberOfCommands -1; i >= 0; i--)
        {
            System.out.println("Adding command "+i);

            str += _commands.elementAt(i);

            if (i != 0)
                str += ",";
        }

        System.out.println("**Last command string created: "+str);

        return str;
    }

    private void recreateCommands (String sequence)
    {
        System.out.println("Recreating commands from "+sequence);

        Stack<String> temp = new Stack<String>();

        StringTokenizer tokeniser = new StringTokenizer(sequence, ",");

        while (tokeniser.hasMoreTokens())
        {
            String token1 = tokeniser.nextToken();
            String token2 = tokeniser.nextToken();

            String str = token1+","+token2;

            System.out.println("created "+str);

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

    private String getCommandString ()
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

    private Stack<String> _path;
    private Vector<MovementRoutine>_functions;
    private Vector<String> _commands;
    private boolean _debug;
}