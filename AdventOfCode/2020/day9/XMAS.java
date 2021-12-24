import java.util.*;

public class XMAS
{
    public XMAS (boolean debug)
    {
        _debug = debug;
    }

    public Vector<Integer> validate (Vector<Integer> input, int preamble)
    {
        Vector<Integer> invalid = new Vector<Integer>();
        int index = 0;

        for (int i = preamble; i < input.size(); i++)
        {
            if (!sums(input.elementAt(i), input, index, preamble))
                invalid.add(input.elementAt(i));
            
            index++;
        }

        return invalid;
    }

    private boolean sums (int total, Vector<Integer> input, int start, int preamble)
    {
        for (int i = start; i < (start + preamble -1); i++)
        {
            if (_debug)
                System.out.println("Trying to total "+total+" using "+input.elementAt(i)+" and "+input.elementAt(i +1));

            if (input.elementAt(i) + input.elementAt(i +1) == total)
            {
                if (_debug)
                    System.out.println("Total "+total+" found.");

                return true;
            }
        }

        if (_debug)
            System.out.println(total+" is not the sum of two entries.");

        return false;
    }

    private boolean _debug;
}