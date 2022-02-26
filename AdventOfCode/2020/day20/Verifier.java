import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

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

        return false;
    }

    private boolean _debug;
}