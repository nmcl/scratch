public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final int EXAMPLE_STEPS = 396;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Maze theMaze = new Maze(EXAMPLE, _debug);
        Traveller theTraveller = new Traveller(theMaze, _debug);

        if (_debug)
        {
            System.out.println(theMaze);

            System.out.println(theMaze.printWithPortals());
        }

        int numberOfSteps = theTraveller.findAllKeys();

        if (numberOfSteps == EXAMPLE_STEPS)
            return true;
        
        System.out.println("Failed to verify "+EXAMPLE);

        return false;
    }

    private boolean _debug;
}