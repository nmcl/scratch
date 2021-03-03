public class Tile
{
    public Tile (Coordinate coord, String type)
    {
        _position = coord;
        _type = type;
    }

    public final String content ()
    {
        return _type;
    }

    public final Coordinate position ()
    {
        return _position;
    }

    @Override
    public String toString ()
    {
        return _type;
    }

    private Coordinate _position;
    private String _type;
}