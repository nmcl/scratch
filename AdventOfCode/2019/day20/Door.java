public class Door
{
    public Door (Coordinate position, boolean horizontalRepresentation)
    {
        _location = position;
        _horizontal = horizontalRepresentation;
    }

    private Coordinate _location;
    private boolean _horizontal;
}