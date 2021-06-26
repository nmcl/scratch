public class State
{
    public State (Coordinate coord, Set<Character> keys, int steps)
    {
        _coord = coord;
        _keys = keys;
        _steps = steps;
    }

    private Coordinate _coord;
    private Set<Character> _keys;
    private int _steps;
}
