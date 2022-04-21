public class Image
{
    /*
     * 144 tiles in the example. Hard wire
     * this for now but could make it dynamic.
     */

    public static final int MAX_X = 12;
    public static final int MAX_Y = 12;

    public Image (boolean debug)
    {
        this(MAX_Y, MAX_X, debug);
    }

    public Image (int y, int x, boolean debug)
    {
        _theWorld = new Tile[y][x];

        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                _theWorld[i][j] = null;
            }
        }

        _debug = debug;
    }

    public void addTile (int x, int y, Tile t)
    {
        _theWorld[y][x] = t.removeBorders();
    }

    @Override
    public String toString ()
    {
        String str = "";
        int y = _theWorld.length;
        int x = _theWorld[0].length;

        for (int i = 0; i < y; i++)
        {
            Tile t = _theWorld[i][0];

            for (int j = 0; j < t.numberOfLines(); j++)
            {
                int index = 0;

                while (index < x)
                {
                    t = _theWorld[i][index];

                    str += t.line(j);

                    index++;
                }

                str += "\n";
            }
        }

        return str;
    }

    private Tile[][] _theWorld;
    private boolean _debug;
}