public class Verifier
{
    public static final String INPUT_SIGNAL_1 = "12345678";
    public static final int[] INPUT_PHASE_1 = {4, 8, 2, 2, 6, 1, 5, 8};
    public static final int[] INPUT_PHASE_2 = {3, 4, 0, 4, 0, 4, 3, 8};
    public static final int[] INPUT_PHASE_3 = {0, 3, 4, 1, 5, 5, 1, 8};

    public Verifier (boolean debug)
    {
        _debug = debug;
        _fft = new Compute(_debug);
    }

    public boolean verify ()
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
            System.out.println("Verified INPUT_PHASE_1");

            data = _fft.process(data, 2);

            for (int i = 0; (i < data.length) && result; i++)
            {
                if (data[i] != INPUT_PHASE_2[i])
                  result = false;
            }

            if (result)
            {
                System.out.println("Verified INPUT_PHASE_2");

                data = _fft.process(data, 3);

                for (int i = 0; (i < data.length) && result; i++)
                {
                    if (data[i] != INPUT_PHASE_3[i])
                        result = false;
                }
            }
        }

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