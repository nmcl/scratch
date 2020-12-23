import java.io.*;

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

    public CircuitBoard (int maxX, int maxY)
    {
        _xAxis = maxX;
        _yAxis = maxY;

        _theBoard = new String[_yAxis][_xAxis];

        for (int y = 0; y < _yAxis; y++)
        {
            for (int x = 0; x < _xAxis; x++)
                _theBoard[y][x] = "";
        }
    }

    public boolean plotLine (String[] line, String lineID)
    {
        int xPos = _xAxis/2;  // always start in the centre of the grid
        int yPos = _yAxis/2;

        for (String str : line)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    int left = Integer.parseInt(str.substring(1));

                    if (xPos - left >= 0)
                    {
                        for (int x = xPos-left; x != xPos; x++)
                        {
                            if (!_theBoard[yPos][x].contains(lineID))
                                _theBoard[yPos][x] += lineID;
                        }

                        xPos -= left;
                    }
                    else
                    {
                        System.out.println("LEFT "+Integer.parseInt(str.substring(1))+" moved xPos negative!");

                        return false;
                    }
                }
                break;
                case TestPlotter.RIGHT:
                {
                    int right = Integer.parseInt(str.substring(1));

                    if (xPos + right < _xAxis)
                    {
                        for (int x= xPos; x <= xPos + right; x++)
                        {
                            if (!_theBoard[yPos][x].contains(lineID))
                                _theBoard[yPos][x] += lineID;
                        }

                        xPos += right;
                    }
                    else
                    {
                        System.out.println("RIGHT "+Integer.parseInt(str.substring(1))+" moved xPos beyond max length!");

                        return false;
                    }
                }
                break;
                case TestPlotter.DOWN:
                {
                    int down = Integer.parseInt(str.substring(1));

                    if (yPos + down < _yAxis)
                    {
                        for (int y = yPos; y <= yPos + down; y++)
                        {
                            if (!_theBoard[y][xPos].contains(lineID))
                                _theBoard[y][xPos] += lineID;
                        }

                        yPos += down;
                    }
                    else
                    {
                        System.out.println("DOWN "+Integer.parseInt(str.substring(1))+" moved yPos beyond max width!");

                        return false;
                    }
                }
                break;
                case TestPlotter.UP:
                {
                    int up = Integer.parseInt(str.substring(1));

                    if (yPos - up >= 0)
                    {
                        for (int y = yPos - up; y <= yPos; y++)
                        {
                            if (!_theBoard[y][xPos].contains(lineID))
                                _theBoard[y][xPos] += lineID;
                        }

                        yPos -= up;
                    }
                    else
                    {
                        System.out.println("UP "+Integer.parseInt(str.substring(1))+" moved yPos negative!");

                        return false;
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

    public int getDistance (String crossingLines)
    {
        int result = (int) Math.sqrt((_xAxis*_xAxis) + (_yAxis*_yAxis));
        boolean found = false;

        System.out.println("Origin is "+(_xAxis/2 + _yAxis/2));

        for (int y = 0; y < _yAxis; y++)
        {
            for (int x = 0; x < _xAxis; x++)
            {
                if (!(_theBoard[y][x]).equals(""))
                {
                    if ((_theBoard[y][x].equals(crossingLines)) && ((x != _xAxis/2) && (y != _yAxis/2)))  // ignore origin
                    {
                        System.out.println("\nChecking "+x+" "+y);
                        System.out.println("Current is "+result);
                        System.out.println("Have "+_theBoard[y][x]);
                        found = true;

                        int temp = (int) Math.sqrt((x*x)+(y*y));

                        if (temp < result)
                            result = x+y;
                    }
                }
            }
        }

        System.out.println("Got "+(result - (_xAxis/2 + _yAxis/2)));

        if (found)
            return result - (_xAxis/2 + _yAxis/2);
        else
            return 0;
    }

    public final void printBoard ()
    {
        for (int y = 0; y < _yAxis; y++)
        {
            for (int x = 0; x < _xAxis; x++)
            {
                if ("".equals(_theBoard[y][x]))
                    System.out.print(".");
                else
                {
                    if (_theBoard[y][x].length() > 1)
                        System.out.print("X");
                    else
                        System.out.print("0");
                }
            }

            System.out.println();
        }
    }

    private String[][] _theBoard = null;
    private int _xAxis = 0;
    private int _yAxis = 0;
}