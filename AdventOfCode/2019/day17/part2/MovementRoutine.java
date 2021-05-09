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
        String searchStr = _fullCommand.substring(_functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getCommand().length()+1);
        int index = _functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getLength();

        System.out.println("index "+index);
        System.out.println("fullCommand "+_fullCommand.length());

        while (index < _fullCommand.length())
        {
            System.out.println("index "+index);

            System.out.println("Searching for "+_functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getCommand());

            System.out.println("search string is "+searchStr);

            if (searchStr.startsWith(_functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getCommand()))
            {
                str += ","+FunctionRoutine.ROUTINE_A;

                System.out.println("Found "+str);

                index += _functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getLength();

                searchStr = searchStr.substring(_functions.elementAt(FunctionRoutine.ROUTINE_A_INDEX).getLength()+1);
            }
            else
            {
                System.out.println("Searching for "+_functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getCommand());

                if (searchStr.startsWith(_functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getCommand()))
                {
                    str += ","+FunctionRoutine.ROUTINE_B;

                    System.out.println("Found "+str);

                    index += _functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getLength();

                    searchStr = searchStr.substring(_functions.elementAt(FunctionRoutine.ROUTINE_B_INDEX).getLength()+1);
                }
                else
                {
                    System.out.println("Searching for "+_functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getCommand());

                    if (searchStr.startsWith(_functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getCommand()))
                    {
                        str += ","+FunctionRoutine.ROUTINE_C;

                        System.out.println("Found "+str);

                        index += _functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getLength();

                        searchStr = searchStr.substring(_functions.elementAt(FunctionRoutine.ROUTINE_C_INDEX).getLength()+1);
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