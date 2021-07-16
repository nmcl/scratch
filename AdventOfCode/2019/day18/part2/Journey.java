import java.util.*;

/*
 * Instances of this class are used to record the current
 * search pattern through the tunnels. We need to remember
 * the starting point, the keys we've found and the number
 * of steps to this stage.
 */

 // Was essentially State in part 1.

public class Journey
{
    public Journey (List<Coordinate> entrances)
    {
        this(entrances, Collections.emptySet(), 0);
    }

    public Journey (List<Coordinate> entrances, Set<Character> keys, int steps)
    {
        _locations = entrances;
        _keys = keys;
        _steps = steps;
        _id = Util.keycode(_keys);
    }

    public final Coordinate getRobotLocation (int robot)
    {
        return _locations.get(robot);
    }

    public final Set<Character> getKeys ()
    {
        return _keys;
    }

    public final int getSteps ()
    {
        return _steps;
    }

    public final String getId ()
    {
        return _id;
    }
    
    @Override
    public int hashCode ()
    {
        return _locations.hashCode();
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

            // assume order matters for equality too!

            if (temp._locations.size() == _locations.size())
            {
                for (int i = 0; i < temp._locations.size(); i++)
                {
                    if (!temp._locations.get(i).equals(_locations.get(i)))
                        return false;
                }
                    
                return temp._keys.containsAll(_keys);
            }
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "Journey < "+_id+ ", " + _steps+" >";
    }

    private List<Coordinate> _locations;
    private Set<Character> _keys;
    private int _steps;
    private String _id;
}