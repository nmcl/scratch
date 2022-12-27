import java.util.*;

public class Caller
{
    public Caller (boolean debug)
    {
        _debug = debug;
    }

    public Integer[] playTheGame (Vector<Integer> numbers, Vector<Board> boards)
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

                Integer[] line = boards.elementAt(j).completeLine();

                if (line != null)
                    return line;
            }
        }

        return null;
    }

    private boolean _debug;
}