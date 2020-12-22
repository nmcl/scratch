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
        boolean verify = false;
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
                verify = true;

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
                if (plotLines(line1, line2))
                {

                }
                else
                    System.out.println("Error in plotting lines!");
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

    private static final boolean plotLines (String[] line1, String[] lime2)
    {
        int xPos = _length/2;  // always start in the centre of the grid
        int yPos = _width/2;

        for (String str : line1)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    int left = Integer.parseInt(str.substring(1));

                    if (xPos - left < 0)
                    {
                        _length += left;

                        xPos = 0;
                    }
                    else
                        xPos -= left;
                }
                break;
                case TestPlotter.RIGHT:
                {
                    int right = Integer.parseInt(str.substring(1));

                    if (xPos + right > _length)
                    {
                        _length += right;

                        xPos += right;
                    }
                    else
                        xPos += right;
                }
                break;
                case TestPlotter.UP:
                {
                    int up = Integer.parseInt(str.substring(1));

                    if (yPos + up > _width)
                    {
                        _width += up;

                        yPos += up;
                    }
                    else
                        yPos += up;
                }
                break;
                case TestPlotter.DOWN:
                {
                    int down = Integer.parseInt(str.substring(1));

                    if (yPos - down < 0)
                    {
                        _width += down;

                        yPos = 0;
                    }
                    else
                        yPos -= down;
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

    private static int[][] _theBoard = null;
    private static int _length = 0;
    private static int _width = 0;

    private static final String DATA_FILE = "data.txt";
}