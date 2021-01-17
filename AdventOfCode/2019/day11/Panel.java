public class Panel
{
    public static final int BLACK = 0;
    public static final int WHITE  =1;

    public Panel (int x, int y)
    {
        _position = new Coordinate(x, y);
    }

    public final int timesPainted ()
    {
        return _numberOfTimesPainted;
    }

    public final Coordinate getPosition ()
    {
        return _position;
    }

    public final void paint (int colour)
    {
        _colour = colour;
        _numberOfTimesPainted++;
    }

    @Override
    public String toString ()
    {
        return "Panel at "+_position+" with colour "+((_colour == BLACK) ? "black" : "white")+" and painted "+_numberOfTimesPainted+" times.";
    }

    private Coordinate _position;
    private int _colour = BLACK;
    private int _numberOfTimesPainted = 0;
}