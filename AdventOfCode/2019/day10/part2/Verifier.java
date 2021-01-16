import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_RESULT = 802;
    
    public static final int TARGET_ASTEROID_NUMBER = 200;

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
        
        Asteroid bestLocation = theMap.getMonitoringStation();

        System.out.println("**best location "+bestLocation.getPosition());

        Vector<Target> results = theMap.sortedTargets(bestLocation);

        Target theSpecificTarget = results.elementAt(TARGET_ASTEROID_NUMBER -1);

        System.out.println("**target "+theSpecificTarget);

        return ok;
    }

    private boolean _debug;
}