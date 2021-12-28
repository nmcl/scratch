import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_BASE = "example";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Grid thePlane = new Grid(EXAMPLE_BASE+".txt", _debug);
        Grid[] evolution = new Grid[5];

        if (_debug)
            System.out.println(thePlane);

        for (int i = 1; i < 6; i++)
        {
            evolution[i] = new Grid(EXAMPLE_BASE+""+i+".txt", _debug);

            thePlane.evolve();

            System.out.println("\nAfter "+i+" round:");
            System.out.println(thePlane);

            if (!evolution[i].equals(thePlane))
                return false;
        }

        return true;
    }

    private boolean _debug;
}