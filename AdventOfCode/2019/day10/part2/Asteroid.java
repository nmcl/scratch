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
        /*
         * Use atan to determine angle from one asteroid (point)
         * to another.
         * 
         * https://en.wikipedia.org/wiki/Atan2
         */

         /*
          * This time change the angle are expressed in radians:
          *
          * 1 radian = 180/pi or 57.29577 degrees;
          * 1 degree = pi/180
          * 0° is at 3 o'clock, (0 radians)
          * 0° is at 6 o'clock (radians = pi/2 or Math.PI/2.0),
          * 180° is at 9 o'clock (radians = pi radians or Math.PI),
          * 270° is at 12 o'clock(radians = 3 x pi/2 or 3.0 * Math.PI /2.0),
          * 360° is at 3 o'clock (radians = 2.0 * Math.PI)
          */

        double angle = Math.toDegrees(Math.atan2(target.getPosition().getY() - getPosition().getY(), target.getPosition().getX() - getPosition().getX())+Math.PI/2.0);

        if (angle < 0)
            angle += 360;

        return String.valueOf(angle);
    }

    public String toString ()
    {
        return Character.toString(ASTEROID_REPRESENTATION);
    }
}