public class Portal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public Portal (Coordinate position, boolean horizontalRepresentation)
    {
        super(position, TileId.PORTAL);
        
        _horizontal = horizontalRepresentation;
    }

    private boolean _horizontal;
}