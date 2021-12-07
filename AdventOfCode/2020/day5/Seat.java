public class Seat
{
    public Seat (int row, int column)
    {
        _row = row;
        _column = column;

        // multiply the row by 8, then add the column

        _id = (_row *8) + _column;
    }

    private final int getRow ()
    {
        return _row;
    }

    private final int getColumn ()
    {
        return _column;
    }

    private final int id ()
    {
        return _id;
    }

    @Override
    public String toString ()
    {
        return "Seat row: "+_row+", column: "+_column+" and id: "+_id;
    }

    private int _row;
    private int _column;
    private int _id;
}