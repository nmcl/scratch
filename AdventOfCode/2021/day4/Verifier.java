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

        if (_debug)
        {
            System.out.println("Loaded:\n");

            for (int i = 0; i < numbers.size(); i++)
                System.out.print(numbers.elementAt(i)+",");

            System.out.println("\n");

            for (int i = 0; i < boards.size(); i++)
                System.out.println(boards.elementAt(i)+"\n");
        }

        return false;
    }

    private boolean _debug;
}