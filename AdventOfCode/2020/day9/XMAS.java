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
            if (!sums(elementAt(i), input, index, preamble))
                invalid.add(elementAt(i));
            
            index++;
        }

        return invalid;
    }

    private boolean sums (int total, Vector<Integer> input, int start, int preanble)
    {
        for (int j = start; j < (start + preamble -1); j++)
        {
            if (input.elementAt(index) + input.elementAt(index +1) == total)
                return true;
        }

        return false;
    }

    private boolean _debug;
}