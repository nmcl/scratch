import java.util.*;

public class Placement
{
    public static final String DATA_FILE = "data.txt";

    /*
     * Each asteroid is a point in the centre of it's "grid/square". To determine if
     * an asteroid is visible from another without being blocked, we "just" need to
     * be able to plot a straight line between the starting asteroid and the current
     * one in question.
     */

    public static final void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;
        int asteroidNumber = 200;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug] [-verify] [-asteriod <number>]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;

            if ("-asteroid".equals(args[i]))
                asteroidNumber = Integer.parseInt(args[i+1]);
        }

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("Verified ok!");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Map theMap = new Map(DATA_FILE);

        if (debug)
        {
            System.out.println("Loaded map "+theMap.getHeight()+" by "+theMap.getWidth());

            System.out.println("\nLoaded map ...\n\n"+theMap);
        }

        Asteroid bestLocation = theMap.getMonitoringStation();

        if (debug)
            System.out.println("Best location "+bestLocation.getPosition());

        Vector<Target> results = theMap.sortedTargets(bestLocation);
        Target theSpecificTarget = results.elementAt(asteroidNumber -1);

        if (debug)
            System.out.println("Target "+theSpecificTarget);

        int value = theSpecificTarget.toDestroy().getPosition().getX()*100 + theSpecificTarget.toDestroy().getPosition().getY();
   
        System.out.println("Final value: "+value);
    }
}