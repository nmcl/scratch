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
            
            System.out.println("**comparing "+x1+" "+y1+" "+z1);
			System.out.println("**and "+x2+" "+y2+" "+z2);
            System.out.println("**against "+xVel+" "+yVel+" "+zVel);

            xVel = x1 > x2 ? xVel - 1 : (x1 == x2 ? xVel : xVel + 1);
		    yVel = y1 > y2 ? yVel - 1 : (y1 == y2 ? yVel : yVel + 1);
            zVel = z1 > z2 ? zVel - 1 : (z1 == z2 ? zVel : zVel + 1);

            _velocity = new Velocity(xVel, yVel, zVel);
            
            System.out.println("**THIS overwriting Moon with "+xVel+" "+yVel+" "+zVel);
        }

        System.out.println("**overwriting Moon with "+_velocity.getX()+" "+_velocity.getY()+" "+_velocity.getZ());
    }

    public final void updatePosition ()
    {
        int x = _position.getX();
        int y = _position.getY();
        int z = _position.getZ();

        _position = new Coordinate(x + _velocity.getX(), y + _velocity.getY(), z + _velocity.getZ());

        System.out.println("**Coord now "+_position);
    }

    @Override
    public String toString ()
    {
        return "Moon: "+_position+" and "+_velocity;
    }

    private Coordinate _position;
    private Velocity _velocity;
}