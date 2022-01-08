public class CubeId
{
    public static final char ACTIVE = '#';
    public static final char INACTIVE = '.';

    public static boolean valid (char check)
    {
        if ((check == ACTIVE) || (check == INACTIVE))
            return true;
        else
            return false;
    }

    private TileId ()
    {
    }
}