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
            results = processPhase(input);

            //phaseInput = results;
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
     * 010-1
     * 001100-1-1
     * 000111000-1-1-1
     * 000011110000-1-1-1-1
     * 000001111100000-1-1-1-1-1
     */

    private int[] processPhase (int[] input)
    {
        int[] results = new int[input.length];
        int basePatternIndex = 0;
        int basePatternLoop = 1;
        int basePatternLowerBounds = 1;

        for (int i = 0; i < input.length; i++)
        {
            int value = 0;

            for (int j = 0; j < input.length; j++)
            {
                int[] work = new int[input.length];
                int pattern = BASE_PATTERN[basePatternIndex];


                work[j] = input[j] * BASE_PATTERN[basePatternIndex];

                System.out.println(input[j]+"*"+BASE_PATTERN[basePatternIndex]);

                basePatternIndex++;

                if (basePatternIndex == BASE_PATTERN.length)
                    basePatternIndex = basePatternLowerBounds;

                value += work[j];
            }

            results[i] = value;
        }

        return results;
    }

    boolean _debug;
}