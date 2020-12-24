import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Collections;

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
                        xPos--;

                        Coordinate coord = new Coordinate(xPos, yPos);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }
                }
                break;
                case CircuitBoard.RIGHT:
                {
                    int right = Integer.parseInt(str.substring(1));

                    for (int x= 0; x < right; x++)
                    {
                        xPos++;

                        Coordinate coord = new Coordinate(xPos, yPos);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }
                }
                break;
                case CircuitBoard.DOWN:
                {
                    int down = Integer.parseInt(str.substring(1));

                    for (int y = 0; y < down; y++)
                    {
                        yPos--;

                        Coordinate coord = new Coordinate(xPos, yPos);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }
                }
                break;
                case CircuitBoard.UP:
                {
                    int up = Integer.parseInt(str.substring(1));

                    for (int y = 0; y < up; y++)
                    {
                        yPos++;

                        Coordinate coord = new Coordinate(xPos, yPos);

                        if (_debug)
                            System.out.println("Adding "+coord);

                        theLine.add(coord);
                    }
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
     * Gets the overlapping coordinates between two given lines.
     * 
     * @param line1 first line.
     * @param line2 second line.
     * @return the overlaps.
     */
    
    public Set<Coordinate> getOverlaps(Set<Coordinate> line1, Set<Coordinate> line2)
    {
        Set<Coordinate> overlaps = line1.stream().filter(coord -> line2.contains(coord)).collect(Collectors.toSet());

        return overlaps;
    }

    /**
     * Get the Manhattan distance.
     * 
     * @param overlaps the Set of the overlapping coordinates.
     * @return the distance.
     */

    public int getManhattanDistance (Set<Coordinate> overlaps)
    {
        List<Integer> distances = overlaps.stream().map(coord -> Math.abs(coord.getX()) + Math.abs(coord.getY())).collect(Collectors.toList());

        return Collections.min(distances);
    }

    public int getMinimumDistance (Set<Coordinate> overlaps, String[] line1, String[] line2)
    {
        List<Integer> distances = overlaps.stream().map(coordinate -> stepsTravelled(coordinate, line1) + stepsTravelled(coordinate, line2)).collect(Collectors.toList());

        return Collections.min(distances);
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

    private int stepsTravelled (Coordinate coord, String[] line)
    {
        int stepsTaken = 0;
        int length = 0;
        int xPos = 0;
        int yPos = 0;

        for (String str : line)
        {
            length = Integer.parseInt(str.substring(1));

            for (int i = 0; i < length; i++)
            {
                switch (str.charAt(0))
                {
                case CircuitBoard.RIGHT:
                    xPos++;
                    break;
                case CircuitBoard.LEFT:
                    xPos--;
                    break;
                case CircuitBoard.UP:
                    yPos++;
                    break;
                case CircuitBoard.DOWN:
                    yPos--;
                    break;
                default:
                    System.out.println("Unknown instruction: "+str.charAt(0));

                    break;
                }

                stepsTaken++;

                if (coord.equals(new Coordinate(xPos, yPos)))
                    break; 
            }

            if (coord.equals(new Coordinate(xPos, yPos)))
                break;
        }

        return stepsTaken;
    }
    
    private boolean _debug = false;
}