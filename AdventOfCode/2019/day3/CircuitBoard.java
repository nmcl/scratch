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
    public static final String SEPARATOR = ",";

    // default circuit board size

    public static final int LENGTH = 1000;
    public static final int WIDTH = 1000;

    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';

    public static void main (String[] args)
    {
        boolean dump = false;
        int length = LENGTH;
        int width = WIDTH;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-verify] [-dump] [-length <length>] [-width <width>]");
                System.exit(0);
            }
            
            if ("-verify".equals(args[i]))
            {
                if (verify())
                    System.out.println("Verified ok!");
                else
                    System.out.println("Verify failed!");

                System.exit(0);
            }

            if ("-dump".equals(args[i]))
                dump = true;

            if ("-width".equals(args[i]))
                _width = Integer.parseInt(args[i+1]);

            if ("-length".equals(args[i]))
                _length = Integer.parseInt(args[i+1]);
        }

        _theBoard = new int[_length][_width];

        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
                _theBoard[i][j] = 0;
        }

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] line1 = null;
        String[] line2 = null;

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (line1 == null)
                    line1 = line.split(SEPARATOR);
                else
                {
                    if (line2 == null)
                        line2 = line.split(SEPARATOR);
                    else
                    {
                        System.out.println("Unexpected extra lines in input file!");

                        System.exit(0);
                    }
                }
            }

            if (dump)
                dumpData(line1, line2);
            else
            {
                if (plotLine(line1))
                {
                    if (plotLine(line1))
                    {

                    }
                    else
                        System.out.println("Error in plotting second line!");
                }
                else
                    System.out.println("Error in plotting first line!");
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
    }

    private static final boolean plotLine (String[] line)
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

    private static final void dumpData (String[] line1, String[] line2)
    {
        System.out.println("Wire 1:");
        for (String str : line1)
        {
            System.out.println(str);
        }

        System.out.println("\nWire 2:");
        for (String str : line2)
        {
            System.out.println(str);
        }
    }

    private static final boolean verify ()
    {

        return true;
    }

    private static int[][] _theBoard = null;
    private static int _length = 0;
    private static int _width = 0;

    private static final String DATA_FILE = "data.txt";

    private static final String TEST_LINE_1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72";
    private static final String TEST_LINE_2 = "U62,R66,U55,R34,D71,R55,D58,R83";
    private static final int TEST_DISTANCE_1 = 159;
    private static final String TEST_LINE_3 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51";
    private static final String TEST_LINE_4 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7";
    private static final int TEST_DISTANCE_2 = 135;
}