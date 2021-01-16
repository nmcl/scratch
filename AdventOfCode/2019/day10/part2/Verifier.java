public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_RESULT = 802;
    
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    // consider adding expected dimensions of grid as an assertion?

    public boolean verify ()
    {
        boolean ok = false;
        Map theMap = new Map(EXAMPLE_FILE);

        if (_debug)
        {
            System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

            System.out.println("\nLoaded map ...\n\n"+theMap);
        }

        long value = theMap.maxDetectableAsteroid();
        
        theMap.getMonitoringStation();

        return ok;
    }

    private boolean _debug;
}