public class Tile
{
    public Tile (Coordinate position)
    {
        this(position, TileId.EMPTY);
    }

    public Tile (Coordinate position, int id)
    {
        _position = position;
        _id = id;
    }

    public final Coordinate getPosition ()
    {
        return _position;
    }
    
    public final int getId ()
    {
        return _id;
    }

    public final void setId (int id)
    {
        _id = id;
    }

    @Override
    public String toString ()
    {
        return TileId.idToString(_id);
    }

    private Coordinate _position;
    private int _id;
}