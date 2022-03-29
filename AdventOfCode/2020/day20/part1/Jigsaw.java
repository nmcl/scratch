import java.util.*;
import java.io.*;

public class Jigsaw
{
    public static final String INPUT_FILE = "data.txt";

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
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Vector<Tile> tiles = Util.loadData(INPUT_FILE);

        if (debug)
        {
            Iterator<Tile> iter = tiles.iterator();

            System.out.println("Loaded:\n\n");

            while (iter.hasNext())
            {
                System.out.println(iter.next());
            }
        }

        Solver s = new Solver(debug);
        Vector<Tile> corners = s.solve(tiles);

        if (corners.size() == 4)
        {
            long result = 1L;

            for (int i = 0; i < 4; i++)
                result *= corners.elementAt(i).getID();

            System.out.println("Result from four corner tiles: "+result);
        }
        else
            System.out.println("Invalid number of corners: "+corners.size());
    }
}