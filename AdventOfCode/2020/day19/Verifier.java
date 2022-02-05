public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final String[] EXAMPLE_MATCH = { "ababbb", "abbbab" };

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Rule[] rules = Util.loadRules(EXAMPLE_DATA, _debug);
        Message[] messages = Util.loadMessages(EXAMPLE_DATA, _debug);

        for (int i = 0; i < rules.length; i++)
            System.out.println(rules[i]);

        for (int j = 0; j < messages.length; j++)
            System.out.println(messages[j]);

        return false;
    }

    private boolean _debug;
}