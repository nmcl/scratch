import java.util.*;

// Game of Life! With an infinite plane.

public class Eris
{
    public static final String WORLD_DATA = "scan.txt";

    public static final int MINUTES = 200;

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-debug] [-help");

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
                System.out.println("Verified ok!");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Grid theWorld = new Grid(WORLD_DATA, debug);

        for (int i = 0; i < MINUTES; i++)
        {
            theWorld.evolve();
        }

        System.out.println("Evolved world after "+MINUTES+" minutes:\n"+theWorld);

        System.out.println("\nTotal bugs: "+theWorld.totalBugCount());
    }
}