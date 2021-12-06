public class Seat
{
    public Seat (int row, int column, int id)
    {
        _row = row;
        _column = _column;
        _id = id;
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

    private int _row;
    private int _column;
    private int _id;
}