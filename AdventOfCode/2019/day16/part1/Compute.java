public class Compute
{
    public static final int[] BASE_PATTERN = {0, 1, 0, -1};

    public Compute (boolean debug)
    {
        _debug = debug;
    }

    public int[] process (int[] input, int numberOfPhases)
    {
        int[] results = input;

        for (int i = 0; i < numberOfPhases; i++)
        {
            results = processPhase(results);
        }

        return results;
    }

    private int[] processPhase (int[] input)
    {
        int[] results = new int[input.length];

        for (int i = 0; i < input.length; i++)
        {
            int[] basePattern = getBasePattern(i+1, input.length);
            int runningTotal = 0;

            for (int j = 0; j < basePattern.length; j++)
            {
                int[] work = new int[input.length];

                work[j] = input[j] * basePattern[j];

                if (_debug)
                    System.out.println(input[j]+"*"+basePattern[j]);

                runningTotal += work[j];
            }

            results[i] = Math.abs(runningTotal)%10;
        }

        return results;
    }

    /*
     * A reminder of the example ...
     * 
     * 10-1010-10
     * 01100-1-10
     * 00111000
     * 00011110
     * 00001111
     * 00000111
     * 00000011
     * 00000001
     * 
     * 10-1010-1010-1010-1  --> miss first digit [010-1010-1010-1]
     * 01100-1-1001100-1-1  --> miss first digit [001100-1-1]
     * 00111000-1-1-1000111000-1-1-1  --> miss first digit [000111000-1-1-1]
     * 00011110000-1-1-1-1000011110000-1-1-1-1  --> miss first digit [000011110000-1-1-1-1]
     * 00001111100000-1-1-1-1-1  --> miss first digit [000001111100000-1-1-1-1-1]
     */

    private final int[] getBasePattern (int phase, int size)
    {
        int[] data = new int[size+1];
        int basePtr = 0;
        int loop = 0;

        for (int i = 0; i < data.length; i++)
        {
            data[i] = BASE_PATTERN[basePtr];

            if (_debug)
                System.out.println("Base pattern entry "+i+" is "+data[i]);

            loop++;

            if (loop == phase)
            {
                loop = 0;
                basePtr++;

                if (basePtr == BASE_PATTERN.length)
                    basePtr = 0;
            }
        }

        // crop the first element

        int[] toReturn = new int[size];

        for (int j = 0; j < size; j++)
            toReturn[j] = data[j+1];

        return toReturn;
    }

    private boolean _debug;
}