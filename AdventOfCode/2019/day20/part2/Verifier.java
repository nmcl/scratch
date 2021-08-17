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
        Maze theMaze = new Maze(EXAMPLE_1, _debug);
        Traveller theTraveller = new Traveller(theMaze, _debug);

        if (_debug)
        {
            System.out.println(theMaze);

            System.out.println(theMaze.printWithPortals());
        }

        int numberOfSteps = theTraveller.findAllKeys();

        if (numberOfSteps == EXAMPLE_1_STEPS)
        {
            theMaze = new Maze(EXAMPLE_2, _debug);
            theTraveller = new Traveller(theMaze, _debug);

            if (_debug)
            {
                System.out.println(theMaze);

                System.out.println(theMaze.printWithPortals());
            }
    
            numberOfSteps = theTraveller.findAllKeys();
    
            if (numberOfSteps == EXAMPLE_2_STEPS)
                return true;
            else
                System.out.println("Failed to verify "+EXAMPLE_2);
        }
        else
            System.out.println("Failed to verify "+EXAMPLE_1);

        return false;
    }

    private boolean _debug;
}