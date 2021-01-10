public class Asteroid extends MapEntry
{
    public static final char ASTEROID_REPRESENTATION = '#';

    public Asteroid (int x, int y)
    {
        super(x, y);
    }

    public boolean isEmpty ()
    {
        return false;
    }

    public String toString ()
    {
        return Character.toString(ASTEROID_REPRESENTATION);
    }
}