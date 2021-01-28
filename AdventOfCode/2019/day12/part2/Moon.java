public class Moon
{
    public Moon (int x, int y, int z)
    {
        _position = new Coordinate(x, y, z);
        _velocity = new Velocity();  // the x, y, and z velocity of each moon starts at 0.
    }

    public Moon (Moon toCopy)
    {
        _position = new Coordinate(toCopy._position);
        _velocity = new Velocity(toCopy._velocity);
    }
    
    public final Coordinate getPosition ()
    {
        return _position;
    }

    public final Velocity getVelocity ()
    {
        return _velocity;
    }

    public final int getPotentialEnergy ()
    {
        int x = _position.getX();
        int y = _position.getY();
        int z = _position.getZ();

        return Math.abs(x)+Math.abs(y)+Math.abs(z);
    }

    public final int getKineticEnergy ()
    {
        int xVel = _velocity.getX();
        int yVel = _velocity.getY();
        int zVel = _velocity.getZ();

        return Math.abs(xVel)+Math.abs(yVel)+Math.abs(zVel);
    }

    public final int getTotalEnergy ()
    {
        return getPotentialEnergy() * getKineticEnergy();
    }

    /*
     * On each axis (x, y, and z), the velocity of each moon changes by
     * exactly +1 or -1 to pull the moons together.
     */

    public final void updateVelocity (Moon otherMoon)
    {
        if (!this.equals(otherMoon))
        {
            int x1 = _position.getX();
            int x2 = otherMoon._position.getX();
            int y1 = _position.getY();
            int y2 = otherMoon._position.getY();
            int z1 = _position.getZ();
            int z2 = otherMoon._position.getZ();
            int xVel = _velocity.getX();
		    int yVel = _velocity.getY();
            int zVel = _velocity.getZ();

            xVel = x1 > x2 ? xVel - 1 : (x1 == x2 ? xVel : xVel + 1);
		    yVel = y1 > y2 ? yVel - 1 : (y1 == y2 ? yVel : yVel + 1);
            zVel = z1 > z2 ? zVel - 1 : (z1 == z2 ? zVel : zVel + 1);

            _velocity = new Velocity(xVel, yVel, zVel);
        }
    }

    public final void updatePosition ()
    {
        int x = _position.getX();
        int y = _position.getY();
        int z = _position.getZ();

        _position = new Coordinate(x + _velocity.getX(), y + _velocity.getY(), z + _velocity.getZ());
    }

    @Override
    public String toString ()
    {
        return "Moon: "+_position+" and "+_velocity;
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
            Moon temp = (Moon) obj;

            return (_position.equals(temp._position) && _velocity.equals(temp._velocity));
        }

        return false;
    }

    private Coordinate _position;
    private Velocity _velocity;
}