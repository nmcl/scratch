public class Seat
{
    public Seat (int row, int column, int id)
    {
        _row = row;
        _column = column;
        _id = id;
    }

    // multiply the row by 8, then add the column

    public Seat (int row, int column)
    {
        this(row, column, (row *8) + column);
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