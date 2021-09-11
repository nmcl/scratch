public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final String MINUTE_BASE = "minute";
    public static final String DUPLICATE_LAYOUT = "layout.txt";

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
        Grid previous = theWorld.snapshot();

        while (!found)
        {
            theWorld.evolve();

            System.out.println("Comparing\n"+theWorld+"\nwith\n"+previous);
            
            if (previous.equals(theWorld))
                found = true;
            else
                previous = theWorld.snapshot();
        }

        if (previous.equals(duplicate))
            return true;

        return false;
    }

    private boolean _debug;
}