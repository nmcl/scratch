import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final long[] EXAMPLE_ROW_1 = { 1951, 2311, 3079 };
    public static final long[] EXAMPLE_ROW_2 = { 2729, 1427, 2473 };
    public static final long[] EXAMPLE_ROW_3 = { 2971, 1489, 1171 };
    public static final long EXAMPLE_RESULT = 20899048083289L;

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

            System.out.println("result "+result);
        }
        else
            System.out.println("Invalid number of corners: "+corners.size());

        return false;
    }

    private boolean _debug;
}