public class TileId
{
    public static final char BUG = '#';
    public static final char EMPTY_SPACE = '.';
    public static final char NESTED_GRID = '?';

    public static boolean valid (char check)
    {
        if ((check == BUG) || (check == EMPTY_SPACE))
            return true;
        else
            return false;
    }

    private TileId ()
    {
    }
}