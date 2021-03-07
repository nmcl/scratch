public class DroidStatus
{
    public static final int COLLISION = 0;
    public static final int MOVED = 1;
    public static final int ARRIVED = 2;
    public static final int BACKUP = 3;

    public static final String toString (int status)
    {
        switch (status)
        {
            case COLLISION:
                return "COLLISION";
            case MOVED:
                return "MOVED";
            case ARRIVED:
                return "ARRIVED";
            case BACKUP:
                return "BACKUP";
            default:
                return "UNKNOWN";
        }
    }

    private DroidStatus ()  // prevent instantiation
    {
    }
}