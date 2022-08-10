import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int EXAMPLE_BLACK_TILES_DAY_1 = 15;
    public static final int EXAMPLE_BLACK_TILES_DAY_10 = 37;
    public static final int EXAMPLE_BLACK_TILES_DAY_100 = 2208;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> lines = Util.readLines(EXAMPLE_DATA);
        Renovation rv = new Renovation(_debug);
        HashSet<Coordinate> blackTiles = rv.tilesOfLife(lines, 1);

        if (blackTiles.size() == EXAMPLE_BLACK_TILES_DAY_1)
        {
            blackTiles = rv.tilesOfLife(lines, 10);

            if (blackTiles.size() == EXAMPLE_BLACK_TILES_DAY_10)
            {
                blackTiles = rv.tilesOfLife(lines, 100);

                if (blackTiles.size() == EXAMPLE_BLACK_TILES_DAY_100)
                    return true;
                else
                    System.out.println("Wrong number of black tiles after day 100: "+blackTiles.size());
            }
            else
                System.out.println("Wrong number of black tiles after day 10: "+blackTiles.size());
        }
        else
            System.out.println("Wrong number of black tiles after day 1: "+blackTiles.size());

        return false;
    }

    private boolean _debug;
}