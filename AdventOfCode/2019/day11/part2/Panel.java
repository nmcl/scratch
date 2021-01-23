import java.util.Objects;

public class Panel
{
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public Panel (int x, int y)
    {
        _position = new Coordinate(x, y);
    }

    public Panel (Coordinate coord)
    {
        _position = coord;
    }

    public final int colour ()
    {
        return _colour;
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

    public final String fullOutput ()
    {
        return "Panel at "+_position+" with colour "+((_colour == BLACK) ? "black" : "white")+" and painted "+_numberOfTimesPainted+" times.";
    }

    @Override
    public String toString ()
    {
        return "Panel at "+_position;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_colour, _numberOfTimesPainted, _position.hashCode());
    }

    // do not compare colour or number of times painted

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        boolean result = false;
        
        if (getClass() == obj.getClass())
        {
            Panel p = (Panel) obj;

            result = _position.equals(p.getPosition());
        }
        
        return result;
    }

    private Coordinate _position;
    private int _colour = BLACK;
    private int _numberOfTimesPainted = 0;
}