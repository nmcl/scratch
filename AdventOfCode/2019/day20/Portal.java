/*
 * A Portal is two letters but as we scan in the information about
 * the Donut and Portals we only get to read one letter at a time.
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
