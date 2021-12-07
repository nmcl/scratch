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
    public boolean equals (Object obj)
    {
        if (obj == null)
        return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Seat temp = (Seat) obj;

            if ((temp._row == _row) && (temp._column == _column) && (temp._id == _id))
                return true;
        }

        return false;
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