public class Verifier
{
    public static final String INPUT_SIGNAL_1 = "12345678";

    public Verifier (boolean debug)
    {
        _debug = debug;
        _fft = new Compute(_debug);
    }

    public boolean verify ()
    {
        boolean result = false;
        int[] input = convert(INPUT_SIGNAL_1);
        int[] data = _fft.process(input, 1);

        for (int i = 0; i < data.length; i++)
            System.out.println(data[i]);

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