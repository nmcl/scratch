public class Handshake
{
    public static final long MAGIC_NUMBER = 20201227;
    public static final int SUBJECT_NUMBER = 7;
    public static final int START_NUMBER = 1;

    public Handshake (boolean debug)
    {
        _debug = debug;
    }

    private boolean _debug;
}