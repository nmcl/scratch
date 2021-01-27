/*
 * Represents a <x, y, z> coordinate for Moon.
 */

public class Coordinate extends ThreeDPoint
{
    public Coordinate (int x, int y, int z)
    {
        super(x, y, z);
    }

    public Coordinate (Coordinate toCopy)
    {
        super(toCopy);
    }

    @Override
    public String toString ()
    {
        return "Coordinate: <x="+_x+", y="+_y+", z="+_z+">";
    }
}