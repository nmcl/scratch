import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int MAX_X = 9;
    public static final int MAX_Y = 9;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Vector<Coordinate> lines = Util.loadCoordinates(EXAMPLE_DATA, _debug);

        if (_debug)
        {
            System.out.println("\nHorizontal or vertical lines:\n");

            for (int i = 0; i < lines.size() -1; i++)
            {
                System.out.print(lines.elementAt(i));
                System.out.print(Util.DELIMITER);
                System.out.println(lines.elementAt(i+1));

                i++;
            }
        }

        Grid g = new Grid(MAX_X, MAX_Y, _debug);

        for (int i = 0; i < lines.size() -1; i++)
        {
            g.plot(lines.elementAt(i), lines.elementAt(i+1));

            i++;
        }

        return false;
    }

    private boolean _debug;
}