import java.util.*;

public class LanternFish
{
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-help]");
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

        Vector<Integer> ages = Util.loadAges(DATA_FILE, debug);
        Generator g = new Generator(debug);
        Vector<Integer> fish = g.evolve(80, ages);

        System.out.println("Number of fish after 80 days: "+fish.size());
    }
}