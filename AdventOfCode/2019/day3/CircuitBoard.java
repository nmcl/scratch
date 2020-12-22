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

    public CircuitBoard ()
    {

    }

    public boolean plotLine (String[] line)
    {
        int xPos = _length/2;  // always start in the centre of the grid
        int yPos = _width/2;

        for (String str : line)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    xPos -= Integer.parseInt(str.substring(1));

                    if (xPos >= 0)
                        _theBoard[xPos][yPos]++;
                    else
                        return false;
                }
                break;
                case TestPlotter.RIGHT:
                {
                    xPos += Integer.parseInt(str.substring(1));

                    if (xPos < _length)
                        _theBoard[xPos][yPos]++;
                    else
                        return false;
                }
                break;
                case TestPlotter.UP:
                {
                    yPos += Integer.parseInt(str.substring(1));

                    if (yPos < _width)
                        _theBoard[xPos][yPos]++;
                    else
                        return false;
                }
                break;
                case TestPlotter.DOWN:
                {
                    yPos -= Integer.parseInt(str.substring(1));

                    if (yPos >= 0)
                        _theBoard[xPos][yPos]++;
                    else
                        return false;
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

    public int getDistance ()
    {
        int result = -1;
        int xPos = 0;
        int yPos = 0;

        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                if (_theBoard[i][j] > 1)
                {
                    System.out.println("Wires cross at <"+i+", "+j+">");
                }
            }
        }

        return -1;
    }

    public final void printBoard ()
    {
        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                System.out.println("<"+i+", "+j+", "+_theBoard[i][j]+">");
            }
        }
    }

    private static int _length = DEFAULT_LENGTH;
    private static int _width = DEFAULT_WIDTH;

    private static final String DATA_FILE = "data.txt";

    /*
     * Let's try something different this time with verifying the
     * program works. Rather than embed verification within and potentially
     * duplicate code, we'll have the examples in their own files and load
     * them separately, compare with the expected result and give a
     * true/false outcome accordingly.
     */

    private static final String EXAMPLE1 = "example1.txt";
    private static final String EXAMPLE2 = "example2.txt";
    private static final int EXAMPLE1_DISTANCE = 159;
    private static final int EXAMPLE2_DISTANCE = 135;
}