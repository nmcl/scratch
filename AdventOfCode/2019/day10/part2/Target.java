import java.util.Objects;

class Target
{
    public Target (Asteroid laser, Asteroid toDestroy)
    {
        _theTarget = toDestroy;
        _angle = laser.angleTo(toDestroy);
        _distance = laser.getPosition().distanceBetween(toDestroy.getPosition());
    }

    public Asteroid toDestroy ()
    {
        return _theTarget;
    }

    public final String getAngle ()
    {
        return _angle;
    }

    public final int getDistance ()
    {
        return _distance;
    }

    @Override
    public String toString ()
    {
        return _theTarget.getPosition().toString()+" "+_angle+" "+_distance;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_angle, _distance);
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
            Target temp = (Target) obj;

            return (_theTarget.equals(temp._theTarget) && (_angle == temp._angle) && (_distance == temp._distance));
        }

        return false;
    }

    private Asteroid _theTarget;
    private String _angle = "";
    private int _distance = 0;
}