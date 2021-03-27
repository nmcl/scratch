public class Verifier
{
    public static final String INPUT_SIGNAL_1 = "12345678";
    public static final int[] INPUT_PHASE_1 = {4, 8, 2, 2, 6, 1, 5, 8};
    public static final int[] INPUT_PHASE_2 = {3, 4, 0, 4, 0, 4, 3, 8};
    public static final int[] INPUT_PHASE_3 = {0, 3, 4, 1, 5, 5, 1, 8};
    public static final int[] INPUT_PHASE_4 = {0, 1, 0, 2, 9, 4, 9, 8};

    public static final String INPUT_SIGNAL_2 = "80871224585914546619083218645595";
    public static final int[] SIGNAL_2_OUTPUT = {2, 4, 1, 7, 6, 1, 7, 6};

    public static final String INPUT_SIGNAL_3 = "19617804207202209144916044189917";
    public static final int[] SIGNAL_3_OUTPUT = {7, 3, 7, 4, 5, 4, 1, 8};

    public static final String INPUT_SIGNAL_4 = "69317163492948606335995924319873";
    public static final int[] SIGNAL_4_OUTPUT = {5, 2, 4, 3, 2, 1, 3, 3};

    public Verifier (boolean debug)
    {
        _debug = debug;
        _fft = new Compute(_debug);
    }

    public boolean verify ()
    {
        if (verifyFirst())
        {
            if (verifySecond())
                return true;
            else
                return false;
        }
        else
            return false;
    }

    private final boolean verifyFirst ()
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

    private final boolean verifySecond ()
    {
        boolean result = true;
        int[] input = Util.convert(INPUT_SIGNAL_2);
        int[] data = _fft.process(input, 100);
        
        for (int i = 0; (i < SIGNAL_2_OUTPUT.length) && result; i++)
        {
            if (SIGNAL_2_OUTPUT[i] != data[i])
                result = false;
        }

        if (result)
        {
            if (_debug)
                System.out.println("Verified "+INPUT_SIGNAL_2);

            input = Util.convert(INPUT_SIGNAL_3);
            data = _fft.process(input, 100);

            for (int i = 0; (i < SIGNAL_3_OUTPUT.length) && result; i++)
            {
                if (SIGNAL_3_OUTPUT[i] != data[i])
                    result = false;
            }

            if (result)
            {
                if (_debug)
                    System.out.println("Verified "+INPUT_SIGNAL_3);

                input = Util.convert(INPUT_SIGNAL_4);
                data = _fft.process(input, 100);

                for (int i = 0; (i < SIGNAL_4_OUTPUT.length) && result; i++)
                {
                    if (SIGNAL_4_OUTPUT[i] != data[i])
                        result = false;
                }

                if (result)
                {
                    if (_debug)
                        System.out.println("Verified "+INPUT_SIGNAL_4);
                }
            }
        }

        return result;
    }
    
    private boolean _debug;
    Compute _fft;
}