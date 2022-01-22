import java.util.Objects;

/*
 * Represents a <x, y, z, w> point.
 */

public class FourDPoint
{
    public FourDPoint (int x, int y, int z, int w)
    {
        _x = x;
        _y = y;
        _z = z;
    }

    protected FourDPoint (FourDPoint toCopy)
    {
        _x = toCopy._x;
        _y = toCopy._y;
        _z = toCopy._z;
        _w = toCopy._w;
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

    public final int getW ()
    {
        return _w;
    }

    @Override
    public String toString ()
    {
        return "FourDPoint < "+_x+", "+_y+", "+_z+", "+_w+" >";
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(_x, _y, _z, _w);
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
            FourDPoint temp = (FourDPoint) obj;

            return ((_x == temp._x) && (_y == temp._y) && (_z == temp._z) && (_w == temp._w));
        }

        return false;
    }

    protected int _x;
    protected int _y;
    protected int _z;
    protected int _w;
}