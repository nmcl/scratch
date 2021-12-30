public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int EARLIEST_DEPARTURE = 944;
    public static final int RESULT = 255;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Timetable tt = new Timetable(EXAMPLE_DATA, _debug);
        Bus bs = tt.busToCatch();

        if (bs != null)
        {
            if (bs.nextDeparture() == EARLIEST_DEPARTURE)
            {
                return true;
            }

            System.out.println("Incorrect departure time: "+tt.earliestDeparture());
        }
        else
            System.out.println("No bus found!");

        return false;
    }

    private boolean _debug;
}