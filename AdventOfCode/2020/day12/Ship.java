public class Ship
{
    public Ship (boolean debug)
    {
        _facing = Direction.EAST;
        _position = new Coordinate(0, 0);
        _debug = debug;
    }

    public final char getFacing ()
    {
        return _facing;
    }

    public final Coordinate getPosition ()
    {
        return _position;
    }

    private char _facing;
    private Coordinate _position;
    private boolean _debug;
}