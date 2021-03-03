import java.util.*;

public class Maze
{
    public static final String WALL = "#";
    public static final String UNEXPLORED = " ";
    public static final String DROID = ".";

    public Maze ()
    {
        _map = new Vector<Coordinate>();
    }

    private Vector<Coordinate> _map;
}