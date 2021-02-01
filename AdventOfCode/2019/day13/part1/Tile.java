import java.util.Objects;

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

    @Override
    public int hashCode ()
    {
        return Objects.hash(_id, _position.hashCode());
    }

    // just compare tile positions
    
    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Tile temp = (Tile) obj;

            return _position.equals(temp.getPosition());
        }

        return false;
    }

    private Coordinate _position;
    private int _id;
}