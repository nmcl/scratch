import java.util.*;

public class Directions
{
    public static final String EAST = "e";
    public static final String SOUTH_EAST = "se";
    public static final String SOUTH_WEST = "sw";
    public static final String WEST = "w";
    public static final String NORTH_WEST = "nw";
    public static final String NORTH_EAST = "ne";

    public final static Coordinate getCoordinate (String direction, Coordinate current)
    {
        switch (direction)
        {
            case EAST:
            {
                return new Coordinate(current.getX()+2, current.getY());
            }
            case SOUTH_EAST:
            {
                return new Coordinate(current.getX()+1, current.getY()-1);
            }
            case SOUTH_WEST:
            {
                return new Coordinate(current.getX()-1, current.getY()-1);
            }
            case WEST:
            {
                return new Coordinate(current.getX()-2, current.getY());
            }
            case NORTH_WEST:
            {
                return new Coordinate(current.getX()-1, current.getY()+1);
            }
            case NORTH_EAST:
            default:
            {
                return new Coordinate(current.getX()+1, current.getY()+1);
            }
        }
    }

    /*
     * Tiles immediately adjacent means the six tiles directly touching the tile in question.
     */

    public final static HashSet<Coordinate> adjacentCoordinates (Coordinate current)
    {
        HashSet<Coordinate> coords = new HashSet<Coordinate>();

        // pretty manual
        
        coords.add(Directions.getCoordinate(EAST, current));
        coords.add(Directions.getCoordinate(SOUTH_EAST, current));
        coords.add(Directions.getCoordinate(SOUTH_WEST, current));
        coords.add(Directions.getCoordinate(WEST, current));
        coords.add(Directions.getCoordinate(NORTH_WEST, current));
        coords.add(Directions.getCoordinate(NORTH_EAST, current));

        return coords;
    } 

    private Directions ()
    {
    }
}