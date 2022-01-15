import java.util.*;

public class Layer
{
    public Layer (int layer, int height, int width, boolean debug)
    {
        _cubes = new char[height][width];
        _layer = layer;
        _debug = debug;

        for (int i = 0; i < _cubes.length; i++)
        {
            for (int i = 0; j < _cubes[0].length; j++)
            {
                _cubes[i][j] = CubeId.INACTIVE;
            }
        }
    }

    public final int getLevel ()
    {
        return _layer;
    }

    @Override
    public String toString ()
    {
        String str = "";
        
        for (int i = 0; i < _cubes.length; i++)
        {
            for (int j = 0; j < _cubes[0].length; j++)
            {
                str += _cubes[i][j];
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
            Layer temp = (Layer) obj;

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

    private char[][] _cubes;
    private int _layer;
    private boolean _debug;
}