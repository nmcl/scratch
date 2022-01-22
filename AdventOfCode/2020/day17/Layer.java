public class Layer
{
    public Layer (int layer, int width, int height)
    {
        _cubes = new Cube[width][height];
        _layer = layer;

        for (int y = 0; y < width; y++)
        {
            for (int x = 0; x < height; x++)
            {
                _cubes[y][x] = new Cube(new ThreeDPoint(x, y, _layer));
            }
        }
    }

    public final void activate (ThreeDPoint coord)
    {
        _cubes[coord.getY()][coord.getX()].activate();
    }

    public final void deactivate (ThreeDPoint coord)
    {
        _cubes[coord.getY()][coord.getX()].deactivate();
    }

    @Override
    public String toString ()
    {
        String str = "z="+_layer+"\n";

        for (int y = 0; y < _cubes.length; y++)
        {
            for (int x = 0; x < _cubes[0].length; x++)
            {
                if (_cubes[y][x].isActive())
                    str += CubeId.ACTIVE;
                else
                    str += CubeId.INACTIVE;
            }

            str += "\n";
        }

        return str;
    }

    private Cube[][] _cubes;
    private int _layer;
}