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

        String str = "A";

        return str;
    }

    private String _fullCommand;
    private Vector<MovementFunction> _functions;
    private boolean _debug;
}