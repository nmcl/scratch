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
        _functions = new Vector<MovementRoutine>();

        /*
         * Now convert the path into a series of commands
         * such as L,4 or R,8.
         */

        _commands = getCommands();
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

        String fullCommand = "";

        for (int i = _commands.size() -1; i >= 0; i--)
        {
            fullCommand += _commands.elementAt(i);
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

            MovementRoutine func = getMovementRoutine(str, commandStart, 2);

            _functions.add(func);

            System.out.println("Function is "+func+"\n");

            commandStart += func.numberOfCommands();
            startString += func.getLength();

            System.out.println("Total commands used so far: "+commandStart);

            System.out.println("startString "+startString);

        } while (startString < fullCommand.length());

        return _functions;
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

    private MovementRoutine getMovementRoutine (String commandString, int startingCommand, int numberOfCommands)
    {
        System.out.println("getUniqueFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementRoutine routine = findRepeatRoutine(commandString, startingCommand, numberOfCommands);

        System.out.println("**got back "+routine);

        if (routine == null)
        {
            String repeat = getRemainingRoutine(startingCommand);

            routine = new MovementRoutine(repeat, _commands.size() - startingCommand);

            /*
             * Not a repeat but maybe it's part of an existing function? Or maybe
             * an existing routine is within the String?
             */

            Vector<MovementRoutine> embedded = findEmbeddedRoutine(routine);

            System.out.println("After checking, commands used "+routine.numberOfCommands());

            if (routine.numberOfCommands() > 0)
            {
                routine = findRoutineFromPartial(routine);
            }
            else
                routine = embedded.elementAt(embedded.size() -1);  // the last entry;
            
            return routine;
        }
        else
        {
            /*
             * Is this a unique function? If so, add it to
             * the list.
             */

             if (!_functions.contains(routine))
                _functions.add(routine);
            
            return routine;
        }
    }

    private MovementRoutine findRoutineFromPartial (MovementRoutine toCheck)  // return the full routine one way or another
    {
        Enumeration<MovementRoutine> iter = _functions.elements();

        while (iter.hasMoreElements())
        {
            MovementRoutine temp = iter.nextElement();

            System.out.println("Comparing "+temp+" with "+toCheck);

            if (temp.containsRoutine(toCheck))  // since no duplicates we know this can only happen once per function
            {
                System.out.println("Found full routine "+temp+" for partial routine "+toCheck);

                return temp;
            }
        }

        return toCheck;
    }

    private Vector<MovementRoutine> findEmbeddedRoutine (MovementRoutine toCheck)
    {
        Vector<MovementRoutine> toReturn = new Vector<MovementRoutine>();
        Enumeration<MovementRoutine> iter = _functions.elements();

        while (iter.hasMoreElements())
        {
            MovementRoutine temp = iter.nextElement();

            if (toCheck.containsRoutine(temp))  // since no duplicates we know this can only happen once per function
            {
                toCheck.removeRoutine(temp);  // update the routine contents along the way

                System.out.println("Found embedded routine "+temp);
                System.out.println("Remaining "+toCheck);
            }
        }

        return toReturn;
    }

    private String getRemainingRoutine (int start)
    {
        String str = "";

        System.out.println("getRemainingRoutine pulling remaining commands from "+start);

        for (int i = start; i < _commands.size(); i++)
        {
            int commandNumber = _commands.size() - 1 - i;

            System.out.println("Adding command "+commandNumber);

            str += _commands.elementAt(commandNumber);
        }

        System.out.println("**Command string created: "+str);

        return str;
    }

    private MovementRoutine findRepeatRoutine (String commandString, int startingCommand, int numberOfCommands)
    {
        System.out.println("findRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        String repeat = getCommandString(startingCommand, numberOfCommands);

        if (commandString.indexOf(repeat, repeat.length()) != -1)  // it repeats so try another command
        {
            System.out.println("Repeat: "+repeat);

            MovementRoutine next = findRepeatRoutine(commandString, startingCommand, numberOfCommands +1);

            if (next == null)
                return new MovementRoutine(repeat, numberOfCommands);
            else
                return next;
        }
        else
            System.out.println("Does not repeat: "+repeat);

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
        }

        System.out.println("**Command string created: "+str);

        return str;
    }

    private String getLastCommandString (int start, int numberOfCommands)
    {
        String str = "";

        System.out.println("getLastCommandString using "+numberOfCommands+" commands");

        for (int i = _commands.size() -numberOfCommands -1; i < _commands.size(); i++)
        {
            int commandNumber = i;

            System.out.println("Adding command "+commandNumber);

            str += _commands.elementAt(commandNumber);
        }

        System.out.println("**Last command string created: "+str);

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
    private Vector<String> _commands;
    private boolean _debug;
}