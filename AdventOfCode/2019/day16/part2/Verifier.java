public class Verifier
{
    public static final String INPUT_SIGNAL_1 = "03036732577212944063491565474664";
    public static final int[] SIGNAL_1_RESULT = {8, 4, 4, 6, 2, 0, 2, 6};

    public static final String INPUT_SIGNAL_2 = "02935109699940807407585447034323";
    public static final int[] SIGNAL_2_RESULT = {7, 8, 7, 2, 5, 2, 7, 0};

    public static final String INPUT_SIGNAL_3 = "03081770884921959731165446850517";
    public static final int[] SIGNAL_3_RESULT = {5, 3, 5, 5, 3, 7, 3, 1};

    public Verifier (boolean debug)
    {
        _debug = debug;
        _fft = new Compute(_debug);
    }

    public final boolean verify ()
    {
        boolean result = true;
        int[] input = Util.convert(INPUT_SIGNAL_1);
        int[] data = _fft.process(input, 1);
        
        for (int i = 0; (i < data.length) && result; i++)
        {
            if (data[i] != INPUT_PHASE_1[i])
                result = false;
        }

        if (result)
        {
            if (_debug)
                System.out.println("Verified INPUT_PHASE_1");

            data = _fft.process(input, 2);

            for (int i = 0; (i < data.length) && result; i++)
            {
                if (data[i] != INPUT_PHASE_2[i])
                  result = false;
            }

            if (result)
            {
                if (_debug)
                    System.out.println("Verified INPUT_PHASE_2");

                data = _fft.process(input, 3);

                for (int i = 0; (i < data.length) && result; i++)
                {
                    if (data[i] != INPUT_PHASE_3[i])
                        result = false;
                }

                if (result)
                {
                    if (_debug)
                        System.out.println("Verified INPUT_PHASE_3");

                    data = _fft.process(input, 4);

                    for (int i = 0; (i < data.length) && result; i++)
                    {
                        if (data[i] != INPUT_PHASE_4[i])
                            result = false;
                    }

                    if (result)
                    {
                        if (_debug)
                            System.out.println("Verified INPUT_PHASE_4");
                    }
                }
            }
        }

        return result;
    }
    
    private boolean _debug;
    Compute _fft;
}