public class Verifier
{
    public static final String INPUT_SIGNAL_1 = "12345678";
    public static final int[] INPUT_PHASE_1 = {4, 8, 2, 2, 6, 1, 5, 8};
    public static final int[] INPUT_PHASE_2 = {3, 4, 0, 4, 0, 4, 3, 8};
    public static final int[] INPUT_PHASE_3 = {0, 3, 4, 1, 5, 5, 1, 8};
    public static final int[] INPUT_PHASE_4 = {0, 1, 0, 2, 9, 4, 9, 8};

    public static String INPUT_SIGNAL_2 = "80871224585914546619083218645595";

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
        int[] input = convert(INPUT_SIGNAL_1);
        int[] data = _fft.process(input, 1);
        
        for (int i = 0; (i < data.length) && result; i++)
        {
            if (data[i] != INPUT_PHASE_1[i])
                result = false;
        }

        if (result)
        {
            //if (_debug)
                System.out.println("Verified INPUT_PHASE_1");

            data = _fft.process(data, 2);

            for (int i = 0; (i < data.length) && result; i++)
            {
                if (data[i] != INPUT_PHASE_2[i])
                  result = false;
            }

            if (result)
            {
                //if (_debug)
                    System.out.println("Verified INPUT_PHASE_2");

                data = _fft.process(data, 3);

                for (int i = 0; (i < data.length) && result; i++)
                {
                    if (data[i] != INPUT_PHASE_3[i])
                        result = false;
                }

                if (result)
                {
                    //if (_debug)
                        System.out.println("Verified INPUT_PHASE_3");

                    data = _fft.process(data, 4);

                    for (int i = 0; (i < data.length) && result; i++)
                    {
                        if (data[i] != INPUT_PHASE_4[i])
                            result = false;
                    }

                    if (result)
                    {
                        //if (_debug)
                            System.out.println("Verified INPUT_PHASE_4");
                    }
                }
            }
        }

        return result;
    }

    private final boolean verifySecond ()
    {
        boolean result = false;
        int[] input = convert(INPUT_SIGNAL_2);
        int[] data = _fft.process(input, 100);
        
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i]);

        System.out.println();

        return result;
    }

    private int[] convert (String input)
    {
        int[] result = new int[input.length()];
        char[] sequence = input.toCharArray();

        for (int i = 0; i < result.length; i++)
        {
            result[i] = Character.getNumericValue(sequence[i]);
        }

        return result;
    }
    
    private boolean _debug;
    Compute _fft;
}