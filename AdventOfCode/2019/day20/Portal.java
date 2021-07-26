public class Portal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public Portal (Coordinate position, boolean horizontalRepresentation)
    {
        super(position, TileId.PORTAL);
        
        _location = position;
        _horizontal = horizontalRepresentation;
    }

    private Coordinate _location;
    private boolean _horizontal;
}