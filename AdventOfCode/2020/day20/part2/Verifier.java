import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final long EXAMPLE_RESULT = 273;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Tile> tiles = Util.loadData(EXAMPLE_DATA);

        if (_debug)
        {
            Iterator<Tile> iter = tiles.iterator();

            System.out.println("Loaded:\n\n");

            while (iter.hasNext())
            {
                System.out.println(iter.next());
            }
        }

        Solver s = new Solver(_debug);
        Vector<Tile> corners = s.solve(tiles);

        if (corners.size() == 4)
        {
            long result = 1L;

            for (int i = 0; i < 4; i++)
                result *= corners.elementAt(i).getID();

            if (result == EXAMPLE_RESULT)
                return true;

            System.out.println("Invalud result: "+result);
        }
        else
            System.out.println("Invalid number of corners: "+corners.size());

        return false;
    }

    private boolean _debug;
}