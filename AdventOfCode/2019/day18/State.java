import java.util.Set;

public class State
{
    public State (Coordinate coord, Set<Character> keys, int steps)
    {
        _coord = coord;
        _keys = keys;
        _steps = steps;
        _id = Util.keycode(keys);
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

    public boolean hasKey (char door)
    {
        char key = Character.toLowerCase(door);

        return _keys.contains(door);
    }

    public Set<Character> getKeys ()
    {
        return _keys;
    }

    public String getIdentifier ()
    {
        return _id;
    }

    private Coordinate _coord;
    private Set<Character> _keys;
    private int _steps;
    private String _id;
}
