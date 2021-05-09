import java.util.*;

public class MovementRoutine
{
    public MovementRoutine (String fullCommand, Vector<MovementFunction> functions, boolean debug)
    {
        _fullCommand = fullCommand;
        _functions = functions;
        _debug = debug;
    }

    public String getMainRoutine ()
    {
        /*
         * We know that Function A (1) is first in the sequence, followed by
         * B (2) and then C (3) is last but we don't know the order they follow
         * after the first.
         */

        String str = FunctionRoutine.ROUTINE_A;
        int index = _functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getLength();

        System.out.println("index "+index);
        System.out.println("fullCommand "+_fullCommand.length());

        while (index < _fullCommand.length())
        {
            System.out.println("Searching for "+_functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getCommand());

            if (_fullCommand.startsWith(_functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getCommand(), index))
            {
                str += ","+FunctionRoutine.ROUTINE_A;

                index += _functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getLength();
            }
            else
            {
                System.out.println("Searching for "+_functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getCommand());

                if (_fullCommand.startsWith(_functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getCommand(), index))
                {
                    str += str += ","+FunctionRoutine.ROUTINE_B;

                    index += _functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getLength();
                }
                else
                {
                    System.out.println("Searching for "+_functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getCommand());

                    if (_fullCommand.startsWith(_functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getCommand(), index))
                    {
                        str += str += ","+FunctionRoutine.ROUTINE_C;

                        index += _functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getLength();
                    }
                    else
                    {
                        System.out.println("Error, no functions found in commannd string "+_fullCommand);

                        System.exit(0);
                    }
                }
            }
        }

        return str;
    }

    private String _fullCommand;
    private Vector<MovementFunction> _functions;
    private boolean _debug;
}