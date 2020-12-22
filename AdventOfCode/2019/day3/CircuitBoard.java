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

    public CircuitBoard (int length, int width)
    {
        _length = length;
        _width = width;

        _theBoard = new String[_length][_width];

        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
                _theBoard[i][j] = "";
        }
    }

    public boolean plotLine (String[] line, String lineID)
    {
        int xPos = _length/2;  // always start in the centre of the grid
        int yPos = _width/2;

        for (String str : line)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    int left = Integer.parseInt(str.substring(1));

                    if (xPos - left >= 0)
                    {
                        for (int i = xPos-left+1; i != xPos; i++)
                        {
                            if (!_theBoard[i][yPos].contains(lineID))
                                _theBoard[i][yPos] += lineID;
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

                    if (xPos + right < _length)
                    {
                        for (int i = xPos; i != xPos + right; i++)
                        {
                            if (!_theBoard[i][yPos].contains(lineID))
                                _theBoard[i][yPos] += lineID;
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
                case TestPlotter.UP:
                {
                    int up = Integer.parseInt(str.substring(1));

                    if (yPos + up < _width)
                    {
                        for (int i = yPos; i != yPos + up; i++)
                        {
                            if (!_theBoard[i][yPos].contains(lineID))
                                _theBoard[xPos][i] += lineID;
                        }

                        yPos += up;
                    }
                    else
                    {
                        System.out.println("UP "+Integer.parseInt(str.substring(1))+" moved yPos beyond max width!");

                        return false;
                    }
                }
                break;
                case TestPlotter.DOWN:
                {
                    int down = Integer.parseInt(str.substring(1));

                    if (yPos - down >= 0)
                    {
                        for (int i = yPos - down +1; i != yPos; i++)
                        {
                            if (!_theBoard[i][yPos].contains(lineID))
                                _theBoard[xPos][i] += lineID;
                        }

                        yPos -= down;
                    }
                    else
                    {
                        System.out.println("DOWN "+Integer.parseInt(str.substring(1))+" moved yPos negative!");

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
        int result = (int) Math.sqrt((_length*_length) + (_width*_width));
        int xPos = 0;
        int yPos = 0;
        boolean found = false;

        System.out.println("Origin is "+(_length/2 + _width/2));

        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if (!(_theBoard[i][j]).equals(""))
                {
                    if ((_theBoard[i][j].equals(crossingLines)) && ((i != _length/2) && (j != _width/2)))  // ignore origin
                    {
                        System.out.println("\nChecking "+i+" "+j);
                        System.out.println("Current is "+result);
                        System.out.println("Crosses at "+i+" "+j);
                        System.out.println("Have "+_theBoard[i][j]);
                        found = true;

                        int temp = (int) Math.sqrt((i*i)+(j*j));

                        if (temp < result)
                            result = i+j;
                    }
                }
            }
        }

        System.out.println("Got "+(result - (_length/2 + _width/2)));

        if (found)
            return result - (_length/2 + _width/2);
        else
            return 0;
    }

    public final void printBoard ()
    {
        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if ("".equals(_theBoard[i][j]))
                    System.out.print(".");
                else
                {
                    if (_theBoard[i][j].length() > 1)
                        System.out.print("X");
                    else
                        System.out.print("0");
                }
            }

            System.out.println();
        }
    }

    private String[][] _theBoard = null;
    int _length = 0;
    int _width = 0;
}