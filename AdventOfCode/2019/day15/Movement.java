public class Movement
{
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;

    public static final String toString (int direction)
    {
        switch (direction)
        {
            case NORTH:
                return "NORTH";
            case SOUTH:
                return "SOUTH";
            case WEST:
                return "WEST";
            case EAST:
                return "EAST";
            default
                return "UNKNOWN";
        }
    }

    private Movement ()  // prevent instantiation
    {
    }
}