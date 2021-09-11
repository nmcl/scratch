public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final String MINUTE_BASE = "minute";

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

        return true;
    }

    private boolean _debug;
}