import java.util.*;
import java.io.*;

/*
 * Various utilities. Some are fairly simple.
 */

public class Util
{
    public static final int cost (HashMap<Coordinate, Integer> stepsToLocation, Coordinate start, Coordinate destination)
    {
        return stepsToLocation.get(start) + start.distanceTo(destination);
    }

    public static final Wormhole findWormhole (Vector<Wormhole> wormholes, String name)
    {
        Enumeration<Wormhole> iter = wormholes.elements();

        while (iter.hasMoreElements())
        {
            Wormhole hole = iter.nextElement();

            if (hole.getName().equals(name))
                return hole;
        }

        return null;
    }

    public static final Vector<Coordinate> extractCoordinates (Vector<Wormhole> wormholes)
    {
        Vector<Coordinate> coords = new Vector<Coordinate>();
        Enumeration<Wormhole> iter = wormholes.elements();

        while (iter.hasMoreElements())
            coords.add(iter.nextElement().getLocation());

        return coords;
    }

    private Util ()
    {
    }
}