public class Verifier
{
    public static final String EXAMPLE = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Grid theWorld = new Grid(EXAMPLE);

        if (_debug)
            System.out.println(theWorld);

        for (int i = 0; i < 4; i++)
        {
            theWorld.evolve();

            System.out.println("After "+(i+1)+" minute:");
            System.out.println(theWorld);
        }

        return false;
    }

    private boolean _debug;
}