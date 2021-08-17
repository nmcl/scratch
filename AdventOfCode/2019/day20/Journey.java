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

    private Coordinate _coord;
    private int _steps;
}