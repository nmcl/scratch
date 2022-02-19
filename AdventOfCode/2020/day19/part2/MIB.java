public class MIB
{
    public static final String MESSAGE_FILE = "messages.txt";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Rule[] rules = Util.loadRules(MESSAGE_FILE, debug);
        Message[] messages = Util.loadMessages(MESSAGE_FILE, debug);
        Matcher m = new Matcher(rules, debug);
        long matching = m.numberOfMatchingMessages(0, messages);

        System.out.println("Messages which completely match rule 0: "+matching);
    }
}