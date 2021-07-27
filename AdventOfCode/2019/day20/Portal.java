public class Portal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public Portal (Coordinate position, char portalId)
    {
        super(position, TileId.PORTAL);
        
        _location = position;
        _portalName = portalId;
    }

    private Coordinate _location;
    private char _portalName;
}