public class Door
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";
    
    public Door (Coordinate position, boolean horizontalRepresentation)
    {
        _location = position;
        _horizontal = horizontalRepresentation;
    }

    private Coordinate _location;
    private boolean _horizontal;
}