public class Plane
{
    public final int ROWS = 128;
    public final int COLUMNS = 8;

    public Plane (boolean debug)
    {
        _seats = new Seat[ROWS][COLUMNS];
        _debug = debug;
    }

    private Seat[][] _seats;
    private boolean _debug;
}