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

            System.exit(0);
        }
        
        Game theGame = new Game(debug);
        long result = theGame.play(INPUT, 10000000);

        System.out.println("Game result: "+result);
    }
}