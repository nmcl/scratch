public class Joystick
{
    public static final int NEUTRAL_POSITION = 0;
    public static final int TILTED_LEFT = -1;
    public static final int TILTED_RIGHT = 1;

    public Joystick ()
    {
        _position = NEUTRAL_POSITION;
    }

    public final int getPosition ()
    {
        return _position;
    }

    public final void setPosition (int p)
    {
        _position = p;
    }

    @Override
    public String toString ()
    {
        return ""+_position;

        /*
        switch (_position)
        {
            case NEUTRAL_POSITION:
                return "neutral position";
            case TILTED_LEFT:
                return "tilted left";
            case TILTED_RIGHT:
                return "tilted right";
            default:
                return "unknown";
        }*/
    }

    private int _position;
}