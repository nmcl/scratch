public class Verifier
{
    public static final String EXAMPLE_1 = "example1.txt";
    public static final int EXAMPLE_1_STEPS = 23;
    public static final String EXAMPLE_2 = "example2.txt";
    public static final int EXAMPLE_2_STEPS = 58;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Maze theMaze = new Maze(EXAMPLE_1, _debug);

        System.out.println(theMaze);

        return true;
    }

    private boolean _debug;
}