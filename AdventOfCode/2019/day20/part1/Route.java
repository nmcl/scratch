import java.util.Objects;

public class Route
{
    public Route (Coordinate start, Coordinate end)
    {
        this(start, end, 1);
    }

    public Route (Coordinate start, Coordinate end, int steps)
    {
        _start = start;
        _end = end;
        _numberOfSteps = steps;
    }

    public final Coordinate getStart ()
    {
        return _start;
    }

    public final Coordinate getEnd ()
    {
        return _end;
    }

    public final int numberOfSteps ()
    {
        return _numberOfSteps;
    }
    
    @Override
    public String toString ()
    {
        return "< "+_start+", "+_end+", "+_numberOfSteps+" >";
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_start, _end);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Route temp = (Route) obj;

            if (_start.equals(temp._start) && (_end.equals(temp._end)))
                return true;
        }

        return false;
    }

    private Coordinate _start;
    private Coordinate _end;
    private int _numberOfSteps;
}