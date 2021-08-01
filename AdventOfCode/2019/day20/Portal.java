/*
 * A Portal is two letters but as we scan in the information about
 * the Donut and Portals we only get to read one letter at a time.
 * 
 * Assume there are always 3 spaces around the first letter in the Portal
 * name. Assume there are always 2 spaces around the second letter, then
 * the first letter and a passage which represents the real location of the
 * Portal.
 */

public class Portal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

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
    
    private char _portalId;
    private String _portalName;
}
