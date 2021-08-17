public class Verifier
{
    public static final String EXAMPLE_1 = "example1.txt";
    public static final int EXAMPLE_1_STEPS = 26;  // problem statement doesn't ask for shortest path
    public static final String EXAMPLE_2 = "example2.txt";
    public static final int EXAMPLE_2_STEPS = 58;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Maze theMaze = new Maze(EXAMPLE_1, _debug);
        Traveller theTraveller = new Traveller(theMaze, _debug);

        System.out.println(theMaze);

        System.out.println(theMaze.printWithPortals());


        return true;
    }

    private boolean _debug;
}