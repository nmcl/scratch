import java.util.*;

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

    @Override
    public String toString ()
    {
        return "< left: "+_left+", right: "+_right+">";
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_left, _right);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            CoordinatePair temp = (CoordinatePair) obj;

            return ((_left.equals(temp._left)) && (_right.equals(temp._right)));
        }

        return false;
    }

    private Coordinate _left;
    private Coordinate _right;
}