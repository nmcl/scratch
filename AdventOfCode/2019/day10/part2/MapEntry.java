public abstract class MapEntry
{
    public final Coordinate getPosition ()
    {
        return _position;
    }

    public abstract boolean isEmpty ();

    protected MapEntry (int x, int y)
    {
        _position = new Coordinate(x, y);
    }

    protected Coordinate _position;
}