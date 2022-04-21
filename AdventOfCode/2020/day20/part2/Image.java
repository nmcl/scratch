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

        /*
        0,0 0,1 0,2
        1,0 1,1 1,2
        2,0 2,1 2,2
*/

        for (int i = 0; i < y; i++)
        {
            int index = 0;
            Tile t = _theWorld[i][index];

            for (int j = 0; j < x; j++)
            {
                while (index < t.numberOfLines())
                {
                    t = _theWorld[i][j];

                    str += t.line(index);

                    index++;
                }

                str += "\n";
            }

            str += "\n";
        }

        return str;
    }

    private Tile[][] _theWorld;
    private boolean _debug;
}