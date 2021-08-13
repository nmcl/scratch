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

    private Util ()
    {
    }
}