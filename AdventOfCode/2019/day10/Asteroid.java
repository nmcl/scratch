public class Asteroid extends MapEntry
{
    public static final char ASTEROID = '#';

    public Asteroid (int x, int y)
    {
        super(x, y);
    }

    public boolean empty ()
    {
        return false;
    }

    public String toString ()
    {
        return Character.toString(ASTEROID);
    }
}