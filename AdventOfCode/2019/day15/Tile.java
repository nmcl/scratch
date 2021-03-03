public class Tile
{
    public Tile (Coordinate coord)
    {
        this(coord, TileId.UNEXPLORED);
    }

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

    @Override
    public int hashCode ()
    {
        return super.hashCode();
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        // only check location not content

        if (getClass() == obj.getClass())
        {
            Tile temp = (Tile) obj;

            return _position.equals(temp._position);
        }

        return false;
    }

    private Coordinate _position;
    private String _type;
}