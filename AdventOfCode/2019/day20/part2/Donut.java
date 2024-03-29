public class Donut
{
    public static final String DATA = "maze.txt";

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Failed to verify.");
        }
        else
        {
            Maze theMaze = new Maze(DATA, debug);
            Traveller theTraveller = new Traveller(theMaze, debug);

            if (debug)
            {
                System.out.println(theMaze);

                System.out.println(theMaze.printWithPortals());
            }

            int numberOfSteps = theTraveller.findAllKeys();

            System.out.println("Number of steps: "+numberOfSteps);
        }
    }
}