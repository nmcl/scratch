import java.util.*;

public class Caller
{
    public Caller (boolean debug)
    {
        _debug = debug;
    }

    public Board playTheGame (Vector<Integer> numbers, Vector<Board> boards)
    {
        for (int i = 0; i < numbers.size(); i++)
        {
            for (int j = 0; j < boards.size(); j++)
            {
                boards.elementAt(j).call(numbers.elementAt(i));

                if (boards.elementAt(j).completeLine())
                    return boards.elementAt(j);
            }
        }

        return null;
    }

    private boolean _debug;
}