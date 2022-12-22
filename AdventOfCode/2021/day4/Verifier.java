import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Vector<Integer> numbers = Util.loadNumbers(EXAMPLE_FILE, _debug);
        Vector<Board> boards = Util.loadBoards(EXAMPLE_FILE, _debug);

        return false;
    }

    private boolean _debug;
}