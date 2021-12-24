import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_INPUT = "example.txt";

    public static final int EXAMPLE_PREAMBLE = 5;
    public static final long[] EXAMPLE_SEQUENCE = { 15, 25, 47, 40 };
    public static final long EXAMPLE_TOTAL = 62;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Long> data = Util.loadData(EXAMPLE_INPUT, _debug);
        XMAS parser = new XMAS(_debug);
        Vector<Long> results = parser.validate(data, EXAMPLE_PREAMBLE);

        if (results.size() == 1)
        {
            Vector<Long> sequence = parser.findSequence(data, results.elementAt(0));

            if ((sequence != null) && (sequence.size() == 4))
            {
                for (int i = 0; i < sequence.size(); i++)
                {
                    if (EXAMPLE_SEQUENCE[i] != sequence.elementAt(i))
                    {
                        System.out.println("Incorrect sequence entry: "+sequence.elementAt(i));

                        return false;
                    }
                }

                // just to be safe ...

                if (Util.smallest(sequence) + Util.largest(sequence) == EXAMPLE_TOTAL)
                    return true;
            }
            else
                System.out.println("Incorrect sequence: "+sequence);
        }
        else
            System.out.println("Wrong number of invalid entries: "+results.size());

        return false;
    }

    private boolean _debug;
}