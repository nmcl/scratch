import java.util.Objects;

/*
 * Represents the Submarine's Course
 */

public class Course
{
    public Course (int position, int y, int depth)
    {
        _position = position;
        _y = y;
        _depth = depth;
    }

    protected Course (Course toCopy)
    {
        _position = toCopy._position;
        _y = toCopy._y;
        _depth = toCopy._depth;
    }

    public final int getPosition ()
    {
        return _position;
    }

    public final int getY ()
    {
        return _y;
    }

    public final int getDepth ()
    {
        return _depth;
    }

    @Override
    public String toString ()
    {
        return "Course < "+_position+", "+_y+", "+_depth+" >";
    }
    
    @Override
    public int hashCode ()
    {
        return Objects.hash(_position, _y, _depth);
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
            Course temp = (Course) obj;

            return ((_position == temp._position) && (_y == temp._y) && (_depth == temp._depth));
        }

        return false;
    }

    protected int _position;
    protected int _y;
    protected int _depth;
}