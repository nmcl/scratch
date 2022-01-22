import java.util.*;

public class ConwayCubes
{
    public static final String WORLD_DATA = "world.txt";

    public static final int ITERATIONS = 6;

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
            Verifier v = new Verifier(ITERATIONS, debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Dimension dim = new Dimension(WORLD_DATA, ITERATIONS, debug);
        int active = dim.cycle();

        System.out.println("After "+ITERATIONS+" number of active cubes: "+active);
    }
}