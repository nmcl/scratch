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

    private Wormhole findWormhole (Vector<Wormhole> wormholes, String name)
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

    private Util ()
    {
    }
}