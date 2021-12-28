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
        Grid theWorld = new Grid(EXAMPLE_BASE+"1.txt", _debug);

        System.out.println("Loaded:\n"+theWorld);

        Grid[] evolution = new Grid[4];

        if (_debug)
            System.out.println(theWorld);

        for (int i = 2; i < 6; i++)
        {
            evolution[i] = new Grid(EXAMPLE_BASE+""+i+".txt", _debug);
            theWorld.evolve();

            System.out.println("After "+i+" round:");
            System.out.println(theWorld);

            if (!evolution[i].equals(theWorld))
                return false;
        }

        return true;
    }

    private boolean _debug;
}