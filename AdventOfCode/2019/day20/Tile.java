public class Tile
{
    public Tile (Coordinate coord)
    {
        this(coord, TileId.SPACE);  // a default.
    }

    public Tile (Coordinate coord, char type)
    {
        _position = coord;
        _type = type;
    }

    public final char content ()
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
        return Character.toString(_type);
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
            
            return _position.equals(temp._position);  // only compare position not type.
        }

        return false;
    }

    protected Coordinate _position;
    protected char _type;
}