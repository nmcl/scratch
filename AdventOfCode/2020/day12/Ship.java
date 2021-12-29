public class Ship
{
    public Ship (boolean debug)
    {
        _facing = Direction.EAST;
        _debug = debug;
    }

    private char _facing;
    private boolean _debug;
}