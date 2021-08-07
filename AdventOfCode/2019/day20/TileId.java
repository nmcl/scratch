public class TileId
{
    public static final char WALL = '#';
    public static final char SPACE = ' ';
    public static final char PASSAGE = '.';
    public static final char PORTAL = '%';

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static String position (int i)
    {
        switch (i)
        {
            case UP:
                return "UP";
            case DOWN:
                return "DOWN";
            case LEFT:
                return "LEFT";
            case RIGHT:
                return "RIGHT";
            default:
                return "UNKNOWN";
        }
    }

    private TileId ()  // prevent instantiation!
    {
    }
}