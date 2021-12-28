public class CellId
{
    public static final char EMPTY_SEAT = 'L';
    public static final char FLOOR = '.';
    public static final char OCCUPIED_SEAT = '#';

    public static boolean valid (char check)
    {
        if ((check == EMPTY_SEAT) || (check == FLOOR) || (check == OCCUPIED_SEAT))
            return true;
        else
            return false;
    }

    private CellId ()
    {
    }
}