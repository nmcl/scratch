public class Verifier
{
    public final int[] EXAMPLE_1 = { 0, 3, 6 };
    public final int EXAMPLE_1_RESULT = 175594;
    public final int[] EXAMPLE_2 = { 1, 3, 2 };
    public final int EXAMPLE_2_RESULT = 2578;
    public final int[] EXAMPLE_3 = { 2, 1, 3 };
    public final int EXAMPLE_3_RESULT = 3544142;
    public final int[] EXAMPLE_4 = { 1, 2, 3 };
    public final int EXAMPLE_4_RESULT = 261214;
    public final int[] EXAMPLE_5 = { 2, 3, 1 };
    public final int EXAMPLE_5_RESULT = 6895259;
    public final int[] EXAMPLE_6 = { 3, 2, 1 };
    public final int EXAMPLE_6_RESULT = 18;
    public final int[] EXAMPLE_7 = { 3, 1, 2 };
    public final int EXAMPLE_7_RESULT = 362;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        if (check(EXAMPLE_1, EXAMPLE_1_RESULT))
        {
            if (check(EXAMPLE_2, EXAMPLE_2_RESULT))
            {
                if (check(EXAMPLE_3, EXAMPLE_3_RESULT))
                {
                    if (check(EXAMPLE_4, EXAMPLE_4_RESULT))
                    {
                        if (check(EXAMPLE_5, EXAMPLE_5_RESULT))
                        {
                            if (check(EXAMPLE_6, EXAMPLE_6_RESULT))
                            {
                                if (check(EXAMPLE_7, EXAMPLE_7_RESULT))
                                    return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean check (int[] initial, int result)
    {
        Memory mem = new Memory(_debug);
        Integer lastNumberSpoken = speakInitial(initial, mem);

        for (int i = 3; i < 30000000; i++)
        {
            if (_debug)
                System.out.println("Turn number "+i+" spoke "+lastNumberSpoken);

            if (mem.firstTimeSpoken(lastNumberSpoken))
                lastNumberSpoken = 0;
            else
                lastNumberSpoken = mem.getTurnDifference(lastNumberSpoken);

            mem.speakNumber(lastNumberSpoken);
        }

        if (lastNumberSpoken == result)
            return true;
        else
            System.out.println("Incorrect final value: "+lastNumberSpoken);

        return false;
    }

    private Integer speakInitial (int[] initial, Memory mem)
    {
        for (int i = 0; i < initial.length; i++)
        {
            mem.speakNumber(initial[i]);
        }

        return initial[initial.length -1];
    }

    private boolean _debug;
}