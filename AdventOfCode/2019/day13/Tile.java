public class Tile
{
    public Tile ()
    {
        this(TileId.EMPTY);
    }

    public Tile (int id)
    {
        _id = id;
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

    private int _id;
}