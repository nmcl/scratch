public java.util.*;

public class Route
{
    public Route (Coordinate start, Coordinate end)
    {
        this(start, end, 0);
    }

    public Route (Coordinate start, Coordinate end, int stepsTaken)
    {
        _start = start;
        _end = end;
        _stepsTaken = stepsTaken;
        _doors = new Vector<Character>();
    }

    @Override
    public String toString ()
    {
        return "Route < "+_start+", "+_end+", "+_stepsTaken+", "+_doors+" >";
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

            return ((_start.equals(temp._start)) && (_end.equals(temp._end));
        }

        return false;
    }

    private Coordinate _start;
    private Coordinate _end;
    private int _stepsTaken;
    private Vector<Character> _doors;
}