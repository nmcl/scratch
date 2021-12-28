import java.util.*;
import java.io.*;

public class Grid
{
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 100;

    public Grid (String fileName, boolean debug)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, fileName, debug);
    }

    public Grid (int height, int width, String fileName, boolean debug)
    {
        _height = height;
        _width = width;
        _debug = debug;

        loadWorld(fileName);
    }

    /**
     * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
     * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
     * Otherwise, the seat's state does not change.
     */

    public void evolve ()
    {
        Cell[][] _nextWorld = new Cell[_height][_width];
        
        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                int adjacentSeats = 0;

                // ignore range checking and rely on exception!

                if (_debug)
                    System.out.println("Checking cell < "+i+", "+j+" > : "+_theWorld[i][j]);

                try
                {
                    if (adjacentSeat(i-1, j))
                        adjacentSeats++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                }

                try
                {
                    if (adjacentSeat(i+1, j))
                        adjacentSeats++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                }

                try
                {
                    if (adjacentSeat(i, j-1))
                        adjacentSeats++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                }

                try
                {
                    if (adjacentSeat(i, j+1))
                        adjacentSeats++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                }

                if (_debug)
                    System.out.println("Adjacent seats: "+adjacentSeats);

                if (_theWorld[i][j].isEmptySeat())
                {
                    if (_debug)
                        System.out.println("Empty seat.");

                    if (adjacentSeats == 0)
                    {
                        if (_debug)
                            System.out.println("Seat will be occupied.");

                        _nextWorld[i][j] = new Cell(CellId.OCCUPIED_SEAT);
                    }
                }
                else
                {
                    if (_theWorld[i][j].isOccupiedSeat())
                    {
                        if (_debug)
                            System.out.println("Seat is occupied.");

                        if (adjacentSeats >= 4)
                        {
                            if (_debug)
                                System.out.println("Seat wil be unoccupied.");

                            _nextWorld[i][j] = new Cell(CellId.EMPTY_SEAT);
                        }
                    }
                }
            }
        }

        _theWorld = _nextWorld;
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
                str += _theWorld[i][j];
            
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
                        if (temp._theWorld[i][j].type() != _theWorld[i][j].type())
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

        _theWorld = new Cell[_height][_width];

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                _theWorld[i][j] = new Cell(theGrid._theWorld[i][j].type());
            }
        }
    }

    private boolean adjacentSeat (int i, int j) throws IndexOutOfBoundsException
    {
        if (_debug)
            System.out.println("Checking < "+i+", "+j+" >");

        if (_theWorld[i][j].isOccupiedSeat())
            return true;
        else
            return false;
    }

    private void loadWorld (String inputFile)
    {
        BufferedReader reader = null;
        int row = 0;
        int actualHeight = 0;
        int actualWidth = 0;

        _theWorld = new Cell[_height][_width];

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
                        _theWorld[row][i] = new Cell(line.charAt(i));
                    else
                        System.out.println("Invalid world entry: "+line.charAt(i));
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
            Cell[][] tempWorld = new Cell[actualHeight][actualWidth];

            for (int i = 0; i < actualHeight; i++)
            {
                for (int j = 0; j < actualWidth; j++)
                    tempWorld[i][j] = _theWorld[i][j];
            }

            _theWorld = tempWorld;
            _height = actualHeight;
            _width = actualWidth;
        }
    }

    private Cell[][] _theWorld;
    private int _height;
    private int _width;
    private boolean _debug;
}