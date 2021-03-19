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
            results = processPhase(phaseInput);

            //phaseInput = results;
        }

        return results;
    }

    private int[] processPhase (int[] input)
    {
        int[] results = new int[input.length];
        int basePatternIndex = 0;
        int basePatternLowerBounds = 1;

        for (int i = 0; i < input.length; i++)
        {
            int value = 0;

            for (int j = 0; j < input.length; j++)
            {
                int[] work = new int[input.length];

                work[j] = input[j] * BASE_PATTERN[basePatternIndex];

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