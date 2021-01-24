import java.util.Objects;

/*
 * Represents a <x, y> coordinate for an Asteroid or an Empty space.
 */

public class Coordinate
{
    public Coordinate (int x, int y, int z)
    {
        _x = x;
        _y = y;
        _z = z;
    }

    public final int getX ()
    {
        return _x;
    }

    public final int getY ()
    {
        return _y;
    }

    public final int getZ ()
    {
        return _z;
    }

    @Override
    public String toString ()
    {
        return "<"+_x+", "+_y+", "+_z+">";
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_x, _y, _z);
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
            Coordinate temp = (Coordinate) obj;

            return ((_x == temp._x) && (_y == temp._y) && (_z == temp._z));
        }

        return false;
    }

    private int _x;
    private int _y;
    private int _z;
}