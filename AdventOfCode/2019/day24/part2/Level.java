import java.util.*;

public class Level
{
    public Level (boolean debug)
    {
        _level = new HashSet<ThreeDPoint>();
        _debug = debug;
    }

    public void addBug (ThreeDPoint location)
    {
        _level.add(location);
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
            Level temp = (Level) obj;

            if (temp._level.size() == _level.size())
            {
                Iterator<ThreeDPoint> iter = _level.iterator();

                while (iter.hasNext())
                {
                    if (!temp._level.contains(iter.next()))
                        return false;
                }

                return true;
            }
        }

        return false;
    }

    private HashSet<ThreeDPoint> _level;
    private boolean _debug;
}