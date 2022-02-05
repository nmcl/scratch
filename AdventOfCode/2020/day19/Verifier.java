import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Rule> rules = Util.loadRules(EXAMPLE_DATA, _debug);
        Vector<Message> messages = Util.loadMessages(EXAMPLE_DATA, _debug);

        System.out.println(rules);
        System.out.println(messages);

        return false;
    }

    private boolean _debug;
}