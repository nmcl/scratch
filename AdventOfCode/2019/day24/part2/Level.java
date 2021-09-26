import java.util.*;

public class Level
{
    public Level (int layer, int height, int width, boolean debug)
    {
        _level = new HashSet<ThreeDPoint>();
        _layer = layer;
        _height = height;
        _width = width;
        _debug = debug;
    }

    public final int getLevel ()
    {
        return _layer;
    }

    public final void addBug (ThreeDPoint location)
    {
        _level.add(location);
    }

    public final HashSet<ThreeDPoint> getBugs ()
    {
        return _level;
    }

    @Override
    public String toString ()
    {
        String str = "";
        
        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if ((i == GridData.CENTRE_Y) && (j == GridData.CENTRE_X))
                    str += TileId.NESTED_GRID;
                else
                {
                    ThreeDPoint point = new ThreeDPoint(i, j, _layer);

                    if (_level.contains(point))
                        str += TileId.BUG;
                    else
                        str += TileId.EMPTY_SPACE;
                }
            }

            str += "\n";
        }

        return str;
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
    private int _layer;
    private int _height;
    private int _width;
    private boolean _debug;
}