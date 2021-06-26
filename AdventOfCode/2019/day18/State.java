public class State
{
    public State (Coordinate coord, Set<Character> keys, int steps)
    {
        _coord = coord;
        _keys = keys;
        _steps = steps;
    }

    public Coordinate getPosition ()
    {
        return _coord;
    }
    
    public int numberOfKeys ()
    {
        return _keys.size();
    }

    public int numberOfSteps ()
    {
        return _steps;
    }

    private Coordinate _coord;
    private Set<Character> _keys;
    private int _steps;
}
