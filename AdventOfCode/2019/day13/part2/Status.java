public class Status
{
    /**
     * The status of the computer.
     */

    public static final int CREATED = 0;
    public static final int PAUSED = 1;
    public static final int RUNNING = 2;
    public static final int HALTED = 3;
    public static final int WAITING_FOR_INPUT = 4;

    public static final String statusToString (int status)
    {
        switch (status)
        {
            case CREATED:
                return "CREATED";
            case PAUSED:
                return "PAUSED";
            case RUNNING:
                return "RUNNING";
            case HALTED:
                return "HALTED";
            case WAITING_FOR_INPUT:
                return "WAITING_FOR_INPUT";
            default:
                return "UNKNOWN";
        }
    }
    
    private Status ()
    {
        // stop instantiation.
    }
}