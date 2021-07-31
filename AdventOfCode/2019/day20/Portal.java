public class Portal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public Portal (Coordinate position, char portalId)
    {
        super(position, TileId.PORTAL);

        _portalId = portalId;
    }

    public final char getId ()
    {
        return _portalId;
    }
    
    private char _portalId;
}
