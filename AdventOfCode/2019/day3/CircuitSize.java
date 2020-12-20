import java.io.*;

public class TestPlotter
{
    public static final String SEPARATOR = ",";

    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';

    /*
     * Building on TestPlotter, rather than have the user give
     * values for length and width for us to test against, this
     * program takes the two lines and finds the circuit board
     * size needed to cope with the wires.
     */

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-debug]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
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

            if (debug)
                dumpData(line1, line2);
            else
            {
                if (checkMatrixSize(line1) && checkMatrixSize(line2))
                {
                    System.out.println("Matrix of "+_length+" and "+_width+" is sufficient.");
                }
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

    private static final boolean checkMatrixSize (String[] line)
    {
        int xPos = _length/2;
        int yPos = _width/2;

        for (String str : line)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    int left = Integer.parseInt(str.substring(1));

                    /*
                     * L10, U50, L95, D70, R20
                     */

                    if (xPos - left < 0)
                    {
                        _length += left * 2; // origin should be in the middle

                        xPos += left;  // move ptr in other direction so we don't go over the edge.
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
                        _length += right * 2; // origin should be in the middle

                        xPos -= right;  // move ptr in other direction so we don't go over the edge.
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
                        _width += up * 2; // origin should be in the middle

                        yPos -= up;  // move ptr in other direction so we don't go over the edge.  Negative?
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
                        _width += up * 2; // origin should be in the middle

                        yPos += down;
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

    private static int _length = 0;
    private static int _width = 0;

    private static final String DATA_FILE = "data.txt";
}