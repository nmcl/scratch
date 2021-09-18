import java.util.*;

// Game of Life! With an infinite plane.
// or 3d?

public class Eris
{
    public static final String WORLD_DATA = "scan.txt";

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
        boolean found = false;
        Vector<Grid> previous = new Vector<Grid>();

        previous.add(theWorld.snapshot());

        while (!found)
        {
            theWorld.evolve();

            if (previous.contains(theWorld))
                found = true;
            else
                previous.add(theWorld.snapshot());
        }

        System.out.println("Biodiversity rating for the first layout that appears twice: "+theWorld.biodiversityRating());
    }
}