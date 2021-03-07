public class DroidMovement
{
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;

    public static final int backup (int direction)
    {
        switch (direction)
        {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            case EAST:
                return WEST;
            default:
            {
                System.out.println("Error - invalid direction: "+direction);

                return 0;
            }
        }
    }

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
            default:
                return "UNKNOWN";
        }
    }

    public static final String toString (String direction)
    {
        switch (direction)
        {
            case "1":
                return "NORTH";
            case "2":
                return "SOUTH";
            case "3":
                return "WEST";
            case "4":
                return "EAST";
            default:
                return "UNKNOWN";
        }
    }

    private DroidMovement ()  // prevent instantiation
    {
    }
}