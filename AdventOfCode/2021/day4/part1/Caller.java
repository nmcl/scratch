import java.util.*;

public class Caller
{
    public Caller (boolean debug)
    {
        _debug = debug;
    }

    public Result playTheGame (Vector<Integer> numbers, Vector<Board> boards)
    {
        for (int i = 0; i < numbers.size(); i++)
        {
            for (int j = 0; j < boards.size(); j++)
            {
                if (_debug)
                    System.out.println("Calling "+numbers.elementAt(i));

                boards.elementAt(j).call(numbers.elementAt(i));

                if (_debug)
                    System.out.println("Checking:\n"+boards.elementAt(j));

                if (boards.elementAt(j).completeLine())
                {
                    return new Result(boards.elementAt(j), numbers.elementAt(i));
                }
            }
        }

        return null;
    }

    private boolean _debug;
}