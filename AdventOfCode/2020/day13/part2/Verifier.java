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
        Bus bs = tt.busToCatch();

        if (bs != null)
        {
            if (bs.nextDeparture() == EARLIEST_DEPARTURE)
            {
                int diff = bs.nextDeparture() - tt.earliestDeparture();

                if (diff * bs.getID() == RESULT)
                    return true;
                else
                    System.out.println("Wrong difference "+diff+" or bus "+bs);
            }
            else
                System.out.println("Incorrect departure time: "+bs.nextDeparture());
        }
        else
            System.out.println("No bus found!");

        return false;
    }

    private boolean _debug;
}