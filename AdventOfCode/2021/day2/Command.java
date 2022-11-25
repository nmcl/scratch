public class Command
{
    public static final String FORWARD = "forward";
    public static final String DOWN = "down";
    public static final String UP = "up";

    public Command (String cmd, int value)
    {
        _cmd = cmd;
        _amount = value;
    }

    public final String cmd ()
    {
        return _cmd;
    }

    public final int amount ()
    {
        return _amount;
    }

    private String _cmd;
    private int _amount;
}