public abstract class MapEntry
{
    public final Coordinate getPosition ()
    {
        return _position;
    }

    public int distanceBetween (MapEntry other)
    {
        return Math.abs(_position.getX() - other.getPosition().getX()) + Math.abs(_position.getY() - other.getPosition().getY());
    }

    public abstract boolean isEmpty ();

    protected MapEntry (int x, int y)
    {
        _position = new Coordinate(x, y);
    }

    protected Coordinate _position;
}