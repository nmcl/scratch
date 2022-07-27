import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int EXAMPLE_BLACK_TILES = 10;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> lines = Util.readLines(EXAMPLE_DATA);
        Renovation rv = new Renovation(_debug);
        Vector<Coordinate> blackTiles = rv.getBlackTiles(lines);

        if (blackTiles.size() == EXAMPLE_BLACK_TILES)
            return true;
        else
            System.out.println("Wrong number of black tiles: "+blackTiles.size());

        return false;
    }

    private boolean _debug;
}