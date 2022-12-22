import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    
    public Verifier ()
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Vector<Integer> numbers = Util.loadNumbers(EXAMPLE_FILE, debug);
        Vector<Board> boards = Util.loadBoards(EXAMPLE_FILE, debug);
        
        return false;
    }

    private boolean _debug;
}