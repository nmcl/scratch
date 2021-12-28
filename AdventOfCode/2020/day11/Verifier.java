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

        for (int i = 0; i < 5; i++)
        {
            if (_debug)
                System.out.println("Iteration: "+(i+1));

            evolution[i] = new Grid(EXAMPLE_BASE+""+(i+1)+".txt", _debug);

            thePlane.evolve();

            System.out.println("\nAfter "+(i+1)+" round:\n");
            System.out.println(thePlane);

            if (!evolution[i].equals(thePlane))
                return false;
        }

        return true;
    }

    private boolean _debug;
}