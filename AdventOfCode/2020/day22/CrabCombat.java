public class CrabCombat
{
    public static final String DATA_FILE = "data.txt";

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
        }
        else
        {
            Deck[] decks = Util.loadRules(DATA_FILE, debug);

            if (debug)
            {
                System.out.println(decks[0]);
                System.out.println(decks[1]);
            }
            
            Game g = new Game(debug);
            int score = g.play(decks);
    
            System.out.println("Winning player's score: "+score);
        }
    }
}