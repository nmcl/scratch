import java.util.Objects;

public class Journey
{
    public Journey (Coordinate coord)
    {
        this(coord, 0);
    }

    public Journey (Coordinate coord, int steps)
    {
        _coord = coord;
        _steps = steps;
    }

    public final Coordinate getLocation ()
    {
        return _coord;
    }
    
    public final int getSteps ()
    {
        return _steps;
    }

    @Override
    public String toString ()
    {
        return "< "+_coord+", "+_steps+" >";
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_coord, _steps);
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
            Journey temp = (Journey) obj;

            if (temp._coord.equals(_coord))
                return true;
        }

        return false;
    }

    private Coordinate _coord;
    private int _steps;
}