import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_SUM = 188;
    public static final int EXAMPLE_NUMBER = 24;
    
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

        Caller c = new Caller(_debug);
        Result r = c.playTheGame(numbers, boards);

        System.out.println("Result:\n\n"+r);

        return false;
    }

    private boolean _debug;
}