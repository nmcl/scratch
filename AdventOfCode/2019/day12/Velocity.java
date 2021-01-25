/*
 * Represents a <x, y, z> velocity for a Moon.
 */

public class Velocity extends ThreeDPoint
{
    public Velocity ()
    {
        this(0, 0, 0);
    }

    public Velocity (int x, int y, int z)
    {
        super(x, y, z);
    }

    @Override
    public String toString ()
    {
        return "Velocity: <x="+_x+", y="+_y+", z="+_z+">";
    }
}