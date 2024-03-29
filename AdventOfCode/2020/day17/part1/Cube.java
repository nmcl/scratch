public class Cube
{
    public Cube (ThreeDPoint coord)
    {
        _coord = coord;
    }

    public final ThreeDPoint position ()
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

            return ((_coord.getX() == temp._coord.getX()) && (_coord.getY() == temp._coord.getY()) && (_coord.getZ() == temp._coord.getZ()));
        }

        return false;
    }

    private ThreeDPoint _coord;
}