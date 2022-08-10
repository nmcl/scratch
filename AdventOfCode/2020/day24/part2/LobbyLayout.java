import java.util.*;

public class LobbyLayout
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

        Vector<String> lines = Util.readLines(DATA_FILE);
        Renovation rv = new Renovation(debug);
        Vector<Coordinate> blackTiles = rv.tilesOfLife(lines, 1);

        System.out.println("Number of black tiles: "+blackTiles.size());
    }
}
        