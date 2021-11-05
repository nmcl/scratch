import java.util.*;

public class Total
{
    public Total (Vector<Integer> list, boolean debug)
    {
        _numbers = list;
        _debug = debug;

        if (_debug)
            System.out.println("Size of numbers: "+_numbers.size());
    }

    public Integer[] sum (int total)
    {
        if (_debug)
            System.out.println("Checking for "+total);

        Integer[] toReturn = new Integer[2];
        boolean found = false;

        for (int i = 0; (i < _numbers.size()) && !found; i++)
        {
            toReturn[0] = _numbers.elementAt(i);

            if (_debug)
                System.out.println("First number: "+toReturn[0]);

            for (int j = i+1; (j < _numbers.size()) && !found; j++)
            {
                if (_debug)
                    System.out.println("Checking "+toReturn[0]+" and "+_numbers.elementAt(j));

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