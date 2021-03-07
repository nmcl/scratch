public class DroidMovement
{
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;

    public static final int backupDirection (int direction)
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

    public static final Coordinate[] getNextPositions (Coordinate coord)
    {
        Coordinate[] coords = new Coordinate[4];

        // N, E, S, W

        coords[0] = getPosition(coord, NORTH);
        coords[1] = getPosition(coord, EAST);
        coords[2] = getPosition(coord, SOUTH);
        coords[3] = getPosition(coord, WEST);

        return coords;
    }

    public static final Coordinate getPosition (Coordinate coord, int direction)
    {
        switch (direction)
        {
            case NORTH:
                return new Coordinate(coord.getX(), coord.getY() -1);
            case EAST:
                return new Coordinate(coord.getX() -1, coord.getY());
            case SOUTH:
                return new Coordinate(coord.getX(), coord.getY() +1);
            case WEST:
                return new Coordinate(coord.getX() +1, coord.getY());
            default:
            {
                System.out.println("Unknown direction: "+direction);

                return coord;
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