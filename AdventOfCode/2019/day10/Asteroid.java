public class Asteroid
{
    public Asteroid (int x, int y)
    {
        _position = new Coordinate(x, y);
    }

    public final Coordinate getPosition ()
    {
        return _position;
    }

    private Coordinate _position;
}