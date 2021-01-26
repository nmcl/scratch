public class Moon
{
    public Moon (int x, int y, int z)
    {
        _position = new Coordinate(x, y, z);
        _velocity = new Velocity();  // the x, y, and z velocity of each moon starts at 0.
    }
    
    public final Coordinate getPosition ()
    {
        return _position;
    }

    public final Velocity getVelocity ()
    {
        return _velocity;
    }

    /*
     * On each axis (x, y, and z), the velocity of each moon changes by
     * exactly +1 or -1 to pull the moons together.
     */

    public final void applyGravity (Moon otherMoon)
    {
        if (!this.equals(otherMoon))
        {
            int x1 = _position.getX();
            int x2 = otherMoon._position.getX();

            if (x1 > x2)
            {
                _velocity.setX(x1 -1);
                otherMoon._velocity.setX(x2 +1);
            }
            else
            {
                if (x1 < x2)
                {
                    _velocity.setX(x1 +1);
                    otherMoon._velocity.setX(x2 -1);
                }
            }

            int y1 = _position.getY();
            int y2 = otherMoon._position.getY();

            if (y1 > y2)
            {
                _velocity.setY(y1 -1);
                otherMoon._velocity.setY(y2 +1);
            }
            else
            {
                if (y1 < y2)
                {
                    _velocity.setY(y1 +1);
                    otherMoon._velocity.setY(y2 -1);
                }
            }

            int z1 = _position.getZ();
            int z2 = otherMoon._position.getZ();

            if (z1 > z2)
            {
                _velocity.setZ(z1 -1);
                otherMoon._velocity.setZ(z2 +1);
            }
            else
            {
                if (z1 < z2)
                {
                    _velocity.setZ(z1 +1);
                    otherMoon._velocity.setZ(z2 -1);
                }
            }
        }
    }

    @Override
    public String toString ()
    {
        return "Moon: "+_position+" and "+_velocity;
    }

    private Coordinate _position;
    private Velocity _velocity;
}