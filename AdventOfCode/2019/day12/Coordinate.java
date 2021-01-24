import java.util.Objects;

/*
 * Represents a <x, y, z> coordinate for Moon.
 */

public class Coordinate extends ThreeDPoint
{
    public Coordinate (int x, int y, int z)
    {
        super(x, y, z);
    }

    @Override
    public String toString ()
    {
        return "Coordinate: <"+_x+", "+_y+", "+_z+">";
    }
}