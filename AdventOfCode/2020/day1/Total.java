import java.util.*;

public class Total
{
    public Total (Vector<Integer> list, boolean debug)
    {
        _numbers = list;
        _debug = debug;
    }

    public Integer[] sum (int total)
    {
        Integer[] toReturn = new Integer[2];
        boolean found = false;

        for (int i = 0; (i < _numbers.size()) && !found; i++)
        {
            toReturn[0] = _numbers.elementAt(i);

            for (int j = i; (j < _numbers.size()) && !found; j++)
            {
                if (toReturn[0] + _numbers.elementAt(j) == total)
                {
                    toReturn[1] = _numbers.elementAt(j);

                    found = true;
                }
            }
        }

        if (found)
            return toReturn;
        else
            return null;
    }

    private Vector<Integer> _numbers;
    private boolean _debug;
}