public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final long EXAMPLE1_EARLIEST_TIMESTAMP = 1068781;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Timetable tt = new Timetable(EXAMPLE1_DATA, _debug);

        if (tt.earliestTimestamp() == EXAMPLE1_EARLIEST_TIMESTAMP)
            return true;
        
        System.out.println("Wrong earliest timestamp: "+tt.earliestTimestamp());

        return false;
    }

    private boolean _debug;
}