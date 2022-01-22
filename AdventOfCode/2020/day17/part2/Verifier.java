public class Verifier
{
    public static final String EXAMPLE_WORLD = "example.txt";

    public static final int ACTIVE_CUBES = 848;

    public Verifier (int iterations, boolean debug)
    {
        _iterations = iterations;
        _debug = debug;
    }

    public boolean verify ()
    {
        Dimension dim = new Dimension(EXAMPLE_WORLD, _iterations, _debug);
        int active = dim.cycle();

        if (active == ACTIVE_CUBES)
            return true;

        System.out.println("Invalid active cubes: "+active);

        return false;
    }

    private int _iterations;
    private boolean _debug;
}