public class Level
{
    public static final int CENTRE_HEIGHT = 3;  // TODO only makes sense for 5x5 so need to fix this!!
    public static final int CENTRE_WIDTH = 3;

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

        _theWorld[CENTRE_HEIGHT][CENTRE_WIDTH] = new Tile(TileId.NESTED_GRID);
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if ((i == CENTRE_HEIGHT) && (j == CENTRE_WIDTH))
                    str += TileId.NESTED_GRID;
                else
                    str += _theWorld[i][j];
            }
            
            str += "\n";
        }

        return str;
    }

    private Tile[][] _theWorld;
    private int _level;
    private int _height;
    private int _width;
    private boolean _debug;
}