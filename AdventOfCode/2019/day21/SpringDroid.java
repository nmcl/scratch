import java.util.*;

public class SpringDroid
{
    public static final int MAX_INSTRUCTIONS = 15;

    public SpringDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
    }

    private Intcode _computer;
    private boolean _debug;
}