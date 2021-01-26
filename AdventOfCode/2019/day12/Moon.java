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

            System.out.println("**comparing "+x1+" and "+x2);

            if (x1 > x2)
            {
                _velocity.decrementX();
                otherMoon._velocity.incrementX();
            }
            else
            {
                if (x1 < x2)
                {
                    _velocity.incrementX();
                    otherMoon._velocity.decrementX();
                }
            }

            int y1 = _position.getY();
            int y2 = otherMoon._position.getY();

            System.out.println("**comparing "+y1+" and "+y2);

            if (y1 > y2)
            {
                _velocity.decrementY();
                otherMoon._velocity.incrementY();
            }
            else
            {
                if (y1 < y2)
                {
                    _velocity.incrementY();
                    otherMoon._velocity.decrementY();
                }
            }

            int z1 = _position.getZ();
            int z2 = otherMoon._position.getZ();

            System.out.println("**comparing "+z1+" and "+z2);

            if (z1 > z2)
            {
                _velocity.decrementZ();
                otherMoon._velocity.incrementZ();
            }
            else
            {
                if (z1 < z2)
                {
                    _velocity.incrementZ();
                    otherMoon._velocity.decrementZ();
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