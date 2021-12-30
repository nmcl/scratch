public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final long EXAMPLE1_EARLIEST_TIMESTAMP = 1068781;
    public static final String EXAMPLE2_DATA = "example2.txt";
    public static final long EXAMPLE2_EARLIEST_TIMESTAMP = 3417;
    public static final String EXAMPLE3_DATA = "example3.txt";
    public static final long EXAMPLE3_EARLIEST_TIMESTAMP = 754018;
    public static final String EXAMPLE4_DATA = "example4.txt";
    public static final long EXAMPLE4_EARLIEST_TIMESTAMP = 779210;
    public static final String EXAMPLE5_DATA = "example5.txt";
    public static final long EXAMPLE5_EARLIEST_TIMESTAMP = 1261476;
    public static final String EXAMPLE6_DATA = "example6.txt";
    public static final long EXAMPLE6_EARLIEST_TIMESTAMP = 1202161486;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Timetable tt = new Timetable(EXAMPLE1_DATA, _debug);

        if (tt.earliestTimestamp() == EXAMPLE1_EARLIEST_TIMESTAMP)
        {
            tt = new Timetable(EXAMPLE2_DATA, _debug);

            if (tt.earliestTimestamp() == EXAMPLE2_EARLIEST_TIMESTAMP)
            {
                tt = new Timetable(EXAMPLE3_DATA, _debug);

                if (tt.earliestTimestamp() == EXAMPLE3_EARLIEST_TIMESTAMP)
                {
                    tt = new Timetable(EXAMPLE4_DATA, _debug);

                    if (tt.earliestTimestamp() == EXAMPLE4_EARLIEST_TIMESTAMP)
                    {
                        tt = new Timetable(EXAMPLE5_DATA, _debug);

                        if (tt.earliestTimestamp() == EXAMPLE5_EARLIEST_TIMESTAMP)
                        {
                            tt = new Timetable(EXAMPLE6_DATA, _debug);

                            if (tt.earliestTimestamp() == EXAMPLE6_EARLIEST_TIMESTAMP)
                                return true;
                            else
                                System.out.println("Wrong 6th earliest timestamp: "+tt.earliestTimestamp());
                        }
                        else
                            System.out.println("Wrong 5th earliest timestamp: "+tt.earliestTimestamp());
                    }
                    else
                        System.out.println("Wrong 4th earliest timestamp: "+tt.earliestTimestamp());
                }
                else
                    System.out.println("Wrong 3rd earliest timestamp: "+tt.earliestTimestamp());
            }
            else
                System.out.println("Wrong 2nd earliest timestamp: "+tt.earliestTimestamp());
        }
        else
            System.out.println("Wrong 1st earliest timestamp: "+tt.earliestTimestamp());

        return false;
    }

    private boolean _debug;
}