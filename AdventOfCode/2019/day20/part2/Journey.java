import java.util.Objects;

public class Journey
{
    public Journey (Coordinate coord)
    {
        this(coord, 0, 0);
    }

    public Journey (Coordinate coord, int steps, int mazeLevel)
    {
        _coord = coord;
        _steps = steps;
        _mazeLevel = mazeLevel;
        _name = _coord + "" + _mazeLevel;
    }

    public final Coordinate getLocation ()
    {
        return _coord;
    }
    
    public final int getSteps ()
    {
        return _steps;
    }

    public final int levelOfMaze ()
    {
        return _mazeLevel;
    }

    public String name ()
    {
        return _name;
    }

    @Override
    public String toString ()
    {
        return "< "+_coord+", "+_steps+", "+_mazeLevel+", "+_name+" >";
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
    private int _mazeLevel;
    private String _name;
}