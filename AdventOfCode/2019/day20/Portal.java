/*
 * A Portal is two letters but as we scan in the information about
 * the Donut and Portals we only get to read one letter at a time.
 * 
 * Assume there are always 3 spaces around the first letter in the Portal
 * name. Assume there are always 2 spaces around the second letter, then
 * the first letter and a passage which represents the real location of the
 * Portal.
 */

 /*
  * When printing, leep a reference to each latter of the Portal for each
  * coordinate. In fact the Portal print method should take this coordinate
  * and print our accordingly.
  */

public class Portal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public Portal (Coordinate position)
    {
        this(position, (char) Character.UNASSIGNED);
    }

    public Portal (Coordinate position, char portalId)
    {
        super(position, TileId.PORTAL);

        _portalId = portalId;
        _portalName = "*"+portalId+"*";  // a default name initially.
    }

    public final char getId ()
    {
        return _portalId;
    }

    public final String getName ()
    {
        return _portalName;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        if ((getClass() == obj.getClass()) || (super.getClass() == obj.getClass()))
        {
            Tile temp = (Tile) obj;

            return _position.equals(temp._position);  // only compare position not type.
        }

        return false;
    }
    
    private char _portalId;
    private String _portalName;
}
