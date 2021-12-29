public class Facing
{
    public char getNextFacting (char current, char action)
    {
        // no real error checking!

        switch (current)
        {
            case Direction.NORTH:
            {
                if (action == Action.LEFT)
                    return Direction.EAST;
                else
                    return Direction.WEST;
            }
            case Direction.SOUTH:
            {
                if (action == Action.LEFT)
                    return Direction.WEST;
                else
                    return Direction.EAST;
            }
            case Direction.EAST:
            {
                if (action == Action.LEFT)
                    return Direction.SOUTH;
                else
                    return Direction.NORTH;
            }
            default:
            {
                if (action == Action.LEFT)
                    return Direction.NORTH;
                else
                    return Direction.SOUTH;
            }
        }
    }

    private Facing ()
    {
    }
}