import java.util.*;

/*
 * Track the route from A to B, how many steps to get there
 * and any doors we must pass through on the way.
 */

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
        _theDoors = new Vector<Character>();
    }

    public Coordinate getStart ()
    {
        return _start;
    }

    public Coordinate getEnd ()
    {
        return _end;
    }

    public int getStepsTaken ()
    {
        return _stepsTaken;
    }

    public Vector<Character> getDoors ()
    {
        return _theDoors;
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

            return ((_start.equals(temp._start)) && (_end.equals(temp._end)));
        }

        return false;
    }

    private Coordinate _start;
    private Coordinate _end;
    private int _stepsTaken;
    private Vector<Character> _theDoors;  // because we can :)
}