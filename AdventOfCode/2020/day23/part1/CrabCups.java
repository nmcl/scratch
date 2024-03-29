public class CrabCups
{
    public static final String INPUT = "523764819";

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
                System.out.println("Verified ok!");
            else
                System.out.println("Verify failed!");
        }
        
        Game theGame = new Game(debug);

        String result = theGame.play(INPUT, 100);

        System.out.println("Labels after 100 rounds: "+result);
    }
}