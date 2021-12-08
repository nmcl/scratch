public class Plane
{
    public final int ROWS = 128;
    public final int COLUMNS = 8;

    public Plane (boolean debug)
    {
        _seats = new Seat[ROWS][COLUMNS];
        _debug = debug;
    }

    public final void addSeat (Seat s)
    {
        _seats[s.getRow()][s.getColumn()] = s;
    }

    public final Seat getSeat (int row, int column)
    {
        return _seats[row][column];
    }

    private Seat[][] _seats;
    private boolean _debug;
}