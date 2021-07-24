public class CoordinatePair
{
    public CoordinatePair (Coordinate left, Coordinate right)
    {
        _left = left;
        _right = right;
    }

    public final Coordinate getLeft ()
    {
        return _left;
    }

    public final Coordinate getRight ()
    {
        return _right;
    }

    private Coordinate _left;
    private Coordinate _right;
}