import java.util.*;

/*
 * Instances of this class are used to record the current
 * search pattern through the tunnels. We need to remember
 * the starting point, the keys we've found and the number
 * of steps to this stage.
 */

 // may not be needed in part 2.
 
public class State
{
    public State (Coordinate coord)
    {
        _coord = coord;
        _keys = Collections.emptySet();
        _steps = 0;
        _id = Util.keycode(_keys);
    }

    public State (State current, Coordinate pos, Character content)
    {
        _coord = pos;
        _keys = new HashSet<Character>(current._keys);

        if (content != null)
        {
            if (Util.isKey(content))
            {
                //System.out.println("is key");

                _keys.add(content);
            }
        }

        _steps = current._steps +1;
        _id = Util.keycode(_keys);
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

        return _keys.contains(key);
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
