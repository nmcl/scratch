import java.util.*;

/*
 * Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

L,4L,4L,6R,10L,6

== A

Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

L,4L,4L,6R,10L,6

== A

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6
Commands: R,8
Commands: R,10
Commands: L,6

L,12L,6R,10L,6R,8R,10L,6

== B

Commands: R,8
Commands: R,10
Commands: L,6

R,8R,10L,6

== C

Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

L,4L,4L,6R,10L,6

== A

Commands: R,8
Commands: R,10
Commands: L,6

R,8R,10L,6

== C

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6
Commands: R,8
Commands: R,10
Commands: L,6

L,12L,6R,10L,6R,8R,10L,6

== B

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6

L,12L,6R,10L,6

== B
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
        _functions = new Vector<MovementRoutine>();
        _debug = debug;
    }

    public void createMovementFunctions ()
    {
        /*
         * Now convert the path into a series of commands
         * such as L,4 or R,8.
         */

        Vector<String> commands = getCommands();

        /*
         * Now turn the series of commands into functions
         * A, B and C based on repeated commands.
         * 
         * There are only 3 possible functions.
         * This means one function always starts at the beginning.
         * One function always ends at the ending (!) assuming it's not a repeat
         * of the first.
         * Then using both the first and the last fragment to find the third
         * and split the entire sequence into functions.
         * 
         * Also it's possible to give the Robot more commands than it can
         * execute before it gets to end of the route.
         */

        String fullCommand = "";

        for (int i = commands.size() -1; i >= 0; i--)
        {
            fullCommand += commands.elementAt(i);
        }

        System.out.println("Full command "+fullCommand);

        /*
         * Find repeated strings. Assume minimum of 2 commands.
         */

        int commandStart = 0;
        int startString = 0;
        String str = fullCommand;

        System.out.println("fullCommand length "+fullCommand.length());

        do
        {
            System.out.println("startString "+startString);

            if (startString != 0)
                str = fullCommand.substring(startString);

            System.out.println("str is "+str);

            MovementRoutine func = getFunction(commands, str, commandStart, 2);

            _functions.add(func);

            System.out.println("Function is "+func+"\n");

            commandStart += func.numberOfCommands();
            startString += func.getLength();

            System.out.println("Total commands used so far: "+commandStart);

            System.out.println("left "+str);

        } while (str != null);
    }

    /*
     * Return a repeating function. Will return all repeating
     * functions given the input so we need to later figure out
     * the unique instances afterwards.
     * 
     * fullCommand is the String to search.
     * startingCommand is the command from which to begin the search.
     * numberOfCommands is the number of commands to pull together.
     */

    private MovementRoutine getFunction (Vector<String> commands, String commandString, int startingCommand, int numberOfCommands)
    {
        System.out.println("getFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        String repeat = getCommandString(commands, startingCommand, numberOfCommands);
        MovementRoutine next = new MovementRoutine(repeat, numberOfCommands);

        System.out.println("**checking "+next);

        if (_functions.indexOf(next) != -1) // already found repeating commands/Function
        {
            System.out.println("Function "+next+" is a duplicate!");

            return next;
        }

        if (commandString.indexOf(repeat, repeat.length()) != -1)
        {
            System.out.println("Repeat: "+repeat);

            next = getFunction(commands, commandString, startingCommand, numberOfCommands +1);

            if (next == null)
                return new MovementRoutine(repeat, numberOfCommands);
            else
                return next;
        }
        else
            System.out.println("Does not repeat: "+repeat);

        return null;
    }

    private String getCommandString (Vector<String> commands, int start, int number)
    {
        String str = "";

        System.out.println("getCommandString starting command "+start+" and number "+number);

        for (int i = start; i < (start + number); i++)
        {
            int commandNumber = commands.size() - 1 - i;

            System.out.println("Adding command "+commandNumber);

            str += commands.elementAt(commandNumber);
        }

        System.out.println("**Command string created: "+str);

        return str;
    }

    private Vector<String> getCommands ()
    {
        Vector<String> commands = new Vector<String>(_path.size());
        String pathElement = null;

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

                commands.add(str);
            }
            catch (Exception ex)
            {
                pathElement = null;
            }

        } while (pathElement != null);

        if (_debug)
        {
            for (int i = commands.size() -1; i >= 0; i--)
            {
                System.out.println("Commands: "+commands.elementAt(i));
            }
        }

        return commands;
    }

    private Stack<String> _path;
    private Vector<MovementRoutine>_functions;
    private boolean _debug;
}