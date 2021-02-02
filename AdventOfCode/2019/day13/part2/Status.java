public class Status
{
    /**
     * The status of the computer.
     */

    public static final int CREATED = 0;
    public static final int PAUSED = 1;
    public static final int RUNNING = 2;
    public static final int HALTED = 3;

    private Status ()
    {
        // stop instantiation.
    }
}