import javax.lang.model.element.Element;

/*
 * Represents a <x, y> coordinate for a piece of a wire.
 */

public class Coordinate
{
    public Coordinate (int x, int y)
    {
        _x = x;
        _y = y;
    }

    public final int getX ()
    {
        return _x;
    }

    public final int getY ()
    {
        return _y;
    }

    @Override
    public String toString ()
    {
        return "<"+_x+", "+_y+">";
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
        
        if (obj instanceof Coordinate)
        {
            Coordinate temp = (Coordinate) obj;

            return ((_x == temp._x) && (_y == temp._y));
        }

        return false;
    }

    private int _x;
    private int _y;
}