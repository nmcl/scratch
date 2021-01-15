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

    // consider adding expected dimensions of grid as an assertion?

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

        ok = (value == EXAMPLE_1_LOS);

        if (ok)
        {
            theMap = new Map(EXAMPLE_2_FILE);

            if (_debug)
            {
                System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

                System.out.println("\nLoaded map ...\n\n"+theMap);
            }

            value = theMap.maxDetectableAsteroid();

            ok = (value == EXAMPLE_2_LOS);

            if (ok)
            {
                theMap = new Map(EXAMPLE_3_FILE);

                if (_debug)
                {
                    System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

                    System.out.println("\nLoaded map ...\n\n"+theMap);
                }

                value = theMap.maxDetectableAsteroid();

                ok = (value == EXAMPLE_3_LOS);

                if (ok)
                {
                    theMap = new Map(EXAMPLE_4_FILE);

                    if (_debug)
                    {
                        System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

                        System.out.println("\nLoaded map ...\n\n"+theMap);
                    }

                    value = theMap.maxDetectableAsteroid();

                    ok = (value == EXAMPLE_4_LOS);

                    if (ok)
                    {
                        theMap = new Map(EXAMPLE_5_FILE);

                        if (_debug)
                        {
                            System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

                            System.out.println("\nLoaded map ...\n\n"+theMap);
                        }

                        value = theMap.maxDetectableAsteroid();

                        ok = (value == EXAMPLE_5_LOS);
                    }
                    else
                        System.out.println("Verify failed for "+EXAMPLE_4_FILE);
                }
                else
                    System.out.println("Verify failed for "+EXAMPLE_3_FILE);
            }
            else
                System.out.println("Verify failed for "+EXAMPLE_2_FILE);
        }
        else
            System.out.println("Verify failed for "+EXAMPLE_1_FILE);

        return ok;
    }

    private boolean _debug;
}