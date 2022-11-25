public class Submarine
{
    public Submarine (boolean debug)
    {
        _position = new ThreeDPoint(0, 0, 0);
        _debug = debug;
    }

    private ThreeDPoint _position;
    private boolean _debug;
}