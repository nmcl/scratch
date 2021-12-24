import java.util.*;

public class XMAS
{
    public XMAS (boolean debug)
    {
        _debug = debug;
    }

    public Vector<Long> validate (Vector<Long> input, int preamble)
    {
        Vector<Long> invalid = new Vector<Long>();
        int index = 0;

        for (int i = preamble; i < input.size(); i++)
        {
            if (!sums(input.elementAt(i), input, index, preamble))
                invalid.add(input.elementAt(i));
            
            index++;
        }

        return invalid;
    }

    public Vector<Long> findSequence (Vector<Long> input, long total)
    {
        int start = 0;
        int end = 0;

        while (start < input.size())
        {
            boolean stop = false;
            int runningTotal = 0;
            Vector<Long> seq = new Vector<Long>();

            for (int i = start; (i < input.size()) && !stop; i++)
            {
                runningTotal += input.elementAt(i);

                seq.add(input.elementAt(i));

                if ((runningTotal == total) && (input.elementAt(i) != total))
                    return seq;
                
                if (runningTotal > total)
                    stop = true;
            }

            start++;
        }

        return null;
    }

    private boolean sums (long total, Vector<Long> input, int start, int preamble)
    {
        //System.out.println("outer range: "+start+" to "+(start + preamble -1));

        for (int i = start; i < (start + preamble -1); i++)
        {
            //System.out.println("inner range "+(i+1)+" to "+(start + preamble));

            for (int j = i +1; j < (start + preamble); j++)
            {
                if (_debug)
                    System.out.println("Trying to total "+total+" using "+input.elementAt(i)+" and "+input.elementAt(j));

                if (input.elementAt(i) + input.elementAt(j) == total)
                {
                    if (_debug)
                        System.out.println("Total "+total+" found.");

                    return true;
                }
            }
        }

        if (_debug)
            System.out.println(total+" is not the sum of two entries.");

        return false;
    }

    private boolean _debug;
}