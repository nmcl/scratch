public class Cube
{
    public Cube (ThreeDPoint coord)
    {
        this(coord, false);
    }

    public Cube (ThreeDPoint coord, boolean active)
    {
        _coord = coord;
        _active = active;
    }

    public boolean isActive ()
    {
        return _active;
    }

    public void activate ()
    {
        _active = true;
    }

    public void deactivate ()
    {
        _active = false;
    }

    @Override
    public String toString ()
    {
        return "Cube: "+((_active ? "active": "inactive"))+" at "+_coord;
    }

    @Override
    public boolean equals (Object obj)  // don't compare activation status
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Cube temp = (Cube) obj;

            return ((_coord.getX() == temp._coord.getX()) && (_coord.getY() == temp._coord.getY()) && (_coord.getZ() == temp._coord.getZ()));
        }

        return false;
    }

    private ThreeDPoint _coord;
    private boolean _active;
}