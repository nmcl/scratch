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

        String fullCommand = "";

        for (int i = _commands.size() -1; i >= 0; i--)
        {
            fullCommand += _commands.elementAt(i);

            if (i != 0)
                fullCommand += ",";
        }

        System.out.println("Full command "+fullCommand);

        /*
         * Find repeated strings. Assume minimum of 2 commands.
         */

        String str = fullCommand;

        _functions = new Vector<MovementRoutine>();

        System.out.println("fullCommand length "+fullCommand.length());

        System.out.println("search string is "+str);

        MovementRoutine func = getFirstMovementRoutine(str, 2);

        System.out.println("First function is "+func+"\n");

        if (func != null)
        {
            // int commandStart = func.numberOfCommands();
    
            str = str.replace(func.getCommand(), "");
    
            recreateCommands(str);

            func = getLastMovementRoutine(str, 2);

            System.out.println("Last function is "+func+"\n");

            System.exit(0);
/*
            if (func != null)
            {
                int commandEnd = func.numberOfCommands();

                str = str.replace(func.getCommand(), "");

                func = getMovementRoutine(str, commandStart, commandEnd, 2);
            }*/
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

        MovementRoutine routine = getMovementRoutine(commandString, 0, _commands.size(), numberOfCommands);

        System.out.println("**got back "+routine);

        if (routine == null)
        {
            System.out.println("Error - no repeating function!");

            return null;
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

    private MovementRoutine getLastMovementRoutine (String commandString, int numberOfCommands)
    {
        System.out.println("getLastMovementRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementRoutine routine = findLastRepeatRoutine(commandString, numberOfCommands);

        System.out.println("**got back "+routine);

        if (routine == null)
        {
            System.out.println("Error - no repeating function!");

            return null;
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

    private MovementRoutine getMovementRoutine (String commandString, int startingCommand, int endCommand, int numberOfCommands)
    {
        System.out.println("getUniqueFunction searching "+commandString+" with "+numberOfCommands+" number of commands");

        MovementRoutine routine = findRepeatRoutine(commandString, startingCommand, endCommand, numberOfCommands);

        System.out.println("**got back "+routine);

        if (routine == null)
        {/*
            String repeat = getRemainingRoutine(startingCommand);

            routine = new MovementRoutine(repeat, _commands.size() - startingCommand);

            *
             * Not a repeat but maybe it's part of an existing function? Or maybe
             * an existing routine is within the String?
             *

            Vector<MovementRoutine> embedded = findEmbeddedRoutine(routine);

            System.out.println("After checking, commands used "+routine.numberOfCommands());

            if (routine.numberOfCommands() > 0)
            {
                routine = findRoutineFromPartial(routine);
            }
            else
                routine = embedded.elementAt(embedded.size() -1);  // the last entry;
            
            return routine;*/

            return null;
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

    private MovementRoutine findRepeatRoutine (String commandString, int startingCommand, int endCommand, int numberOfCommands)
    {
        System.out.println("findRoutine searching "+commandString+" with "+numberOfCommands+" number of commands");

        if (numberOfCommands < (startingCommand + endCommand))
        {
            String repeat = getCommandString(startingCommand, numberOfCommands);

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

            if (i < (start + numberOfCommands))
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
        _commands = new Vector<String>();

        StringTokenizer tokeniser = new StringTokenizer(sequence, ",");

        while (tokeniser.hasMoreTokens())
        {
            String token1 = tokeniser.nextToken();
            String token2 = tokeniser.nextToken();

            String str = token1+","+token2;

            System.out.println("created "+str);

            _commands.add(str);
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
    private Vector<MovementRoutine>_functions;
    private Vector<String> _commands;
    private boolean _debug;
}