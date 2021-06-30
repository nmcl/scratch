import java.util.Set;

/*
 * Instances of this class are used to record the current
 * search pattern through the tunnels. We need to remember
 * the starting point, the keys we've found and the number
 * of steps to this stage.
 */

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
