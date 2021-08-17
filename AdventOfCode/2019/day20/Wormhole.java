import java.util.Objects;

/*
 * A wormhole represents the connection between two Portals.
 */

public class Wormhole
{
    public Wormhole (Tile first, Tile second, Coordinate location)
    {
        _name = "" + ((Portal) first).getId() + ((Portal) second).getId();
        _position = location;

        System.out.println("created "+this);
    }

    public final String getName ()
    {
        return _name;
    }

    public final Coordinate getLocation ()
    {
        return _position;
    }

    @Override
    public String toString ()
    {
        return "Wormhole < "+_name+", "+_position+" >";
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_name, _position);
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
            Wormhole temp = (Wormhole) obj;

            return _name.equals(temp._name);  // compare on names for wormholes, not locations!
        }

        return false;
    }


    private String _name;
    private Coordinate _position;
}