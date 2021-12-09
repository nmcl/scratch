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

    /*
     * It will have a unique row number as it's the last one in the plane (of valid seats).
     * 
     * assumes only one!
     */

    public final Seat emptySeat ()
    {
        Seat theSeat = null;
        int[] rows = new int[ROWS];

        for (int i = 0; i < ROWS; i++)
        {
            rows[i] = 0;
        }

        return null;
    }

    private Seat[][] _seats;
    private boolean _debug;
}