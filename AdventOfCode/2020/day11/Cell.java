public class Cell
{
    public Cell (char type)
    {
        _type = type;
    }

    public char type ()
    {
        return _type;
    }
    
    public boolean isEmptySeat ()
    {
        return CellId.EMPTY_SEAT == _type;
    }

    @Override
    public String toString ()
    {
        return Character.toString(_type);
    }

    private char _type;
}