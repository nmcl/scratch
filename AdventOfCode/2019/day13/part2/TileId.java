public class TileId
{
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int BLOCK = 2;
    public static final int PADDLE = 3;
    public static final int BALL = 4;

    public static final String idToString (int id)
    {
        switch (id)
        {
            case EMPTY:
                return "empty";
            case WALL:
                return "wall";
            case BLOCK:
                return "block";
            case PADDLE:
                return "paddle";
            case BALL:
                return "ball";
            default:
                return "unknown";
        }
    }
    
    private TileId ()
    {
        // stop instantiation.
    }
}