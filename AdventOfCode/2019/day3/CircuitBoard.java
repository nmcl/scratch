import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/*
 * Take the data given and find where the two wires cross.
 * 
 * The size of the circuit board should have been obtained
 * correctly beforehand using something like CircuitSize or
 * TestPlotter. The algorithms which those programs use
 * could be incorporated directly here but for now we'll keep
 * things simple.
 */

public class CircuitBoard
{
    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';

    public CircuitBoard (boolean debug)
    {
        _debug = debug;
    }

    public boolean plotLine (String[] line, Set<Coordinate> theLine)
    {
        int xPos = 0;
        int yPos = 0;

        for (String str : line)
        {
            if (_debug)
                System.out.println("Command "+str);

            switch (str.charAt(0))
            {
                case CircuitBoard.LEFT:
                {
                    int left = Integer.parseInt(str.substring(1));

                    for (int x = 0; x < left; x++)
                    {
                        Coordinate coord = new Coordinate(xPos-x, yPos);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }

                    xPos -= left;
                }
                break;
                case CircuitBoard.RIGHT:
                {
                    int right = Integer.parseInt(str.substring(1));

                    for (int x= 0; x < right; x++)
                    {
                        Coordinate coord = new Coordinate(xPos+x, yPos);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }

                    xPos += right;
                }
                break;
                case CircuitBoard.DOWN:
                {
                    int down = Integer.parseInt(str.substring(1));

                    for (int y = 0; y < down; y++)
                    {
                        Coordinate coord = new Coordinate(xPos, yPos-y);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }

                    yPos -= down;
                }
                break;
                case CircuitBoard.UP:
                {
                    int up = Integer.parseInt(str.substring(1));

                    for (int y = 0; y < up; y++)
                    {
                        Coordinate coord = new Coordinate(xPos, yPos+y);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }

                    yPos += up;
                }
                break;
                default:
                {
                    System.out.println("Unknown instruction: "+str.charAt(0));

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Get the Manhattan distance.
     * 
     * @param crossingLines the representation of lines crossing, e.g., "AB" or "ABCD".
     * @return the distance.
     */

    public int getDistance (Set<Coordinate> line1, Set<Coordinate> line2)
    {
        Set<Coordinate> overlap = new HashSet<Coordinate>();
        Object[] coords = line2.toArray();
        Iterator<Coordinate> iter = line1.iterator();
        int toReturn = Integer.MAX_VALUE;

        while (iter.hasNext())
        {
            Coordinate element = iter.next();

            if (_debug)
                System.out.println("Checking to see if "+element+" is an overlap.");

//            if (line2.contains(element))

            boolean duplicate = false;

            for (int i = 0; (i < coords.length) && !duplicate; i++)
            {
                if (element.equals((Coordinate) coords[i]))
                    duplicate = true;
            }

            if (duplicate)
            {
                overlap.add(element);

                if (_debug)
                    System.out.println("It is an overlap.");
            }
            else
            {
                if (_debug)
                    System.out.println("It is not an overlap.");
            }
        }

        if (overlap.size() > 0)
        {
            iter = overlap.iterator();

            while (iter.hasNext())
            {
                Coordinate element = iter.next();

                if (_debug)
                    System.out.println("Comparing "+element+" with "+toReturn);
                
                if ((element.getX() != 0) && (element.getY() != 0))
                {
                    if (Math.abs(element.getX()) + Math.abs(element.getY()) < toReturn)
                        toReturn = Math.abs(element.getX()) + Math.abs(element.getY());
                }
            }
        }
        else
        {
            if (_debug)
                System.out.println("No overlaps found!");
        }

        if (_debug)
            System.out.println("Manhattan distance "+toReturn);

        return toReturn;
    }

    public void printCircuit (Set<Coordinate> theLine)
    {
        Iterator<Coordinate> iter = theLine.iterator();

        System.out.println("Printing line coordinates.");

        while (iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }

    private boolean _debug = false;
}