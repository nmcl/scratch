public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final long EXAMPLE_MATCH = 12;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Rule[] rules = Util.loadRules(EXAMPLE_DATA, _debug);
        Message[] messages = Util.loadMessages(EXAMPLE_DATA, _debug);

        if (_debug)
        {
            System.out.println("Loaded:\n");
            
            for (int i = 0; i < rules.length; i++)
                System.out.println(rules[i]);

            for (int j = 0; j < messages.length; j++)
                System.out.println(messages[j]);
        }

        Matcher m = new Matcher(rules, _debug);
        long matching = m.numberOfMatchingMessages(0, messages);

        if (matching == EXAMPLE_MATCH.length)
            return true;

        System.out.println("\nGot back wrong count: "+matching);

        return false;
    }

    private boolean _debug;
}