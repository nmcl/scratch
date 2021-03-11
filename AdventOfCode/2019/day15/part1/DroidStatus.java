public class DroidStatus
{
    public static final int COLLISION = 0;
    public static final int MOVED = 1;
    public static final int ARRIVED = 2;
    public static final int VISITED = 3;
    public static final int BACKTRACKED = 4;
    public static final int ERROR = 10;

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
            case VISITED:
                return "VISITED";
            case BACKTRACKED:
                return "BACKTRACKED";
            default:
                return "ERROR";
        }
    }

    private DroidStatus ()  // prevent instantiation
    {
    }
}