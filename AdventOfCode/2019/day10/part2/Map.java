import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Map
{
    public  Map (String fileToLoad)
    {
        _theMap = new Vector<MapEntry>();

        if (!loadData(fileToLoad))
            System.out.println("Error in loading data file: "+fileToLoad);
    }

    public final int getHeight ()
    {
        return _height;
    }

    public final int getWidth ()
    {
        return _width;
    }

    /*
     * Stream through all of the asteroids and get the
     * count of visible items for each.
     */

    public final long maxDetectableAsteroid () 
    {
        Vector<Asteroid> allAsteroids = filterAsteroids();

        return allAsteroids.stream()
                .mapToLong(asteroid -> detectableAsteroids(allAsteroids, asteroid))
                .max().getAsLong();
    }

    public final Asteroid getMonitoringStation ()
    {
        Vector<Asteroid> allAsteroids = filterAsteroids();
        Enumeration<Asteroid> iter = allAsteroids.elements();
        Asteroid bestPosition = null;
        long maxDetected = 0;

        while (iter.hasMoreElements())
        {
            Asteroid as = (Asteroid) iter.nextElement();
            long detectable = detectableAsteroids(allAsteroids, as);

            if (detectable > maxDetected)
            {
                maxDetected = detectable;
                bestPosition = as;
            }
        }

        return bestPosition;
    }

    public Vector<Target> sortedTargets (Asteroid laserLocation)
    {
        return sortTargetsBySweepAngleThenDistance(laserLocation, filterAsteroids());
    }

    @Override
    public String toString ()
    {
        int xAxis = 0;
        Enumeration<MapEntry> iter = _theMap.elements();
        String str = "";

        while (iter.hasMoreElements())
        {
            MapEntry theEntry = iter.nextElement();

            if (theEntry.getPosition().getX() != xAxis)
            {
                xAxis = theEntry.getPosition().getX();
                str += "\n";
            }

            str += theEntry.toString();
        }

        return str;
    }

    private final long detectableAsteroids (Vector<Asteroid> theList, Asteroid from)
    {
        return theList.stream()
                .map(asteroid -> from.angleTo(asteroid))
                .distinct().count();
    }

    // sort by sweep angle and then distance.

    private final Vector<Target> sortTargetsBySweepAngleThenDistance (Asteroid from, Vector<Asteroid> allAsteroids)
    {
        Vector<Target> sortedAllTargets = allAsteroids.stream()
                        .filter(t -> !t.equals(from))
                        .map(t -> new Target(from, t))
                        .sorted((a, b) -> {
                                    if (a.getAngle().equals(b.getAngle())) return a.getDistance() - b.getDistance();
                                    else return Double.compare(Double.valueOf(a.getAngle()), Double.valueOf(b.getAngle()));
                        }).collect(Collectors.toCollection(Vector::new));

        int index = 0;
        String laserAngle = null;
        Vector<Target> finalSortedTarget = new Vector<Target>();

        // walk through the targets

        while (!sortedAllTargets.isEmpty())
        {
            if (!sortedAllTargets.elementAt(index).getAngle().equals(laserAngle))
            {
                Target target = sortedAllTargets.remove(index);

                laserAngle = target.getAngle();
                finalSortedTarget.add(target);
            }
            else
                index++;

            if (index >= sortedAllTargets.size())
            {
                index = 0;
                laserAngle = null;
            }
        }

        return finalSortedTarget;
    }

    /*
     * The Map we store contains empty elements. This method simply
     * filters out the empty space and returns only the asteroids.
     */

    private final Vector<Asteroid> filterAsteroids ()
    {
        /*
         * Remove all empty items before we search so we can
         * ignore comparisons with space.
         */

        Vector<Asteroid> allAsteroids = new Vector<Asteroid>();
        Enumeration<MapEntry> iter = _theMap.elements();

        while (iter.hasMoreElements())
        {
            MapEntry item = iter.nextElement();

            if (item instanceof Asteroid)
                allAsteroids.add((Asteroid) item);
        }

        return allAsteroids;
    }

    private final boolean loadData (String file)
    {
        BufferedReader reader = null;
        boolean valid = true;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                char[] asChar = line.toCharArray();
                
                if (_width == 0)
                    _width = asChar.length;

                for (int i = 0; i < _width; i++)
                {
                    // remember x,y coords are reversed for arrays
                    
                    if (asChar[i] == Asteroid.ASTEROID_REPRESENTATION)
                    {
                        _theMap.add(new Asteroid(i, _height));
                    }
                    else
                    {
                        if (asChar[i] == Empty.EMPTY_REPRESENTATION)
                        {
                            _theMap.add(new Empty(i, _height));
                        }
                        else
                        {
                            System.out.println("Unknown character in data file: "+asChar[i]);

                            valid = false;
                        }
                    }
                }

                _height++;
            }
        }
        catch (Throwable ex)
        {
            valid = false;

            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return valid;
    }

    private Vector<MapEntry> _theMap;
    private int _height = 0;
    private int _width = 0;
}