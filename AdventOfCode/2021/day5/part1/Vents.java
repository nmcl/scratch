import java.util.*;

public class Vents
{
    public static final String DATA_FILE = "input.txt";
    public static final int MAX_X = 1000;
    public static final int MAX_Y = 1000;

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

        Vector<Coordinate> lines = Util.loadCoordinates(DATA_FILE, debug);
        Grid g = new Grid(MAX_X, MAX_Y, debug);

        for (int i = 0; i < lines.size() -1; i++)
        {
            g.plot(lines.elementAt(i), lines.elementAt(i+1));

            i++;
        }

        System.out.println("Number of overlapping lines: "+g.overlappingLines());
    }
}