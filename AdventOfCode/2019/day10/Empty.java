public class Empty extends MapEntry
{
    public static final char EMPTY = '.';

    public Empty (int x, int y)
    {
        super(x, y);
    }

    public boolean empty ()
    {
        return true;
    }

    public String toString ()
    {
        return Character.toString(EMPTY);
    }
}