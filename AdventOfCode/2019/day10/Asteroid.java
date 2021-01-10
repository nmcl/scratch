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

    public String angleTo (Asteroid target)
    {
        float angle = (float) Math.toDegrees(Math.atan2(target.getPosition().getY() - getPosition().getY(), target.getPosition().getX() - getPosition().getX()));

        if (angle < 0)
            angle += 360;

        System.out.println("**fromAngle "+angle);

        return String.valueOf(angle);
    }

    public String toString ()
    {
        return Character.toString(ASTEROID_REPRESENTATION);
    }
}