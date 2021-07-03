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

    @Override
    public int hashCode ()
    {
        return _coord.hashCode();
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
            State temp = (State) obj;

            if (temp._coord.equals(_coord))
            {
                return temp._keys.containsAll(_keys);
            }
        }

        return false;
    }

    @Override
    public String toString ()
    {
        return "State: "+_coord+", "+_id+", "+_steps;
    }

    private Coordinate _coord;
    private Set<Character> _keys;
    private int _steps;
    private String _id;
}
