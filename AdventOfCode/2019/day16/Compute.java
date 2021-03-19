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

            phaseInput = results;
        }

        return results;
    }

    private int[] processPhase (int[] input)
    {
        int[] results = null;

        return results;
    }

    boolean _debug;
}