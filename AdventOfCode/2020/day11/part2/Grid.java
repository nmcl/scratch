import java.util.*;
import java.io.*;

public class Grid
{
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 100;

    private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {1, 0}, {0, 1}};

    public Grid (String fileName, boolean debug)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, fileName, debug);
    }

    public Grid (int height, int width, String fileName, boolean debug)
    {
        _height = height;
        _width = width;
        _debug = debug;

        loadPlane(fileName);
    }

    public int occupiedSeats ()
    {
        int count = 0;

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if (_thePlane[i][j].isOccupiedSeat())
                    count++;
            }
        }

        return count;
    }

    /**
     * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * Otherwise, the seat's state does not change.
     */

    public int evolve ()
    {
        Cell[][] _nextPlane = new Cell[_height][_width];
        int changedSeats = 0;

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                int visibleSeats = 0;

                // ignore range checking and rely on exception!

                if (_debug)
                    System.out.println("Checking cell < "+i+", "+j+" > : "+_thePlane[i][j]);

                /*
                 * -1,1 0,1 1,1
                 * -1,0 0,0 1,0
                 * -1,-1 0,-1 1,-1
                 */

                for (int d = 0; d < DIRECTIONS.length; d++)
                {
                    try
                    {
                        if (visibleSeat(i, j, DIRECTIONS[d][0], DIRECTIONS[d][1]))
                            visibleSeats++;
                    }
                    catch (IndexOutOfBoundsException ex)
                    {
                    }
                }

                if (_debug)
                    System.out.println("Adjacent seats: "+visibleSeats);

                if (_thePlane[i][j].isEmptySeat())
                {
                    if (_debug)
                        System.out.println("Empty seat.");

                    if (visibleSeats == 0)
                    {
                        if (_debug)
                            System.out.println("Seat will be occupied.");

                        _nextPlane[i][j] = new Cell(CellId.OCCUPIED_SEAT);

                        changedSeats++;
                    }
                }
                else
                {
                    if (_thePlane[i][j].isOccupiedSeat())
                    {
                        if (_debug)
                            System.out.println("Seat is occupied.");

                        if (visibleSeats >= 5)
                        {
                            if (_debug)
                                System.out.println("Seat wil be unoccupied.");

                            _nextPlane[i][j] = new Cell(CellId.EMPTY_SEAT);

                            changedSeats++;
                        }
                    }
                }

                if (_nextPlane[i][j] == null)
                {
                    if (_debug)
                        System.out.println("Seat state remains the same.");

                    _nextPlane[i][j] = new Cell(_thePlane[i][j]);
                }
            }
        }

        _thePlane = _nextPlane;

        return changedSeats;
    }

    public Grid snapshot ()
    {
        return new Grid(this);
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
                str += _thePlane[i][j];
            
            str += "\n";
        }

        return str;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Grid temp = (Grid) obj;

            if ((temp._height == _height) && (temp._width == _width))
            {
                for (int i = 0; i < _height; i++)
                {
                    for (int j = 0; j < _width; j++)
                    {   
                        if (temp._thePlane[i][j].type() != _thePlane[i][j].type())
                            return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    protected Grid (Grid theGrid)
    {
        _height = theGrid._height;
        _width = theGrid._width;
        _debug = theGrid._debug;

        _thePlane = new Cell[_height][_width];

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                _thePlane[i][j] = new Cell(theGrid._thePlane[i][j].type());
            }
        }
    }

    private boolean visibleSeat (int i, int j, int hDelta, int wDelta) throws IndexOutOfBoundsException
    {
        int x = i + hDelta;
        int y = j + wDelta;

        if (_debug)
            System.out.println("Checking < "+x+", "+y+" >");

        if (_thePlane[x][y].isOccupiedSeat())
            return true;
        else
        {
            if (_thePlane[x][y].isFloor())
            {
                return visibleSeat(x, y, hDelta, wDelta);
            }
            else
                return false;
        }
    }

    private void loadPlane (String inputFile)
    {
        BufferedReader reader = null;
        int row = 0;
        int actualHeight = 0;
        int actualWidth = 0;

        _thePlane = new Cell[_height][_width];

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                actualWidth = line.length();

                for (int i = 0; i < line.length(); i++)
                {
                    if (CellId.valid(line.charAt(i)))
                        _thePlane[row][i] = new Cell(line.charAt(i));
                    else
                        System.out.println("Invalid plane entry: "+line.charAt(i));
                }

                row++;
            }
        }
        catch (Throwable ex)
        {
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

        actualHeight = row;

        if ((actualHeight != _height) || (actualWidth != _width))
        {
            Cell[][] tempPlane = new Cell[actualHeight][actualWidth];

            for (int i = 0; i < actualHeight; i++)
            {
                for (int j = 0; j < actualWidth; j++)
                    tempPlane[i][j] = _thePlane[i][j];
            }

            _thePlane = tempPlane;
            _height = actualHeight;
            _width = actualWidth;
        }
    }

    private Cell[][] _thePlane;
    private int _height;
    private int _width;
    private boolean _debug;
}