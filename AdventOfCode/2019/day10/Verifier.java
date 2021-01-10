public class Verifier
{
    public static final String EXAMPLE_1_FILE = "example1.txt";
    public static final Coordinate EXAMPLE_1_RESULT = new Coordinate(3, 4);
    public static final int EXAMPLE_1_LOS = 8;
    public static final String EXAMPLE_2_FILE = "example2.txt";
    public static final Coordinate EXAMPLE_2_RESULT = new Coordinate(5, 8);
    public static final int EXAMPLE_2_LOS = 33;
    public static final String EXAMPLE_3_FILE = "example3.txt";
    public static final Coordinate EXAMPLE_3_RESULT = new Coordinate(1, 2);
    public static final int EXAMPLE_3_LOS = 35;
    public static final String EXAMPLE_4_FILE = "example4.txt";
    public static final Coordinate EXAMPLE_4_RESULT = new Coordinate(6, 3);
    public static final int EXAMPLE_4_LOS = 41;
    public static final String EXAMPLE_5_FILE = "example5.txt";
    public static final Coordinate EXAMPLE_5_RESULT = new Coordinate(11, 13);
    public static final int EXAMPLE_5_LOS = 210;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        boolean ok = false;
        Map theMap = new Map(EXAMPLE_1_FILE);

        if (_debug)
        {
            System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

            System.out.println("\nLoaded map ...\n\n"+theMap);
        }

        long value = theMap.maxDetectableAsteroid();

        System.out.println("**the value: "+value);

        /*

        theMap = new Map(EXAMPLE_2_FILE);

        System.out.println("\nLoaded map ...\n\n"+theMap);
        
        theMap = new Map(EXAMPLE_3_FILE);

        System.out.println("\nLoaded map ...\n\n"+theMap);

        theMap = new Map(EXAMPLE_4_FILE);

        System.out.println("\nLoaded map ...\n\n"+theMap);

        theMap = new Map(EXAMPLE_5_FILE);

        System.out.println("\nLoaded map ...\n\n"+theMap);*/

        return ok;
    }

    private boolean _debug;
}