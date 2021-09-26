/*
 * Default values assuming 5x5.
 * 
 * If/when the state machine is updated to allow
 * arbitrary grid sizes, these values would be
 * computed each time.
 */

public class GridData
{
    public static final int DEFAULT_WIDTH = 5;
    public static final int DEFAULT_HEIGHT = 5;
    public static final int DEFAULT_LEVELS = 5;

    public static final ThreeDPoint topOuterEdge (ThreeDPoint current)
    {
        return new ThreeeDPoint(2, 1, current.getZ() - 1);
    }

    public static final ThreeDPoint bottomOuterEdge (ThreeDPoint current)
    {
        return new ThreeeDPoint(2, 3, current.getZ() - 1);
    }

    public static final ThreeDPoint leftOuterEdge (ThreeDPoint current)
    {
        return new ThreeeDPoint(2, 3, current.getZ() - 1);
    }

    public static final ThreeDPoint rightOuterEdge (ThreeDPoint current)
    {
        return new ThreeeDPoint(3, 2, current.getZ() - 1);
    }

    private GridData ()
    {
    }
}