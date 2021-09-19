public class Level
{
    public Level (int height, int width, int level, boolean debug)
    {
        _level = level;
        _height = height;
        _width = width;
        _debug = debug;

        _theWorld = new Tile[_height][_width];

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                _theWorld[i][j] = new Tile(TileId.EMPTY_SPACE);
            }
        }

        _theWorld[3][3] = new Tile(TileId.NESTED_GRID);
    }

    private Tile[][] _theWorld;
    private int _level;
    private int _height;
    private int _width;
    private boolean _debug;
}