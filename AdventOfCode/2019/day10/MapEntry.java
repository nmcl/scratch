public abstract class MapEntry
{
    public final Coordinate getPosition ()
    {
        return _position;
    }

    public abstract boolean empty ();
    
    protected MapEntry (int x, int y)
    {
        _position = new Coordinate(x, y);
    }

    private Coordinate _position;
}