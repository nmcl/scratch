public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final int EARLIEST_DEPARTURE = 944;
    public static final int RESULT = 295;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Timetable tt = new Timetable(EXAMPLE1_DATA, _debug);

        tt.earliestTimestamp();
        
        return false;
    }

    private boolean _debug;
}