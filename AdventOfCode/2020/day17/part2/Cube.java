public class Cube
{
    public Cube (FourDPoint coord)
    {
        _coord = coord;
    }

    public final FourDPoint position ()
    {
        return _coord;
    }

    @Override
    public String toString ()
    {
        return "Cube: "+_coord;
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

            return ((_coord.getX() == temp._coord.getX()) && (_coord.getY() == temp._coord.getY())
                    && (_coord.getZ() == temp._coord.getZ()) && (_coord.getW() == temp._coord.getW()));
        }

        return false;
    }

    private FourDPoint _coord;
}