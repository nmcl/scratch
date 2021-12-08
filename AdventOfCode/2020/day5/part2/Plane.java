import java.util.*;

public class Plane
{
    public final int ROWS = 128;
    public final int COLUMNS = 8;

    public Plane (boolean debug)
    {
        _seats = new Seat[ROWS][COLUMNS];
        _debug = debug;

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++)
                _seats[i][j] = null;
        }
    }

    public final void addSeat (Seat s)
    {
        _seats[s.getRow()][s.getColumn()] = s;
    }

    public final Seat getSeat (int row, int column)
    {
        return _seats[row][column];
    }

    public final Vector<Seat> emptySeats ()
    {
        Vector<Seat> blanks = new Vector<Seat>();

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++)
            {
                if (_seats[i][j] == null)
                    blanks.add(new Seat(i, j));
            }
        }
        
        return blanks;
    }

    private Seat[][] _seats;
    private boolean _debug;
}