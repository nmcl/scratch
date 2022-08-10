import java.util.*;

public class Renovation
{
    public Renovation (boolean debug)
    {
        _debug = debug;
    }

    public HashSet<Coordinate> tilesOfLife (Vector<String> lines, int numberOfDays)
    {
        HashSet<Coordinate> blackTiles = getBlackTiles(lines);

        for (int i = 0; i < numberOfDays; i++)
        {
            HashSet<Coordinate> nextIteration = new HashSet<Coordinate>();
            Iterator<Coordinate> iter = blackTiles.iterator();

            System.out.println("Day "+i+" and "+blackTiles.size());

            while (iter.hasNext())
            {
                Coordinate current = iter.next();
                HashSet<Coordinate> adjacentTiles = Directions.adjacentCoordinates(current);
                int neighbouringBlackTiles = Util.commonBlackTiles(blackTiles, adjacentTiles);

                /*
                 * Any black tile with zero or more than 2 black tiles immediately adjacent
                 * to it is flipped to white.
                 */

                if ((neighbouringBlackTiles == 0) || (neighbouringBlackTiles > 2))
                {
                    //if (_debug)
                        System.out.println("Flipping "+current+" to white.");
                }
                else
                {
                    //if (_debug)
                        System.out.println("Leaving "+current+" as black.");

                    nextIteration.add(current);
                }

                /*
                 * Any white tile with exactly 2 black tiles immediately adjacent
                 * to it is flipped to black.
                 */

                Iterator<Coordinate> adjIter = adjacentTiles.iterator();

                while (adjIter.hasNext())
                {
                    Coordinate whiteTile = adjIter.next();

                    if (!blackTiles.contains(whiteTile)) // aka a white tile
                    {
                        int numberOfCommonBlackTiles = Util.commonBlackTiles(blackTiles, Directions.adjacentCoordinates(whiteTile));

                        if (numberOfCommonBlackTiles == 2)
                        {
                            //if (_debug)
                                System.out.println("Adding "+whiteTile+" as black.");

                            nextIteration.add(whiteTile);
                        }
                    }
                }
            }

            blackTiles = nextIteration;
        }

        return blackTiles;
    }

    private HashSet<Coordinate> getBlackTiles (Vector<String> lines)
    {
        HashSet<Coordinate> blackTiles = new HashSet<Coordinate>();

        for (int i = 0; i < lines.size(); i++)
        {
            Coordinate coord = tilePosition(lines.elementAt(i));

            if (!blackTiles.contains(coord))
                blackTiles.add(coord);
            else
                blackTiles.remove(coord);
        }

        return blackTiles;
    }

    private Coordinate tilePosition (String line)
    {
        Coordinate coord = new Coordinate(0, 0);
        int index = 0;
        String remainder = line;

        while (index < line.length())
        {
            int directionSize = 2;

            if ((remainder.startsWith(Directions.EAST)) || (remainder.startsWith(Directions.WEST)))
                directionSize = 1;

            String direction = remainder.substring(0, directionSize);

            remainder = remainder.substring(directionSize);
            index += directionSize;

            if (_debug)
            {
                System.out.println("Direction: "+direction);
                System.out.println("Remainder: "+remainder);
            }

            coord = Directions.getCoordinate(direction, coord);
        }

        return coord;
    }

    private boolean _debug;
}