public class Empty extends MapEntry
{
    public static final char EMPTY_REPRESENTATION = '.';

    public Empty (int x, int y)
    {
        super(x, y);
    }

    public boolean isEmpty ()
    {
        return true;
    }

    public String toString ()
    {
        return Character.toString(EMPTY_REPRESENTATION);
    }
}