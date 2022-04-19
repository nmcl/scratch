public class Image
{
    /*
     * 144 tiles in the example. Hard wire
     * this for now but could make it dynamic.
     */

    public static final int MAX_X = 12;
    public static final int MAX_Y = 12;

    public static final String NO_TILE = "xxxxxxxx";

    public Image ()
    {
        _theWorld = new Tile[MAX_Y][MAX_X];

        for (int i = 0; i < MAX_Y; i++)
        {
            for (int j = 0; j < MAX_X; j++)
            {
                _theWorld[i][j] = null;
            }
        }
    }

    public void addTile (int x, int y, Tile t)
    {
        _theWorld[y][x] = t;
    }

    @Override
    public String toString ()
    {
        String str = null;
        int index = 0;
        int maxIndex = 0;

        do
        {
            for (int i = 0; i < _theWorld.length; i++)
            {
                for (int j = 0; j < _theWorld[0].length; j++)
                {
                    Tile t = _theWorld[i][j];

                    if (t != null)
                    {
                        str += t.line(index);

                        maxIndex = t.numberOfLinex();
                    }
                    else
                        str += NO_TILE;
                }

                str += "\n";
            }

            index++;

        } while (index < maxIndex);

        return str;
    }

    private Tile[][] _theWorld;
}