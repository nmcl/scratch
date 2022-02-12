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

        if (_debug)
        {
            System.out.println("Loaded:\n");
            
            for (int i = 0; i < rules.length; i++)
                System.out.println(rules[i]);

            for (int j = 0; j < messages.length; j++)
                System.out.println(messages[j]);
        }

        Matcher m = new Matcher(rules, _debug);
        Message[] msgs = m.matchRule(0, messages);

        System.out.println("\nGot back "+msgs);
        
        // check messages are equivalent even if different orders

        if ((msgs != null) && (msgs.length == EXAMPLE_MATCH.length))
        {
            for (int i = 0; i < EXAMPLE_MATCH.length; i++)
            {
                boolean found = false;

                for (int j = 0; (j < msgs.length) && !found; j++)
                {
                    if (msgs[j].equals(EXAMPLE_MATCH[i]))
                        found = true;
                }

                if (!found)
                    return false;
            }

            return true;
        }

        return false;
    }

    private boolean _debug;
}