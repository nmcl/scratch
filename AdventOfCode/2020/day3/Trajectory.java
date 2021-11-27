public class Trajectory
{
    public static final String MAP_FILE = "map.txt";

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
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verified failed!");

            System.exit(0);
        }

        Map theMap = new Map(MAP_FILE, debug);
        Taboggan theTaboggan = new Taboggan(theMap, debug);
        int numberOfTrees = theTaboggan.move();

        System.out.println("Number of trees encountered: "+numberOfTrees);
    }
}