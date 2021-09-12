public class Tile
{
    public Tile (char type)
    {
        _type = type;
    }

    public char type ()
    {
        return _type;
    }
    
    public boolean isBug ()
    {
        return TileId.BUG == _type;
    }

    @Override
    public String toString ()
    {
        return Character.toString(_type);
    }

    private char _type;
}