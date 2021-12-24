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
            int toCheck = input.elementAt(i);

            for (int j = index; j < preamble; j++)
            {
                
            }
        }

        return invalid;
    }

    private boolean _debug;
}