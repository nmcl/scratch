import java.util.*;
import java.io.*;

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

        return allAsteroids.stream()
                .mapToLong(asteroid -> detectableAsteroids(allAsteroids, asteroid))
                .max().getAsLong();
    }

    public final Asteroid getMonitoringStation ()
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

        Enumeration<Asteroid> iter2 = allAsteroids.elements();
        Asteroid bestPosition = null;
        long maxDetected = 0;

        while (iter2.hasMoreElements())
        {
            Asteroid as = (Asteroid) iter2.nextElement();
            long detectable = detectableAsteroids(allAsteroids, as);

            if (detectable > maxDetected)
            {
                maxDetected = detectable;
                bestPosition = as;
            }
        }

        return bestPosition;
    }

    private final long detectableAsteroids (Vector<Asteroid> theList, Asteroid from)
    {
        return theList.stream()
                .map(asteroid -> from.angleTo(asteroid))
                .distinct().count();
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

    private Vector<MapEntry> _theMap;
    private int _height = 0;
    private int _width = 0;
}