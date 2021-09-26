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

    public static final int CENTRE_X = 2;
    public static final int CENTRE_Y = 2;

    public static final int TOP_INNER_EDGE_Y = 1;
    public static final int BOTTOM_INNER_EDGE_Y = 3;

    public static final int LEFT_INNER_EDGE_X = 1;
    public static final int RIGHT_INNER_EDGE_X = 3;

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

    public static final void bottomInnerEdge (ThreeDPoint current, HashSet<ThredDPoint> tiles)
    {
        tiles.add(new ThreeeDPoint(0, 4, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(1, 4, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(2, 4, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(3, 4, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(4, 4, current.getZ() + 1));
    }

    public static final void topInnerEdge (ThreeDPoint current, HashSet<ThreeDPoint> tiles)
    {
        tiles.add(new ThreeeDPoint(0, 0, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(1, 0, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(2, 0, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(3, 0, current.getZ() + 1));
        tiles.add(new ThreeeDPoint(4, 0, current.getZ() + 1));
    }

    private GridData ()
    {
    }
}