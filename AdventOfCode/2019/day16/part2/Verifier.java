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
        int[] signal = Util.replicate(INPUT_SIGNAL_1, Util.REPEAT_SIZE);
        int[] data = _fft.process(signal, Util.PHASES);
        long offset = Util.offset(INPUT_SIGNAL_1);

        //if (_debug)
            System.out.println("Offset for "+INPUT_SIGNAL_1+" is "+offset);

        System.out.println();
        Util.printSignal(data, INPUT_SIGNAL_1.length());

        return result;
    }
    
    private boolean _debug;
    Compute _fft;
}