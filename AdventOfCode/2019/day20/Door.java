public class Door extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public Door (Coordinate position, boolean horizontalRepresentation)
    {
        super(position, TileId.DOOR);
        
        _location = position;
        _horizontal = horizontalRepresentation;
    }

    private Coordinate _location;
    private boolean _horizontal;
}