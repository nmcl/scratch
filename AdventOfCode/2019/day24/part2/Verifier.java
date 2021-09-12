import java.util.*;

public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final String DEPTH_BASE = "depth";

    public static final long BUGS_PRESENT = 99;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Grid theWorld = new Grid(EXAMPLE, _debug);
        Grid[] evolution = new Grid[4];

        if (_debug)
            System.out.println(theWorld);

        for (int i = 0; i < 4; i++)
        {
            evolution[i] = new Grid(MINUTE_BASE+""+(i+1)+".txt", _debug);
            theWorld.evolve();

            System.out.println("After "+(i+1)+" minute:");
            System.out.println(theWorld);

            if (!evolution[i].equals(theWorld))
                return false;
        }

        boolean found = false;
        Grid duplicate = new Grid(DUPLICATE_LAYOUT, _debug);
        Vector<Grid> previous = new Vector<Grid>();

        previous.add(theWorld.snapshot());

        while (!found)
        {
            theWorld.evolve();

            if (previous.contains(theWorld))
                found = true;
            else
                previous.add(theWorld.snapshot());
        }

        if (theWorld.equals(duplicate))
        {
            if (theWorld.biodiversityRating() == BIODIVERSITY_RATING)
                return true;
        }

        return false;
    }

    private boolean _debug;
}