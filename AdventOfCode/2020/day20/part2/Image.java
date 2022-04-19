public class Image
{
    /*
     * 144 tiles in the example. Hard wire
     * this for now but could make it dynamic.
     */

    public static final int MAX_X = 12;
    public static final int MAX_Y = 12;

    public static final String NO_TILE = "NO TILE PRESENT";

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

    @Override
    public String toString ()
    {
        String str = null;

        return str;
    }

    private Tile[][] _theWorld;
}