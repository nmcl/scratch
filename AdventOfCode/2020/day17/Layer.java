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
            for (int j = 0; j < _cubes[0].length; j++)
            {
                _cubes[i][j] = CubeId.INACTIVE;
            }
        }
    }

    public final void activate (int x, int y)
    {
        _cubes[x][y] = CubeId.ACTIVE;
    }

    public final void deactivate (int x, int y)
    {
        _cubes[x][y] = CubeId.INACTIVE;
    }

    public final boolean empty ()
    {
        for (int i = 0; i < _cubes.length; i++)
        {
            for (int j = 0; j < _cubes[0].length; j++)
            {
                if (_cubes[i][j] == CubeId.ACTIVE)
                    return false;
            }
        }

        return true;
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

    private char[][] _cubes;
    private int _layer;
    private boolean _debug;
}